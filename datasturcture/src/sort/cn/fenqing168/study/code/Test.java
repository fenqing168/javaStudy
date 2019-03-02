package sort.cn.fenqing168.study.code;

import java.util.Arrays;
import java.util.Random;

/**
 * @author fenqing
 */
public class Test {

    public static void main(String[] args) {

        //准备数据
        int[] nums = new int[100];
        for(int i = 0; i < nums.length; i++){
            nums[i] = new Random().nextInt();
        }

        //输出排序前的数据
        System.out.println(Arrays.toString(nums));

        //排序
        sort(nums);

        //输出排序后的数据
        System.out.println(Arrays.toString(nums));

    }

    private static void sort(int[] nums) {

        //获取起始点和结束点
        int s = 0;
        int e = nums.length - 1;

        //开始排序
        sort(nums, s, e);
    }

    private static void sort(int[] nums, int s, int e) {

        if(s > e){
            return;
        }
        //开始分区
        int index = partition(nums, s, e);

        //将左边的再次分区
        sort(nums, s, index - 1);
        //将右边的再次分区
        sort(nums, index + 1, e);
    }

    private static int partition(int[] nums, int s, int e) {

        //获取基准值
        int num = nums[s];

        //开始分区
        boolean falg = true;
        while(s < e){
            //移动右边
            if(falg){
                if(num >= nums[e]){
                    nums[s] = nums[e];
                    s++;
                    falg = !falg;
                }else{
                    e--;
                }
            }else{//移动左边
                if(num < nums[s]){
                    nums[e] = nums[s];
                    e--;
                    falg = !falg;
                }else{
                    s++;
                }
            }
        }
        nums[s] = num;

        return s;

    }

}
