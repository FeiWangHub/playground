package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class M_Tree_ZCurveLoop_103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traversal(root, res, 0);
        return res;
    }

    //java版本1ms，使用dfs，对应层判断一下奇偶，决定在表头还是表尾添加元素就可以了
    private void traversal(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }

        if (res.size() == level) {
            res.add(new ArrayList<Integer>());
        }

        if ((level & 1) == 1){
            res.get(level).add(0, root.val);
        } else {
            res.get(level).add(root.val);
        }

        traversal(root.left, res, level + 1);
        traversal(root.right, res, level + 1);
    }

}
