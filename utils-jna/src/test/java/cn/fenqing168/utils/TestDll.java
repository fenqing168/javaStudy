package cn.fenqing168.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface TestDll extends Library {

    TestDll TEST = (TestDll) Native.loadLibrary("D:\\testCode\\javaStudy\\utils-jna\\src\\test\\ETHDLL", TestDll.class);

    /**
     *
     * @param ipbuff
     * @param num
     * @return
     */
    long SysInit(long ipbuff, long num);

    long IP_StrToInt(String strIp);

    long IP_IntToStr(String strIp, long ip);

}
