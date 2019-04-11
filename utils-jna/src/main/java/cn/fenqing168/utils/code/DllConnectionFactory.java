package cn.fenqing168.utils.code;

import cn.fenqing168.utils.bean.ConfigBean;

/**
 * 连接对象工厂
 */
public class DllConnectionFactory {

    private static ConfigBean configBean = ConfigBean.configBean;

    public static DllConnection createConnection(){
        DllConnection connection = new DllConnection(configBean.getFacilityIp(), configBean.getFacilityPort(),
                configBean.getLocalPort(), configBean.getType(), configBean.getOutTime(), configBean.getConnectionNum());
        return connection;
    }

}
