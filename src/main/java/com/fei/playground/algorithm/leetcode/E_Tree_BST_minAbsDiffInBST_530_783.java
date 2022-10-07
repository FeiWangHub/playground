package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

/**
 * https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
 */
public class E_Tree_BST_minAbsDiffInBST_530_783 {

    TreeNode pre = null;
    int minDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        //手撸中序遍历
        dfs(root);
        return minDiff;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (pre != null) minDiff = Math.min(minDiff, Math.abs(node.val - pre.val));
        pre = node;
        dfs(node.right);
    }

}
