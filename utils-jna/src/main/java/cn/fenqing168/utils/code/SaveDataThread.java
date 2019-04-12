package cn.fenqing168.utils.code;

import cn.fenqing168.utils.bean.Common;

public class SaveDataThread implements Runnable {

    /**
     * 要保存的数据
     */
    private double[] datas;

    /**
     * 保存的逻辑代码模板
     */
    private SaveDataTemplate saveDataTemplate;

    /**
     * 创建该线程必须提供指定的对象
     * @param datas
     * @param saveDataTemplate
     */
    public SaveDataThread(double[] datas, SaveDataTemplate saveDataTemplate) {
        this.datas = datas;
        this.saveDataTemplate = saveDataTemplate;
    }

    /**
     * 开始存储
     */
    @Override
    public void run() {
        Common.printLog(Common.HINT, "异步保存数据开始");
        saveDataTemplate.saveData(datas);
    }
}
