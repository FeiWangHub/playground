package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 *
 * int[] nums = new int[] {1,2,3,3,3};
 * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
 * solution.pick(3);
 * // pick(1) 应该返回 0。因为只有nums[0]等于1。
 * solution.pick(1);
 *
 * https://leetcode-cn.com/problems/random-pick-index/
 */
public class M_Random_index_398_TODO {

    //简单思路的Hashmap法
    Random rand = new Random();
    Map<Integer, List<Integer>> map = new HashMap<>();

    public void Solution_hp(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            List<Integer> lst = map.getOrDefault(nums[i], new ArrayList<>());
            lst.add(i);
            map.put(nums[i], lst);
        }
    }

    public int pick_hp(int target) {
        List<Integer> lst = map.get(target);
        int size = lst.size();
        return lst.get(rand.nextInt(size));
    }

    //TODO 水塘抽样法 学习下
    private int[] nums;
    public void Solution(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        Random r = new Random();
        int n = 0;
        int index = 0;
        for(int i = 0;i < nums.length;i++)
            if(nums[i] == target){
                //计算同一个target的个数
                n++;
                //我们以同一个数字的频数1/n的概率选出其中一个索引
                if(r.nextInt(n) == 0) index = i;
            }
        return index;
    }

}
