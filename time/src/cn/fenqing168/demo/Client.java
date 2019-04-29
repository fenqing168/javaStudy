package cn.fenqing168.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            Socket socket = null;
            BufferedReader reader = null;
            System.out.print("回车立刻同步时间");
            scanner.nextLine();
            try{
                socket = new Socket("127.0.0.1", 9999);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String date = reader.readLine();
                String[] dates = date.split(" ");
                String cmd;
                // 格式：yyyy-MM-dd
                cmd = " cmd /c date " + dates[0];
                Runtime.getRuntime().exec(cmd);
                // 格式 HH:mm:ss
                cmd = " cmd /c time " + dates[1];
                Runtime.getRuntime().exec(cmd);
                System.out.println("windows 时间修改" + date);
            }catch (Exception e){

            }finally {
                reader.close();
                socket.close();
            }


        }
    }

}
