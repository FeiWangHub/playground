package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 从前序与中序遍历序列构造二叉树
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * 给定两个整数数组preorder 和 inorder，其中preorder 是二叉树的先序遍历， inorder是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class M_Tree_ReBuildTreeFromPreOrderAndInOrder_105 {

    ArrayList<Integer> preorder;
    ArrayList<Integer> inorder;

    /**
     * 5% 17% 使用list手撸
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = (ArrayList<Integer>) Arrays.stream(preorder).boxed().collect(Collectors.toList());
        this.inorder = (ArrayList<Integer>) Arrays.stream(inorder).boxed().collect(Collectors.toList());
        return build(0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode build(int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) return null;

        TreeNode root = new TreeNode(preorder.get(preStart));
        int rootIdxOfInorder = inorder.indexOf(root.val);

        int lInEnd = rootIdxOfInorder - 1;//root 前节点
        int lTreeSize = rootIdxOfInorder - inStart;
        int lPreStart = preStart + 1;
        int lPreEnd = preStart + lTreeSize;
        root.left = build(lPreStart, lPreEnd, inStart, lInEnd);

        int rInStart = rootIdxOfInorder + 1;//root 前节点;
        int rPreStart = preStart + lTreeSize + 1;
        root.right = build(rPreStart, preEnd, rInStart, inEnd);

        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        M_Tree_ReBuildTreeFromPreOrderAndInOrder_105 test = new M_Tree_ReBuildTreeFromPreOrderAndInOrder_105();
        test.buildTree(preorder, inorder);
    }

    /**
     * 官方答案 99% 21%
     * 使用hashmap存储位置
     */
    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree_official(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

}
