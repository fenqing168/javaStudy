package cn.fenqing168.code;

public class Runtime {

    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int size = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val){
                nums[size++] = nums[i];
            }
        }
        return size;
    }

    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {

        if(null == needle){
            return 0;
        }
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        int num = 0;
        for(int i = 0, j = i + n.length - 1; j < h.length; i++, j++){
            boolean falg = true;
            for(int z = i, temp = 0; z <= j; z++, temp++){
                if(h[z] != n[temp]){
                    falg = false;
                    break;
                }
            }
            if(falg){
                return i;
            }
        }
        return -1;
    }

    /**
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if(x < 2){
            return x;
        }
        if(x / 2 < 2){
            return 1;
        }
        int last = 0;
        int f = x / 2;
        for(int i = 2; i <= f; i++){
            int num = i * i;
            if(num > x || num < 0){
                return last;
            }
            if(num == x){
                return i;
            }
            last = i;
        }
        return last;
    }

}
