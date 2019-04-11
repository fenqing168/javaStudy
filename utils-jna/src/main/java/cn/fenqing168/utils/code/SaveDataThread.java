package cn.fenqing168.utils.code;

import cn.fenqing168.utils.bean.ConfigBean;

public class SaveDataThread implements Runnable {

    private double[] datas;

    private SaveDataTemplate saveDataTemplate;

    public SaveDataThread(double[] datas, SaveDataTemplate saveDataTemplate) {
        this.datas = datas;
        this.saveDataTemplate = saveDataTemplate;
    }

    @Override
    public void run() {
        saveDataTemplate.saveData(datas);
    }
}
