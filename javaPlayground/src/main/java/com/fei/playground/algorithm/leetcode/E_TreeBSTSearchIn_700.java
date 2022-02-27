package com.fei.playground.algorithm.leetcode;

/**
 * 二叉搜索树中的搜索
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * https://www.jianshu.com/p/b5beadd9dab9
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 */
public class E_TreeBSTSearchIn_700 {

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
