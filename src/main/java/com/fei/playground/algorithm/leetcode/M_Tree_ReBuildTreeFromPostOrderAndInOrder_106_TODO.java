package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.HashMap;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗二叉树。
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class M_Tree_ReBuildTreeFromPostOrderAndInOrder_106_TODO {

    /**
     * 手撕 99% 48%
     */
    int[] inorder;
    int[] postorder;
    HashMap<Integer, Integer> num2IdxMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        this.num2IdxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            num2IdxMap.put(inorder[i], i);
        }
        return dfsBuild(0, inorder.length - 1, 0, postorder.length - 1);
    }

    //构造这个子树的根节点
    public TreeNode dfsBuild(int inStart, int inEnd, int postStart, int postEnd) {
        if (postStart > postEnd) return null;
        //postorder最后一个节点是根节点
        TreeNode node = new TreeNode(postorder[postEnd]);

        //找到inorder的分界线
        int inRootIdx = num2IdxMap.get(node.val);
        int leftSize = inRootIdx - inStart;

        node.left = dfsBuild(inStart, inRootIdx - 1, postStart, postStart + leftSize - 1);
        node.right = dfsBuild(inRootIdx + 1, inEnd, postStart + leftSize, postEnd - 1);
        return node;
    }

}
