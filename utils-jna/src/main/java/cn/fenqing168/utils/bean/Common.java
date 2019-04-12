package cn.fenqing168.utils.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 公共类
 */
public class Common {

    /**
     * 时间格式化工具
     */
    public static DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    /**
     * 提示信息
     */
    public static String SUCCEED = "[成功]", ERROR = "[失败]", WARNING = "[警告]", HINT = "[提示]";

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTime(){
        return DF.format(new Date());
    }

    /**
     * 打印日志
     * @param status
     * @param msg
     */
    public static void printLog(String status, String msg){
        System.err.println(status + getNowTime() + ":" + msg );
    }

}
