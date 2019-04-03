package cn.fenqing168.utils;

import sun.rmi.runtime.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Crc16Util {

    /**
     * 计算CRC16校验码带空格
     * @param bytes
     * @return
     */
    public static String getCRCBlank(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return getCrc(CRC);
    }


    /**
     * 返回在原有的数组后面追加校验码的数组
     * @param bytes
     * @return
     */
    public static byte[] getCRCPackage(byte[] bytes) {
        int length = bytes.length + 2;
        byte[] result = Arrays.copyOf(bytes, bytes.length + 2);
        String temp = getCRCBlank(bytes);
        String[] items = temp.split(" ");
        result[length - 2] = (byte)Integer.parseInt(items[1], 16);
        result[length - 1] = (byte)Integer.parseInt(items[0], 16);
        return result;
    }

    /**
     * 将计算的CRC值 转换为加空格的  比如  ： crc值为 A30A -> A3 0A
     * @param res   十进制的整数
     * @return
     */
    public static String getCrc(int res) {
        String format = String.format("%04x", res);
        String substring = format.substring(0, 2);
        String substring1 = format.substring(2, 4);
        return substring.concat(" ").concat(substring1).concat(" ");
    }

    /**
     * 将计算的CRC值 转换为加空格的  比如  ： crc值为 A30A -> A3 0A
     * @param hexadecimal 16进制的字符串
     * @return
     */
    public static String getCrc(String hexadecimal) {
        String format = String.format("%04x", Integer.parseInt(hexadecimal, 16));
        String substring = format.substring(0, 2);
        String substring1 = format.substring(2, 4);
        return substring.concat(" ").concat(substring1).concat(" ");
    }

    /**
     * 数组转成十六进制字符串
     * @param
     * @return HexString
     */
    public static String toHexString1(byte[] b){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i){
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString().concat(" ");
    }
    public static String toHexString1(byte b){
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1){
            return "0" + s.concat(" ");
        }else{
            return s.concat(" ");
        }
    }
    public static void main(String[] args)throws Exception {
        DecimalFormat df = new DecimalFormat("0.000");//保留三位小数
        df.setRoundingMode(RoundingMode.HALF_UP);



        byte[]voltageByte=new byte[]{0x11,0x03,0x00,0x58,0x00,0x01};//电压指令byte数组
        byte[]newvoltageByte=getCRCPackage(voltageByte);
        byte[]temperatureByte=new byte[]{0x11,0x04,0x00,0x50,0x00,0x01};//温度指令byte数组
        byte[]newtemperatureByte=getCRCPackage(temperatureByte);

        String str1=toHexString1(newvoltageByte);
        //System.out.println("电压请求发送转换成16进制"+str1);//字节数组打印
       // System.out.println("电压请求发送10进制"+Arrays.toString(newvoltageByte));//字节数组打印
        SocketUtil socketUtil=new SocketUtil("192.168.3.10", 28899);
        long y=System.currentTimeMillis();//开始执行时间
        byte[]bytes1=socketUtil.sendData(newvoltageByte);
        //System.out.println("电压请求返回值16进制===="+toHexString1(bytes1));
        //System.out.println("电压返回值返回值10进制"+Arrays.toString(bytes1));
        System.out.println("当前电压：" + ModbusTcpDataUtil.getVoltage(bytes1)+"V");
        long y1=System.currentTimeMillis();
        System.out.println("电压返回结果相差"+String.valueOf(y1-y));
        String str2=toHexString1(newtemperatureByte);
       // System.out.println("温度请求发送转换成16进制"+str2);//字节数组打印
       // System.out.println("温度请求发送10进制"+Arrays.toString(newvoltageByte));//字节数组打印
        y=System.currentTimeMillis();
        byte[]bytes2=socketUtil.sendData(newtemperatureByte);
       // System.out.println("温度请求返回值16进制===="+toHexString1(bytes1));
        //System.out.println("温度返回值返回值10进制"+Arrays.toString(bytes1));
        System.out.println("当前温度：" + ModbusTcpDataUtil.getTemperature(bytes2)+"℃");
        y1=System.currentTimeMillis();
        System.out.println("温度返回结果相差"+String.valueOf(y1-y));
        double averageValue=0;
        for (int i=0;i<=3;i++){
           byte[]bytes=socketUtil.sendData(newvoltageByte);
           System.out.println(ModbusTcpDataUtil.getVoltage(bytes));
           averageValue+=ModbusTcpDataUtil.getVoltage(bytes);
           }
           System.out.println("累加电压"+averageValue);
           System.out.println("取电压平均值"+df.format(averageValue/4));
    }
}
