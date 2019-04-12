package cn.fenqing168.utils.code;

import cn.fenqing168.utils.bean.Common;

import javax.xml.ws.soap.Addressing;

/**
 * 应用程序的核心
 */
public class ApplicationContext {

    private DllConnection connection;

    private RequestDataTemplate requestDataTemplate;

    private SaveDataTemplate saveDataTemplate;

    public ApplicationContext(DllConnection connection, RequestDataTemplate requestDataTemplate, SaveDataTemplate saveDataTemplate) {
        this.connection = connection;
        this.requestDataTemplate = requestDataTemplate;
        this.saveDataTemplate = saveDataTemplate;
    }

    /**
     * 开始程序，启动死循环，不断读取数据，获取数据后开启线程保存数据，不影响继续读取
     */
    public void start(){
        if(connection.getConnectIndex() >= 0){
            while(true){
                Common.printLog(Common.HINT, "开始获取数据");
                double[] datas = requestDataTemplate.request(connection);
                new Thread(new SaveDataThread(datas, saveDataTemplate)).start();
            }
        }else{
            Common.printLog(Common.ERROR, "连接失效，启动失败！");
        }

    }
}
