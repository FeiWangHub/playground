package com.fei.playground.algorithm.leetcode;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 */
public class M_Tree_BST_isPostOrderOfBinarySearchTree_offer33_TODO {

    /**
     * TODO 需要自己手写下
     * TODO 利用特点：1.最后数字是根节点 2.左边连续比根小的是左子树 3.右子树不应该有比根节点大的
     *
     * 评论区100% 75% 分区左右子树
     * - 指导思想：二叉树问题一定可以用递归解决
     * - 不合格的后序遍历一定是在根节点的左侧出现了比根节点大的数，或者在根节点的右侧出现了比根节点小的树
     * - 由于后序遍历的顺序是左右根，根节点位于序列的最后一位，然后根据根节点的值将序列下标（0，n-2）的结点分成左右子树序列
     * - 所以我们采取分治的思想，将一个序列划为左右子树的两个子序列，对每一个子序列进行判断
     */
    public boolean verifyPostorder_comment(int[] postorder) {
        int n = postorder.length;
        return dfs_2(postorder, 0, n - 1);
    }

    //left表示左子序列起始位置下标，root表示根节点所在下标
    private boolean dfs_2(int[] postorder, int left, int root) {
        if (left >= root) return true;
        int right = left, i = left;
        //寻找右节点
        while (i < root && postorder[i] < postorder[root]) i++;
        right = i;
        // 检查右子树有没有比根节点小的
        for (i += 1; i < root; i++) {
            if (postorder[i] < postorder[root]) return false;
        }
        //划分为左右两个子序列
        boolean l = dfs_2(postorder, left, right - 1);
        boolean r = dfs_2(postorder, right, root - 1);
        return l && r;
    }

}
