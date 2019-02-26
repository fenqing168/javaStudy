package liner_search.cn.fenqing168.study.code;

/**
 * 二分查找
 * @author fenqing
 */
public class Test03 {

    public static void main(String[] args) {

        //准备数据
        int[] nums = {1, 2, 3, 5, 6, 7, 89, 100};

        //准备待查找的值
        int num = 100;

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
     * 递归
     * 二分查找
     * 需排序
     * T(n) = O(log2 n)
     * S(n) = S(log2 n)
     * @param nums
     * @param num
     * @return
     */
    public static int search(int[] nums, int num){
        int t = 0;
        int w = nums.length - 1;
        return search(nums, num, t, w);
    }

    private static int search(int[] nums, int num, int t, int w){
        int z = (t + w) >> 1;
        int tmp = nums[z];
        if(t > w){
            return -1;
        }else if(tmp == num){
            return z;
        }else if(tmp > num){
            return search(nums, num, t, z - 1);
        }else{
            return search(nums, num, z + 1, w);
        }
    }

}
