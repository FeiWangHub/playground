package com.fei.playground.algorithm.leetcode;

/**
 * 判断当前是否是valid BST
 * 原则是，遍历时，结果应该是递增的
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class M_TreeBSTIsValid98 {

    Integer max = null;
    boolean isValid = true;

    public boolean isValidBST(TreeNode root) {
        travel(root);
        return isValid;
    }

    /**
     * 我的解法
     */
    public void travel(TreeNode root) {
        if (root == null) return;

        //left
        travel(root.left);

        //self
        if (max == null) {
            max = root.val;
        } else if (root.val <= max) {
            isValid = false;
            return;
        } else {
            max = root.val;
        }

        //right
        travel(root.right);
    }

    //官方解法
    boolean isValidBSTBetter(TreeNode node){
        return hlp(node, null, null);
    }
    // 辅助函数hlp
    boolean hlp(TreeNode node, TreeNode min, TreeNode max){
        if(node == null) return true;
        if(min != null && node.val < min.val) return false;
        if(max != null && node.val > max.val) return false;

        return hlp(node.left, min, node) && hlp(node.right, node, max);
    }

}
