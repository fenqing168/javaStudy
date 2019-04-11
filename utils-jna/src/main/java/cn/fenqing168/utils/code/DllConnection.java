package cn.fenqing168.utils.code;

import cn.fenqing168.utils.lib.Dll;
import com.sun.jna.Native;

import java.net.URL;

/**
 * Dll发送请求连接对象
 * 可以构建连接以及使用该连接的一系列操作
 * 该连接对象已内置Dll JNI接口
 */
public class DllConnection {

    /**
     * Dll接口的单例对象
     */
    private static Dll DLL;

    /**
     * 加载dll文件
     * 请在classpath下创建一个dll文件夹，将dll库文件复制到路径下
     * maven项目，直接在resources下创建一个dll文件夹，将ETHDLL.dll放入即可
     */
    static {
        String classpath = DllConnection.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        DLL = (Dll) Native.loadLibrary("ETHDLL", Dll.class);
    }

    /**
     * 连接的id
     */
    private int connectIndex;

    /**
     * 网络请求的方式
     */
    public final static int CONN_TYPE_UDP = 0, CONN_TYPE_TCP = 1;

    /**
     * 本地默认端口
     */
    public final static int IDLE_PORT = 0;

    /**
     * 构造函数
     *
     * @param ip        ip
     * @param port      服务器端口
     * @param localPort 本地端口， udp才需要使用，tcp直接使用IDLE_PORT
     * @param type      udp：CONN_TYPE_UDP   tcp：CONN_TYPE_TCP
     * @param outTime   超时时间
     * @param num       重连次数
     */
    public DllConnection(String ip, int port, int localPort, int type, int outTime, int num) {
        if (type == CONN_TYPE_UDP) {
            throw new RuntimeException("UDP暂时无法提供使用");
        }
        int[] ipbuff = new int[]{DLL.IP_StrToInt(ip)};
        DLL.SysInit(ipbuff, 1);
        connectIndex = DLL.ConnectCreate(ipbuff[0], (short) port, (short) localPort, type, outTime, num);
    }

    /**
     * 关闭连接
     */
    public void close(){
        DLL.ConnectDel(connectIndex);
    }

    /**
     * @param adFrequency 采样频率，单位是 HZ，是单个通道的频率，不是所有采集通道的总频率。比如要连续采样 3 个通道，要求每个通道的采样频率达到 10000Hz，则这个参数应该输入 10000。
     * @param adRange     A/D 输入范围，该参数对 SK1218A、SK2011A、SK2011B 时 0—±5V，1—±10V；对SK2013A 时，0-±2.5V，1-±1.25V，2-±625mV，3-±312.5mV，4-±156.25mV，5-±78.125mV，其他产品时，该参数填 0。
     * @param ainSelect   模拟信号输入选择，每路占 2bit，=0 为 DC，=1 为 Rtd，=2 为 AC，=3 为 ICP。
     * @param aisleNum   直接填写10进制即可 1代表一个通道 2代表两个.....
     * @param masterFlag  设置相应的板为主控板或从板，=0 为主控板，=1 为从板。
     * @return
     */
    public int syncParaWrite(int adFrequency, int adRange, int ainSelect, int aisleNum, short masterFlag){
        short chEnabled = compute8421(aisleNum);
        DLL.ADSyncParaWrite(adFrequency, adRange, ainSelect, chEnabled, masterFlag, connectIndex);
        int num = DLL.ADStart(connectIndex);
        return aisleNum;
    }

    /**
     * 获取原始数据
     * @param aisleNum syncParaWrite返回的数据，通道的数量
     * @param groupNum 获取多少组数据
     * @return
     */
    public short[] dataRead(int aisleNum, int groupNum){
        int size = aisleNum * groupNum;
        short[] datas = new short[size];
        int num = DLL.ADDataRead(datas, size, connectIndex);
        return datas;
    }

    /**
     * 获取各个通道原始数据
     * @param aisleNum syncParaWrite返回的数据，通道的数量
     * @param groupNum 获取多少组数据
     * @return
     */
    public short[][] dataReadGroup(int aisleNum, int groupNum){
        short[] datas = dataRead(aisleNum, groupNum);
        short[][] result = new short[aisleNum][groupNum];
        for(int i = 0; i < datas.length; i++){
            result[i % aisleNum][i / aisleNum] = datas[i];
        }
        return result;
    }

    /**
     * 获取各个通道电压数据
     * @param aisleNum
     * @param groupNum
     * @return
     */
    public double[][] dataReadGroupVoltage(int aisleNum, int groupNum, int voltageRange){
        short[][] datas = dataReadGroup(aisleNum, groupNum);
        double[][] result = new double[aisleNum][groupNum];
        for(int i = 0; i < datas.length; i++){
            for(int j = 0; j < datas[i].length; j++){
                result[i][j] = datas[i][j] * (double)voltageRange / 0x7fff;
            }
        }
        return result;
    }

    /**
     * 获取电压的平均值
     * @param aisleNum
     * @param groupNum
     * @return
     */
    public double[] dataReadGroupVoltageAvg(int aisleNum, int groupNum, int voltageRange){
        double[][] datas = dataReadGroupVoltage(aisleNum, groupNum, voltageRange);
        double[] result = new double[aisleNum];
        for(int i = 0; i < datas.length; i++){
            double count = 0;
            for(int j = 0; j < datas[i].length; j++){
                count += datas[i][j];
            }
            result[i] = count / datas[i].length;
        }
        return result;
    }

    /**
     * 计算8421码
     * @param aisleNum 通道数
     * @return
     */
    private static short compute8421(int aisleNum) {
        short num = 0;
        for(int i = 0; i < aisleNum; i++){
            num += Math.pow(2, i);
        }
        return num;
    }
}

