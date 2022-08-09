package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 前序遍历 https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * 中序遍历 https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * 后序遍历 https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class E_Tree_Pre_Middle_Post_Travel_94_145 {

    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        travel_middle(root, res);
//        travel_pre(root, res);
//        travel_post(root, res);
        return res;
    }

    /**
     * 前序遍历 https://leetcode.cn/problems/binary-tree-preorder-traversal/
     */
    public void travel_pre(TreeNode root, LinkedList<Integer> res) {
        if (root == null) return;
        travel_pre(root.left, res);
        res.add(root.val);
        travel_pre(root.right, res);
    }

    /**
     * 中序遍历 https://leetcode.cn/problems/binary-tree-inorder-traversal/
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     */
    public void travel_middle(TreeNode root, LinkedList<Integer> res) {
        if (root == null) return;
        travel_middle(root.left, res);
        res.add(root.val);
        travel_middle(root.right, res);
    }

    /**
     * 后序遍历 https://leetcode.cn/problems/binary-tree-postorder-traversal/
     */
    public void travel_post(TreeNode root, LinkedList<Integer> res) {
        if (root == null) return;
        travel_post(root.left, res);
        travel_post(root.right, res);
        res.add(root.val);
    }
}
