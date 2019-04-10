package cn.fenqing168.utils.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * 配置信息类
 */
public class ConfigBean {

    /**
     * 设备ip
     */
    private String facilityIp;

    /**
     * 设备端口
     */
    private int facilityPort;

    /**
     * 本地端口
     */
    private int localPort;

    /**
     * 连接类型
     */
    private int type;

    /**
     * 超时时间
     */
    private int outTime;

    /**
     * 连接数
     */
    private int connectionNum;

    /**
     * 连接池最小数量
     */
    private int minConnectionPool;

    /**
     * 连接池最大数量
     */
    private int maxConnectionPool;

    /**
     * 获取数据的线程数
     */
    private int dataThread;

    /**
     * 单例
     */
    public static ConfigBean configBean;

    static{
        System.out.println("【加载配置文件】");
        Class c = ConfigBean.class;
        ClassLoader classLoader = c.getClassLoader();
        URL u = classLoader.getResource("config/dll.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(u.getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        configBean = new ConfigBean();
        configBean.facilityIp = properties.getProperty("facilityIp");
        configBean.facilityPort = Integer.valueOf(properties.getProperty("facilityPort"));
        configBean.localPort = Integer.valueOf(properties.getProperty("localPort"));
        configBean.type = Integer.valueOf(properties.getProperty("type"));
        configBean.outTime = Integer.valueOf(properties.getProperty("outTime"));
        configBean.connectionNum = Integer.valueOf(properties.getProperty("connectionNum"));
        configBean.minConnectionPool = Integer.valueOf(properties.getProperty("minConnectionPool"));
        configBean.maxConnectionPool = Integer.valueOf(properties.getProperty("maxConnectionPool"));
        configBean.dataThread = Integer.valueOf(properties.getProperty("dataThread"));
    }

    public String getFacilityIp() {
        return facilityIp;
    }

    public int getFacilityPort() {
        return facilityPort;
    }

    public int getType() {
        return type;
    }

    public int getLocalPort() {
        return localPort;
    }

    public int getOutTime() {
        return outTime;
    }

    public int getConnectionNum() {
        return connectionNum;
    }

    public int getMinConnectionPool() {
        return minConnectionPool;
    }

    public int getMaxConnectionPool() {
        return maxConnectionPool;
    }

    public int getDataThread() {
        return dataThread;
    }
}
