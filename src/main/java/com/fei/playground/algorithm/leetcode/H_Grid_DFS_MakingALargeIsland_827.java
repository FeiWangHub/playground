package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 827. 最大人工岛
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格0 变成1 。
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * 岛屿 由一组上、下、左、右四个方向相连的1 形成。
 * https://leetcode.cn/problems/making-a-large-island/
 * <p>
 * 大神的解题思路：https://leetcode.cn/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/#comment
 * 这道题实际上是对网格做了两遍 DFS：第一遍 DFS 遍历陆地格子，计算每个岛屿的面积并标记岛屿；
 * 第二遍 DFS 遍历海洋格子，观察每个海洋格子相邻的陆地格子。
 * <p>
 * 时间优先暂时不自己做了
 */
public class H_Grid_DFS_MakingALargeIsland_827 {

    int move[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * 网友思路 双DFS 32% 62%
     * https://leetcode.cn/problems/making-a-large-island/solution/zui-da-ren-gong-dao-by-leetcode-solution-lehy/
     * 先BFS搜索所有现成的岛屿，并把每一个岛屿都标记上号码，同时统计出来面积，再枚举每一个值为0的块儿，四周联通计算
     */
    public int largestIsland(int[][] grid) {
        //先BFS搜索所有现成的岛屿，并把每一个岛屿都标记上号码，同时统计出来面积，再枚举每一个值为0的块儿，四周联通计算
        int n = grid.length, count = 2;
        int area[] = new int[n * n + 5];//标记每个联通块儿的面积
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = count;
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[]{i, j});
                    for (int p = 0; p < list.size(); p++) {
                        int a[] = list.get(p);
                        for (int k = 0; k < 4; k++) {
                            int x = a[0] + move[k][0], y = a[1] + move[k][1];
                            if (x < 0 || x == n || y < 0 || y == n || grid[x][y] != 1) {
                                continue;
                            }
                            grid[x][y] = count;
                            list.add(new int[]{x, y});
                        }
                    }
                    area[count] = list.size();
                    count++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int x = i + move[k][0], y = j + move[k][1];
                        if (x < 0 || x == n || y < 0 || y == n || grid[x][y] == 0) {
                            continue;
                        }
                        set.add(grid[x][y]);
                    }
                    int sum = 0;
                    for (int a : set) {
                        sum += area[a];
                    }
                    ans = Math.max(ans, sum + 1);
                }
            }
        }
        return ans == 0 ? n * n : ans;
    }

    /**
     * 官方思路 64% 32%
     * 遍历每一个水域，判断它是否能连接2个岛屿，如果能，DFS算面积
     */
    static int[] d = {0, -1, 0, 1, 0};
    public int largestIsland_official(int[][] grid) {
        int n = grid.length, res = 0;
        int[][] tag = new int[n][n];
        Map<Integer, Integer> area = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && tag[i][j] == 0) {
                    int t = i * n + j + 1;
                    area.put(t, dfs(grid, i, j, tag, t));
                    res = Math.max(res, area.get(t));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int z = 1;
                    Set<Integer> connected = new HashSet<Integer>();
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k], y = j + d[k + 1];
                        if (!valid(n, x, y) || tag[x][y] == 0 || connected.contains(tag[x][y])) {
                            continue;
                        }
                        z += area.get(tag[x][y]);
                        connected.add(tag[x][y]);
                    }
                    res = Math.max(res, z);
                }
            }
        }
        return res;
    }

    public int dfs(int[][] grid, int x, int y, int[][] tag, int t) {
        int n = grid.length, res = 1;
        tag[x][y] = t;
        for (int i = 0; i < 4; i++) {
            int x1 = x + d[i], y1 = y + d[i + 1];
            if (valid(n, x1, y1) && grid[x1][y1] == 1 && tag[x1][y1] == 0) {
                res += dfs(grid, x1, y1, tag, t);
            }
        }
        return res;
    }

    public boolean valid(int n, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
