package cn.fenqing168.beans;

import cn.fenqing168.utils.ModbusTcpDataUtil;

import java.util.*;

/**
 * 数据池
 */
public class DataPool {

    private List<byte[]> bufferData;

    private int averageNode;

    private Map<Date, Double> data;

    public DataPool(int averageNode) {
        this.bufferData = new ArrayList<>();
        this.averageNode = averageNode;
        this.data = new HashMap<>();
    }

    public synchronized void addData(byte[] data){
        bufferData.add(data);
        if(bufferData.size() >= averageNode){
            double sum = 0;
            for(byte[] dataTemp : bufferData){
                long mtdTemp = ModbusTcpDataUtil.readData(dataTemp);
                sum += mtdTemp;
            }
            double svg = sum / averageNode;
            this.data.put(new Date(), svg);
            bufferData.clear();
        }
    }

    public synchronized Map<Date, Double> getData(){
        return data;
    }
}
