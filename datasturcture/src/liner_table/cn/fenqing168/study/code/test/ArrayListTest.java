package liner_table.cn.fenqing168.study.code.test;

import liner_table.cn.fenqing168.study.code.definite.ArrayList;
import liner_table.cn.fenqing168.study.code.interfaces.List;

/**
 * ArrayList测试类
 */
public class ArrayListTest {

    public static void main(String[] args) {

        List list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(789);
        list.add(234);
        list.add(345);
        list.add(1, 555);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list);

    }

}
