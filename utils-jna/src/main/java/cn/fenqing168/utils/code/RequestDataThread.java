package cn.fenqing168.utils.code;

import cn.fenqing168.utils.bean.ConfigBean;

public class RequestDataThread implements Runnable {

    /**
     * 连接池对象
     */
    private DllConntectionPool dllConntectionPool;

    /**
     * 保存数据模板
     */
    private SaveDataTemplate saveDataTemplate;

    /**
     * 请求数据的模板
     */
    private RequestDataTemplate requestDataTemplate;


    public RequestDataThread(DllConntectionPool dllConntectionPool, SaveDataTemplate saveDataTemplate, RequestDataTemplate requestDataTemplate) {
        this.dllConntectionPool = dllConntectionPool;
        this.saveDataTemplate = saveDataTemplate;
        this.requestDataTemplate = requestDataTemplate;
    }

    @Override
    public void run() {
        while(true){
            DllConnection connection = dllConntectionPool.getConnection();
            saveDataTemplate.saveData(requestDataTemplate.request(connection));
            dllConntectionPool.close(connection);
        }
    }

    /**
     * 开始程序
     * @param dllConntectionPool
     * @param saveDataTemplatem
     * @param requestDataTemplate
     */
    public static void startApp(DllConntectionPool dllConntectionPool, SaveDataTemplate saveDataTemplatem, RequestDataTemplate requestDataTemplate){
        for(int i = 0; i < ConfigBean.configBean.getDataThread(); i++){
            new Thread(new RequestDataThread(dllConntectionPool, saveDataTemplatem, requestDataTemplate)).start();
        }
    }
}
