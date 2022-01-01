package com.fei.playground.algorithm.leetcode;

/**
 * https://www.jianshu.com/p/b5beadd9dab9
 */
public class E_TreeBSTSearchIn {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if(root.val == val){
            return root;
        }else if(root.val > val){
            return searchBST(root.left, val);
        }else{
            return searchBST(root.right, val);
        }
    }
}
