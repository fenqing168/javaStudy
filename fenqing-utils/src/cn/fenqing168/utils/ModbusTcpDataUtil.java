package cn.fenqing168.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用于读取ModbusTCP协议数据
 * @author fenqing
 */
public class ModbusTcpDataUtil {

    /**
     * 工具类，不允许创建实例
     */
    private ModbusTcpDataUtil(){}

    /**
     * 读取数据区的数据 既11 03 02 10 00 74 47 读取数据区 1000
     * @param data
     * @return
     */
    public static long readData(byte[] data){
        int minLength = 5;
        if(data.length <= minLength){
            throw new RuntimeException("参数不正确");
        }
        //开始位置，固定为3
        int start = 3;
        int end = data.length - 2;
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < end; i++){
            String temp = Crc16Util.toHexString1(data[i]).replaceAll(" ", "");
            if(temp.length() <= 1){
                temp = "0" + temp;
            }
            sb.append(temp);
        }
        long result = Long.parseLong(sb.toString(), 16);
        return result;
    }

    /**
     * 获取电压
     * @param data
     * @return
     */
    public static double getVoltage(byte[] data){
        long result = readData(data);
        return result / 1000.0;
    }

    /**
     * 获取温度
     * @param data
     * @return
     */
    public static double getTemperature(byte[] data){
        long result = readData(data);
        return (result-10000) / 100.0;
    }

    public static void main(String[] args) {
       System.out.println(getVoltage(new byte[]{0x11, 0x03, 0x02, 0x10, 0x00, 0x74, 0x47}));
    }
}
