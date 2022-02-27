package com.fei.playground.algorithm.leetcode;

/**
 * 思路跟搜索一样
 */
public class E_TreeInsertInTree {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        if(root.val == val){
            return root;
        }else if(root.val < val){
            root.right = insertIntoBST(root.right, val);
        }else{
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }

}
