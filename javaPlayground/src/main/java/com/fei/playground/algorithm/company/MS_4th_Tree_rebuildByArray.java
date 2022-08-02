package com.fei.playground.algorithm.company;

import com.fei.playground.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 第4轮博士面试
 */
public class MS_4th_Tree_rebuildByArray {

    public static void dfs(TreeNode root) {
        if (root == null) return;
        //后序遍历 4 5 2 3 1
        dfs(root.left);
        dfs(root.right);
        System.out.println(root.val);

        //前序遍历 1，2，4，5，3
//        System.out.println(root.val);
//        dfs(root.left);
//        dfs(root.right);

        //中序遍历 4，2，5，1，3
//        dfs(root.left);
//        System.out.println(root.val);
//        dfs(root.right);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrFront = new ArrayList<>(Arrays.asList(1,2,4,5,3));
        ArrayList<Integer> arrMiddle = new ArrayList<>(Arrays.asList(4,2,5,1,3));
        TreeNode root = buildBTree(arrFront, arrMiddle);
        dfs(root);
    }

    public static TreeNode buildBTree(List<Integer> arrFront, List<Integer> arrMiddle) {
        if(arrFront.size()==0 || arrMiddle.size()==0) return null;

        //1 find root
        TreeNode root = new TreeNode(arrFront.get(0));

        //2. find root in arrMiddle
        int rootIdxOfMiddle = arrMiddle.indexOf(root.val);

        //3. found left/right tree of mid arr
        List<Integer> left = arrMiddle.subList(0, rootIdxOfMiddle);
        List<Integer> right = arrMiddle.subList(rootIdxOfMiddle + 1, arrMiddle.size());

        //4. found left/right tree of arrFront
        List<Integer> leftOfFront = arrFront.subList(1, left.size() + 1);
        List<Integer> rightOfFront = arrFront.subList(left.size() + 1, arrFront.size());

        root.left = buildBTree(leftOfFront, left);
        root.right = buildBTree(rightOfFront, right);

        return root;
    }

}
