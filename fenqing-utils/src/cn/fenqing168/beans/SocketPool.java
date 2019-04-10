package cn.fenqing168.beans;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 连接池
 * 多个连接重复使用
 */
public class SocketPool {

    private int minComment;

    private int maxComment;

    private String ip;

    private int port;

    private Queue<Socket> socketPool;

    private static SocketPool SOCKET_POOL;

    private SocketPool(int minComment, int maxComment, String ip, int port){
        socketPool = new LinkedList<>();
        this.minComment = minComment;
        this.maxComment = maxComment;
        this.ip = ip;
        this.port = port;
        for(int i = 0; i < minComment; i++){
            try {
                socketPool.add(new Socket(ip, port));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static SocketPool getSocketPoolInstance(int minComment, int maxComment, String ip, int port){
        if (SOCKET_POOL != null){
            synchronized (SocketPool.class){
                if(SOCKET_POOL != null){
                    SOCKET_POOL = new SocketPool(minComment, maxComment, ip, port);
                }
            }
        }
        return SOCKET_POOL;
    }

    public synchronized Socket getConnect(){
        Socket socket = null;
        try {
            if(socketPool.isEmpty()){
                socket = new Socket(ip, port);
            }else{
                socket = socketPool.peek();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    public synchronized void close(Socket socket){
        if(socketPool.size() < maxComment){
            socketPool.add(socket);
        }else{
            try {
                socket.getOutputStream().close();
                socket.getInputStream().close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
