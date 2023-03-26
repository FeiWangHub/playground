package com.fei.playground.algorithm.leetcode;

/**
 * 207. 课程表 TODO
 * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。先修课程按数组prerequisites给出，其中prerequisites[i]=[ai, bi]，表示如果要学习课程ai则必须先学习课程bi。
 * 例如，先修课程对[0,1] 表示：想要学习课程0，你需要先完成课程1
 * 请你判断是否可能完成所有课程的学习？如果可以，返回true ；否则，返回 false 。
 * https://leetcode.cn/problems/course-schedule/
 */
public class M_Topology_DFS_CourseSchedule_207_TODO {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //统计每个课被指向次数，初始被指向次数为0的肯定是安全的（不在环上）。
        //每被安全课程指向一次，被指次数减一，
        //如果被指次数减到0，说明该课程全部指向都来自安全课程，则它也是安全的。
        //依此进行队列循环。

//        for(int[] row: prerequisites){
//            System.out.println(Arrays.toString(row));
//        }
        return true;
    }

}
