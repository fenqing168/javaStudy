package stark_queue.cn.fenqing168.study.code.test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 测试栈
 */
public class StackTest {

    public static void main(String[] args) {
        System.out.println(decimaclismToBin(9555566));
    }

    /**
     * 十进制转二进制方法
     * @param decimaclismNum  十进制数字
     * @return
     */
    public static String decimaclismToBin(int decimaclismNum){

        Deque<Integer> stack = new LinkedList<>();
        do{
            int num = decimaclismNum % 2;
            stack.push(num);
            decimaclismNum = decimaclismNum / 2;
        }while(decimaclismNum > 0);
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

}
