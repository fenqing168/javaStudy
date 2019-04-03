package object_pool.cn.fenqing168.study.code.test;

import object_pool.cn.fenqing168.study.code.definite.ArrayDataObjectPool;
import object_pool.cn.fenqing168.study.code.interfaces.DataObjectPool;

public class DataObjectPoolTest {

    public static void main(String[] args) {

        DataObjectPool dataObjectPool = new ArrayDataObjectPool(-100, 100);
        Integer num1 = dataObjectPool.pack(600);
        Integer num2 = dataObjectPool.pack(600);
        System.out.println(num1 == num2);

    }

}
