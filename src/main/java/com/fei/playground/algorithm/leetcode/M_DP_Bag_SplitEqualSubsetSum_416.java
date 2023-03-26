package com.fei.playground.algorithm.leetcode;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 * (解析完就是，本题要求集合里能否出现总和为 sum / 2 的子集。)
 */
public class M_DP_Bag_SplitEqualSubsetSum_416 {

    /**
     * 我的二位数组版本尝试
     * 1. dp[i][sum] 代表前i个物品，能否装满容积为sum的背包，true为可以
     * 2.
     */
    public boolean canPartition(int[] nums) {
        int[] dp = new int[10000 + 1];

        //找到目标target
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;

        // 开始01背包
        for (int i = 0; i < nums.length; i++) {//遍历物品
            for (int j = target; j >= nums[i]; j--) {//遍历背包size, 每一个元素一定是 不可重复放入，所以从大到小遍历
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[target] == target;// 集合中的元素正好可以凑成总和target
    }

    /**
     * carl的一位数组教程版本，时间N2，空间N
     * 1. dp[i]表示 背包(数字总和)总容量是i，最大可以凑成i的子集总和为dp[i]
     * 2. 递推公式 dp[j] = max(dp[j], dp[j - nums[i]] + nums[i])
     * 2.1 本题，相当于背包里放入数值，那么物品i的重量是nums[i]，其价值也是nums[i]。
     * 3 初始化: dp[0]一定是0; 每个数组中的元素不会超过 100，数组的大小不会超过 200; 总和不会大于20000，背包最大只需要其中一半，所以10001大小就可以了
     * 4. 遍历顺序: 同01背包问题，一维dp数组，物品遍历在外层，背包遍历在内层，切内层倒序
     * 5. 推导dp数组:
     */
    public boolean canPartition_carl_1dimension(int[] nums) {
        int[] dp = new int[10000 + 1];

        //找到目标target
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;

        // 开始01背包
        for (int i = 0; i < nums.length; i++) {//遍历物品
            for (int j = target; j >= nums[i]; j--) {//遍历背包size, 每一个元素一定是 不可重复放入，所以从大到小遍历
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[target] == target;// 集合中的元素正好可以凑成总和target
    }

}
