package cn.fenqing168.demo;

import redis.clients.jedis.Jedis;

import java.util.*;

public class XXXXXXXXXXXXXUtil {

    private static String PUBLIC_KEY_NAME = "voltageList";

    /**
     * 通过时间查询数据
     * @param jedis redis客户端
     * @param start 开始时间
     * @param end   结束时间
     * @return
     */
    public static List<String> queryListByTimeScope(Jedis jedis, Date start, Date end){
        //十位数
        Set<String> ten = new HashSet<>();
        //个位
        Set<String> unit = new HashSet<>();

        long startTime = start.getTime() / 1000;

        long endTime = end.getTime() / 1000;

        for(long i = endTime; i >= startTime; i--){
            ten.add(String.valueOf(i / 10 % 10));
            unit.add(String.valueOf(i % 10));
        }

        //公共数字
        String publicNum = String.valueOf(startTime / 100);
        //十位数条件
        StringBuffer tenWhereSb = new StringBuffer();
        tenWhereSb.append("[");
        for(String temp : ten){
            tenWhereSb.append(temp);
        }
        tenWhereSb.append("]");

        //个位数条件
        StringBuffer unitWhereSb = new StringBuffer();
        unitWhereSb.append("[");
        for(String temp : unit){
            unitWhereSb.append(temp);
        }
        unitWhereSb.append("]");

        String cmd = PUBLIC_KEY_NAME + publicNum + tenWhereSb.toString() + unitWhereSb.toString();
        Set<String> keys = jedis.keys(cmd);
        List<String> values = new ArrayList<>();
        for(String key : keys){
            values.addAll(jedis.lrange(key, 0, -1));
        }
        return values;
    }

}
