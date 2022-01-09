package com.fei.playground.algorithm;

import com.fei.playground.algorithm.leetcode.M_BackTrack_Permutation46;
import com.fei.playground.algorithm.leetcode.M_BackTrack_PermutationII47;

/**
 * 各种算法思路
 */
public class AlgoMindsetCollection {

    /**
     * 贪心算法思路
     * 贪心算法（又称贪婪算法）是指，在对问题求解时，总是做出在当前看来是最好的选择。也就是说，不从整体最优上加以考虑，算法得到的是在某种意义上的局部最优解
     * https://baike.baidu.com/item/贪心算法/5411800
     *
     * 1、有一个以最优方式来解决的问题。为了构造问题的解决方案，有一个候选的对象的集合：比如不同面值的硬币 [3]  。
     * 2、随着算法的进行，将积累起其他两个集合：一个包含已经被考虑过并被选出的候选对象，另一个包含已经被考虑过但被丢弃的候选对象 [3]  。
     * 3、有一个函数来检查一个候选对象的集合是否提供了问题的解答。该函数不考虑此时的解决方法是否最优 [3]  。
     * 4、还有一个函数检查是否一个候选对象的集合是可行的，即是否可能往该集合上添加更多的候选对象以获得一个解。和上一个函数一样，此时不考虑解决方法的最优性 [3]  。
     * 5、选择函数可以指出哪一个剩余的候选对象最有希望构成问题的解 [3]  。
     * 6、最后，目标函数给出解的值 [3]  。
     *
     * 例如，平时购物找零钱时，为使找回的零钱的硬币数最少，不要求找零钱的所有方案，而是从最大面值的币种开始，按递减的顺序考虑各面额，先尽量用大面值的面额，当不足大面值时才去考虑下一个较小面值，这就是贪心算法
     */
    public void GreedyAlgo(){

    }

    /**
     * 动态规划 求解决策过程最优化的过程
     *
     * 动规五部曲分别为： https://github.com/youngyangyang04/leetcode-master/blob/master/problems/动态规划总结篇.md
     * 1确定dp数组（dp table）以及下标的含义
     * 2确定递推公式
     * 3dp数组如何初始化
     * 4确定遍历顺序
     * 5举例推导dp数组
     *
     * https://www.jianshu.com/p/e8da6139f84b?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
     * 动态规划问世以来，在经济管理、生产调度、工程技术和最优控制等方面得到了广泛的应用。例如最短路线、库存管理、资源分配、设备更新、排序、装载等问题，用动态规划方法比用其它方法求解更为方便
     * 动态规划算法通常用于求解具有某种最优性质的问题。在这类问题中，可能会有许多可行解。每一个解都对应于一个值，我们希望找到具有最优值的解。动态规划算法与分治法类似，其基本思想也是将待求解问题分解成若干个子问题，先求解子问题，然后从这些子问题的解得到原问题的解。
     * 如果我们能够保存已解决的子问题的答案，而在需要时再找出已求得的答案，这样就可以避免大量的重复计算，节省时间。
     * !!!!我们可以用一个表来记录所有已解的子问题的答案。不管该子问题以后是否被用到，只要它被计算过，就将其结果填入表中。这就是动态规划法的基本思路。具体的动态规划算法多种多样，但它们具有相同的填表格式
     *
     */
    public void DP_DynamicProgramming(){
//        M_DP_MaxArraySum test = new M_DP_MaxArraySum();
//        M_DP_Abbrevation ab = new M_DP_Abbrevation();
    }

    /**
     * 回溯 ：原始状态下走完分支之后，重置状态到原始状态重新走下一个分支
     *
     * 思路模型：
     * 1.Base Case 就是出口(叶子节点)的处理逻辑 一般回溯似乎都是DFS，最先执行叶子节点
     * 2.对于每一个可能性的case
     *   2.1 记住当下state
     *   2.2 backtrack(next_state)
     *   2.3 restore current state
     */
    public void Backtracking(){
//        M_BackTrack_Permutation46 t = new M_BackTrack_Permutation46();
//        M_BackTrack_PermutationII47 t = new M_BackTrack_PermutationII47();
    }

    public static void main(String[] args) {
    }

}
