package cn.fenqing168.utils;

public class CmdDataBean {

    /**
     * 描述
     */
    private String describe;

    /**
     * 数据主体
     */
    private byte[] dataBody;

    /**
     * 构造函数
     * @param describe
     * @param dataBody
     */
    public CmdDataBean(String describe, byte[] dataBody) {
        this.describe = describe;
        this.dataBody = dataBody;
    }

    public String getDescribe() {
        return describe;
    }

    public byte[] getDataBody() {
        return dataBody;
    }
}
