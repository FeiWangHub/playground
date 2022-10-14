package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * (也可使使用层序遍历解法)
 * https://leetcode.cn/problems/symmetric-tree/
 */
public class E_Tree_isSymmetric_101 {

    /**
     * 100% 96%
     * def 函数A（左树，右树）： 左树节点值等于右树节点值 且 函数A（左树的左子树，右树的右子树），函数A（左树的右子树，右树的左子树）均为真 才返回真
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymme(root.left, root.right);
    }

    public boolean isSymme(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if ((left != null && right == null) || (left == null && right != null)) {
            return false;
        }
        if (left.val != right.val) return false;

        return isSymme(left.left, right.right) && isSymme(left.right, right.left);
    }

}
