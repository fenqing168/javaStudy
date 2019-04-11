package cn.fenqing168.utils.code;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        //开始程序
        //RequestDataThread.startApp(DllConntectionPool.getInstance(), new Save(), new Request());
        test02();
    }

    public static void test02(){
        DllConnection connection = new DllConnection("192.168.3.11", (short) 1600, (short)
                DllConnection.IDLE_PORT, DllConnection.CONN_TYPE_TCP, 1000, 3);
        //进行同步采集并开启采集操作
        connection.syncParaWrite(10000, 1, 3,  4,  (short) 0);
        //获取10组数据，（四个通道）
        //原始数据
        short[] datas1 = connection.dataRead(4, 10);
        System.out.println(Arrays.toString(datas1));
//        //原始分组数据
//        short[][] datas2 = connection.dataReadGroup(4, 10);
//        //计算成电压
//        double[][] datas3 = connection.dataReadGroupVoltage(4, 10, 10);
//        //计算成电压并求平均值
//        double[] datas4 = connection.dataReadGroupVoltageAvg(4, 10, 10);
//        System.out.println("平均值"+Arrays.toString(datas4));
    }

}
