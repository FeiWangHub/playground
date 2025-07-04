package com.fei.playground.algorithm.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139 单词拆分 TODO 需要手写练习
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * https://leetcode.cn/problems/word-break/
 * <p>
 * 网友分析：https://blog.csdn.net/weixin_30700977/article/details/96740961
 * 设dp[i]代表s.substring(0, i)能否被字典表达，此刻我们知道dp[0]~dp[i-1]的结果。
 * 而dp[i]的结果由两部分组成，一部分是dp[j]（j < i)，已知；另一部分是j到i之间的字符串是不是在字典里。
 * 当这两个部分都为真的时候，dp[i]即为真。而一旦dp[i]为真，就不用继续迭代了。
 *
 * DP解题模板
 * 1. dp数组以及下标的含义:  dp[i]代表s[0-i]能否被字典词代表
 * 2. 递推公式:  dp[i]=dp[j] && check(s[j..i−1])
 * 3. dp数组如何初始化
 * 4. 遍历顺序（2个循环如何嵌套？从前向后，还是反方向？）
 * 5. 打印dp数组
 */
public class M_DP_String_Dict_WordBreak_139_TODO {

    /**
     * 官方DP 56.56% 50.01%
     * 时间n2平方，空间n
     * n为字符串长度，总共有o(n)个状态需要计算，每次需要枚举O(n)个分割点
     * 需要n的空间存放dp和哈希表
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {//遍历前边已经走过的，j到i之间字符
                if (dp[j] && dictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
