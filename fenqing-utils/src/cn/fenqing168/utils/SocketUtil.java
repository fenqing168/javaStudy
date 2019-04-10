package cn.fenqing168.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class SocketUtil {

    private String host;

    private int port;

    private Socket socket;

    private OutputStream os;

    private InputStream is;

    public SocketUtil(String host, int port){
        this.host = host;
        this.port = port;
        try {
            socket = new Socket(host, port);
            os = socket.getOutputStream();
            is = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送并响应
     * @param data
     * @return
     */
    public byte[] sendData(byte[] data){
        try {
            os.write(data);
            os.flush();
            return readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("请求失败");
    }

    /**
     * 发送数据，并不读取响应
     * @param data
     */
    public void sendData2(byte[] data, boolean isFinish){
        try {
            os.write(data);
            if(isFinish){
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("请求失败");
    }

    /**
     * 读取数据
     * @return
     */
    public byte[] readData(){
        try {
            List<Byte> byteList = new ArrayList<>();
            byte temp;
            while ((temp = (byte) is.read()) != -1){
                byteList.add(temp);
                if(is.available()<=0){
                    break;
                }

            }
            byte[] result = new byte[byteList.size()];
            for(int i = 0; i < result.length; i++){
                result[i] = byteList.get(i);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("读取数据失败");
    }

    /**
     * 只限于采集卡的指令
     * @return
     */
    public byte[] sendDataByBean(CmdDataBean cmdData){
        String d = cmdData.getDescribe() + "\r\n";
        sendData2(d.getBytes(), false);
        sendData2(cmdData.getDataBody(), true);
        return readData();
    }

}
