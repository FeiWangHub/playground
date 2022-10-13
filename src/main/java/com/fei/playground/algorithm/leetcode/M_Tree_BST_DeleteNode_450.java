package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 * <p>
 * 先找到要删除的节点，然后
 * <p>
 * 3中情况
 * 1.没有子节点 直接删除
 * 2.只有左or右，直接用左or右替换
 * 3.左右都有，要保证删除完之后依然是BST的属性,把左子树最大/或右子树最小，拿来替换
 */
public class M_Tree_BST_DeleteNode_450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode lmax = getLMax(root.left);
            root.val = lmax.val;
            root.left = deleteNode(root.left, lmax.val);
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }

        return root;
    }

    private TreeNode getLMax(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

}
