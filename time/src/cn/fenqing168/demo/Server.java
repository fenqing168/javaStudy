package cn.fenqing168.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Socket socket = null;
        BufferedWriter writer = null;
        while(true){
            try {
                socket = serverSocket.accept();
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String date = df.format(new Date());
                System.out.println("发送时间" + date);
                writer.write(date);
                writer.flush();
                socket.shutdownOutput();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                writer.close();
                socket.close();
            }
        }


    }

}
