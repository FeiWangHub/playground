package com.fei.playground.algorithm.hackerRank;

/**
 * https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/forum?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees
 */
public class E_TreeFindCommonAncestor {

    static Node lca(Node root, int v1, int v2) {
        if (root.data > v1 && root.data > v2) {
            return lca(root.left, v1, v2);
        } else if (root.data < v1 && root.data < v2) {
            return lca(root.right, v1, v2);
        } else {
            return root;
        }
    }

    class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

}
