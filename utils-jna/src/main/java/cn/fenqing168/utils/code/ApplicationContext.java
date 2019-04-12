package cn.fenqing168.utils.code;

import cn.fenqing168.utils.bean.Common;

/**
 * 应用程序的核心
 */
public class ApplicationContext {

    private RequestDataTemplate requestDataTemplate;

    private SaveDataTemplate saveDataTemplate;

    public ApplicationContext(RequestDataTemplate requestDataTemplate, SaveDataTemplate saveDataTemplate) {
        this.requestDataTemplate = requestDataTemplate;
        this.saveDataTemplate = saveDataTemplate;
    }

    /**
     * 开始程序，启动死循环，不断读取数据，获取数据后开启线程保存数据，不影响继续读取
     */
    public void start() {
        while (true) {
            DllConnection connection = DllConnectionFactory.createConnection();
            if (connection.getConnectIndex() >= 0) {
                Common.printLog(Common.HINT, "开始获取数据");
                double[] datas = requestDataTemplate.request(connection);
                saveDataTemplate.saveData(datas);
                connection.close();
            }
        }
    }
}
