package cn.fenqing168.utils.code;

import cn.fenqing168.utils.bean.ConfigBean;

/**
 * 连接对象工厂
 */
public class DllConnectionFactory {

    /**
     * 配置信息
     */
    private static ConfigBean configBean = ConfigBean.configBean;

    /**
     * 创建连接
     * @return
     */
    public static DllConnection createConnection(){
        DllConnection connection = new DllConnection(configBean.getFacilityIp(), configBean.getFacilityPort(),
                configBean.getLocalPort(), configBean.getType(), configBean.getOutTime(), configBean.getConnectionNum());
        return connection;
    }

}
