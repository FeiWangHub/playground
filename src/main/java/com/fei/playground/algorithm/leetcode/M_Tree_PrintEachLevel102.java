package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的层序遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class M_Tree_PrintEachLevel102 {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return result;
        }

        //新的递归解法 BFS
        bfs(root, 0);


        //下边是循环解法 Begin
        // //1. find all nodes of current level
        // ArrayList<TreeNode> curLevel = new ArrayList<>();
        // ArrayList<TreeNode> nextLevel = new ArrayList<>();
        // nextLevel.add(root);

        // //for each node in current
        // while(nextLevel.size()!=0){
        //     curLevel = nextLevel;
        //     nextLevel = new ArrayList<TreeNode>();
        //     ArrayList<Integer> curLevelInt = new ArrayList<Integer>();

        //     Iterator<TreeNode> it = curLevel.iterator();
        //     while(it.hasNext()){
        //         TreeNode n = it.next();
        //         curLevelInt.add(n.val);

        //         //1. pushin left
        //         if(n.left != null){
        //             nextLevel.add(n.left);
        //         }

        //         //2. pushin right
        //         if(n.right != null){
        //             nextLevel.add(n.right);
        //         }
        //     }

        //     result.add(curLevelInt);
        // }

        //循环解法 END

        return result;

    }

    public void bfs(TreeNode node, int curLevel) {
        //出口
        if (node == null) return;

        //本层
        if (result.size() < curLevel + 1) {
            result.add(new ArrayList<Integer>());
        }
        result.get(curLevel).add(node.val);

        //下一层左，右
        bfs(node.left, curLevel + 1);
        bfs(node.right, curLevel + 1);
    }

    /**
     * 手撕 queue 100% 83%
     */
    public List<List<Integer>> levelOrder_loop(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (null == root) return res;

        LinkedList<TreeNode> nextLevel = new LinkedList<>();
        nextLevel.offer(root);

        while (!nextLevel.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int curLen = nextLevel.size();
            while (curLen-- != 0) {
                TreeNode n = nextLevel.poll();
                level.add(n.val);
                if (n.left != null) nextLevel.offer(n.left);
                if (n.right != null) nextLevel.offer(n.right);
            }
            res.add(level);
        }

        return res;
    }

}
