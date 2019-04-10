package cn.fenqing168.utils.code;

import cn.fenqing168.utils.bean.ConfigBean;
import sun.security.jca.GetInstance;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 连接池对象，用于生产链接对象
 *
 */
public class DllConntectionPool {

    /**
     * 拿到配置信息
     */
    private ConfigBean configBean = ConfigBean.configBean;

    /**
     * 单例模式
     */
    private static DllConntectionPool dllConntectionPool;

    /**
     * 池对象
     */
    private Queue<DllConnection> pool = new LinkedList<>();

    /**
     * 私有构造函数
     */
    private DllConntectionPool(){
        int minPool = configBean.getMinConnectionPool();
        for(int i = 0; i < minPool; i++){
            createConnection();
        }
    }

    /**
     * 获取实例方法
     * 采取双重检索方式，保证延时加载的同时，也保证了单例
     * 因为创建实例会创建数个连接，比较耗时，所以在使用时在连接
     * @return
     */
    public static DllConntectionPool getInstance(){
        if(dllConntectionPool == null){
            synchronized (DllConntectionPool.class){
                if(dllConntectionPool == null){
                    dllConntectionPool = new DllConntectionPool();
                }
            }

        }
        return dllConntectionPool;
    }

    /**
     * 创建一个连接
     */
    public void createConnection(){
        DllConnection dllConnection = new DllConnection(configBean.getFacilityIp(),
                configBean.getFacilityPort(), configBean.getLocalPort() , configBean.getType(),
                configBean.getOutTime(), configBean.getConnectionNum());
        pool.add(dllConnection);
    }

    /**
     * 获取连接
     * @return
     */
    public synchronized DllConnection getConnection(){
        if(pool.isEmpty()){
            createConnection();
        }
        DllConnection dllConnection = pool.poll();
        return dllConnection;
    }

    /**
     * 关闭连接
     * @param connection
     * @return
     */
    public synchronized void close(DllConnection connection){
        if(pool.size() >= configBean.getMaxConnectionPool()){
            connection.close();
        }else{
            pool.add(connection);
        }
    }
}
