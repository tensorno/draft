package model;
import java.util.*;
public class minchanges {
    public int minChanges(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < copy.length; i++) {
            map.put(copy[i], i);
        }
        boolean[] flag = new boolean[nums.length];  // 用于标记 nums[i] 是否已经被加入环中
        int loop = 0; // 环的个数
        for (int i = 0; i < nums.length; i++) {
            if (!flag[i]) {
                int j = i;
                while (!flag[j]) { // 画环
                    int index = map.get(nums[j]); // 当前节点指向的位置，画环过程
                    flag[j] = true; // 将 j 加入环中
                    j = index; // 将当前节点移动到环上下个节点
                }
                loop++; // 环数递增
            }
        }
        return nums.length - loop; // 最小交换次数为 ： 数组长度 - 环数
    }
}
