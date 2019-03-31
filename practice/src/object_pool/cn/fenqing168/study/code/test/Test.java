package object_pool.cn.fenqing168.study.code.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fenqing
 */
public class Test {

    public static void main(String[] args) {

        test01();

    }

    /**
     * 测试HashMapkey为整形和字符型有顺序问题
     */
    public static void test01(){

        Map<Integer, Object> map = new HashMap<>(4);

        //随意扩容
        for(int i = 1; i <= 100; i++){
                map.put(i, "v" + i);
        }

        System.out.println("扩容数倍后：");
        for(Integer key : map.keySet()){
            System.out.println("k:" + key + ", v:" + map.get(key));
        }

    }

}
