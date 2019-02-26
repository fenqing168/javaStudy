package liner_search.cn.fenqing168.study.code;

import java.sql.SQLOutput;

/**
 * 遍历查找
 * @author fenqing
 */
public class Test01 {

    public static void main(String[] args) {

        //准备数据
        int[] nums = {1, 2, 3, 5, 6, 7, 89, 1};

        //准备待查找的值
        int num = 1212;

        //准备下标变量
        int index = search(nums, num);



        //输出结果
        if(index < 0){
            System.out.println("不存在");
        }else{
            System.out.println(num + "的下标为：" + index);
        }

    }

    /**
     * 遍历查找
     * 无需排序
     * T(n) = O(n)
     * S(n) = S(1)
     * @param nums
     * @param num
     * @return
     */
    public static int search(int[] nums, int num){
        //开始查找
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == num){
                return i;
            }
        }
        return -1;
    }

}
