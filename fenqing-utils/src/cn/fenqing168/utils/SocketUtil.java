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

    public byte[] sendData(byte[] data){
        try {
            os.write(data);
            os.flush();

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
        throw new RuntimeException("请求失败");
    }

}
