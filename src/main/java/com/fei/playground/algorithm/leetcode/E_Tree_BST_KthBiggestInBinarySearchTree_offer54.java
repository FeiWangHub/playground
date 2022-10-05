package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

/**
 * 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 */
public class E_Tree_BST_KthBiggestInBinarySearchTree_offer54 {

    int res, k;

    /**
     * 手撕 100% 86.4%
     */
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        midTravel(root);
        return res;
    }

    public void midTravel(TreeNode node) {
        if (node == null || this.k == 0) return;

        midTravel(node.right);
        if (--this.k == 0) {
            res = node.val;
            return;
        }
        midTravel(node.left);
    }

}
