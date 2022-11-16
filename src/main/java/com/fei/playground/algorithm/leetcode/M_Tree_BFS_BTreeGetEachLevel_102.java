package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 */
public class M_Tree_BFS_BTreeGetEachLevel_102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        //1. find all nodes of current level
        ArrayList<TreeNode> curLevel = new ArrayList<>();
        ArrayList<TreeNode> nextLevel = new ArrayList<>();
        nextLevel.add(root);

        //for each node in current
        while (nextLevel.size() != 0) {
            curLevel = nextLevel;
            nextLevel = new ArrayList<TreeNode>();
            ArrayList<Integer> curLevelInt = new ArrayList<Integer>();

            Iterator<TreeNode> it = curLevel.iterator();
            while (it.hasNext()) {
                TreeNode n = it.next();
                curLevelInt.add(n.val);

                //1. pushin left
                if (n.left != null) {
                    nextLevel.add(n.left);
                }

                //2. pushin right
                if (n.right != null) {
                    nextLevel.add(n.right);
                }
            }
            result.add(curLevelInt);
        }

        return result;
    }

}
