package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

/**
 * 669. 修剪二叉搜索树
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
 * 修剪树 不应该改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在唯一的答案。
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 * https://leetcode.cn/problems/trim-a-binary-search-tree/
 * <p>
 * 我的思路:
 * 1.遍历每个节点
 * 2.遇上不在范围内的节点，返回删除它之后，应该传递给parent的子节点，有3种情况
 * 2.1 它没有子节点，返回null
 * 2.2 它只有左或者右，返回这点，并且跟着这个节点接着遍历删除
 * 2.3 它左右都有，返回它左边的最大，或者右边的最小
 */
public class M_Tree_BST_TrimBSTRange_669 {

    /**
     * 手撕一遍过 100% 50%
     * 传入一个根节点，返回如果这个节点被删除，应该被哪个节点替换
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        if (root.val >= low && root.val <= high) return root;

        //不合法，需要删除
        if (root.left != null && root.right == null) {
            return root.left;
        } else if (root.right != null && root.left == null) {
            return root.right;
        } else if (root.right == null && root.left == null) {
            return null;
        } else {//返回left max
            root.left.val = getLeftMaxAndDelete(root, root.left);
            return root;
        }
    }

    public int getLeftMaxAndDelete(TreeNode parent, TreeNode maxChild) {
        while (maxChild.right != null) {
            parent = maxChild;
            maxChild = maxChild.right;
        }
        parent.right = null;
        return maxChild.val;
    }

    /**
     * 官方精简递归100% 62% 时间N 空间N
     * 对于当前访问的结点，如果结点为空结点，直接返回空结点；
     * 如果结点的值小于low，那么说明该结点及它的左子树都不符合要求，我们返回对它的右结点进行修剪后的结果；
     * 如果结点的值大于high，那么说明该结点及它的右子树都不符合要求，我们返回对它的左子树进行修剪后的结果；
     * 如果结点的值位于区间[low,high],我们将结点的左结点设为对它的左子树修剪后的结果，右结点设为对它的右子树进行修剪后的结果。
     */
    public TreeNode trimBST_official_recursion(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    /**
     * 官方迭代 100% 90% 时间N 空间1
     * 主要是相对递归省空间
     */
    public TreeNode trimBST_official_loop(TreeNode root, int low, int high) {
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        if (root == null) {
            return null;
        }
        for (TreeNode node = root; node.left != null; ) {
            if (node.left.val < low) {
                node.left = node.left.right;
            } else {
                node = node.left;
            }
        }
        for (TreeNode node = root; node.right != null; ) {
            if (node.right.val > high) {
                node.right = node.right.left;
            } else {
                node = node.right;
            }
        }
        return root;
    }



}
