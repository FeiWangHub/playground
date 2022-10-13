package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

/**
 * 701. 二叉搜索树中的插入操作 (不用恐惧其重构搜索树，其实根本不用重构，找到合适的叶子节点就行了)
 * 给定二叉搜索树（BST）的根节点root和要插入树中的值value，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。
 * 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 * https://leetcode.cn/problems/insert-into-a-binary-search-tree/
 */
public class M_Tree_BST_InsertIntoBST_701 {

    /**
     * 评论精简版 100% 65%
     */
    public TreeNode insertIntoBST_best(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    /**
     * 手撕100% 51% 向下找位置就行了
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (null == root) return new TreeNode(val);

        if (root.val > val) {//go left
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insertIntoBST(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insertIntoBST(root.right, val);
            }
        }
        return root;
    }

}
