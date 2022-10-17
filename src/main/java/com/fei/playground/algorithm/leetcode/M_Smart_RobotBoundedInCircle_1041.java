package com.fei.playground.algorithm.leetcode;

/**
 * 1041. 困于环中的机器人
 * 在无限的平面上，机器人最初位于(0, 0)处，面朝北方。注意:
 * 北方向 是y轴的正方向。
 * 南方向 是y轴的负方向。
 * 东方向 是x轴的正方向。
 * 西方向 是x轴的负方向。
 * 机器人可以接受下列三条指令之一：
 * "G"：直走 1 个单位
 * "L"：左转 90 度
 * "R"：右转 90 度
 * 机器人按顺序执行指令instructions，并一直重复它们。
 * 只有在平面中存在环使得机器人永远无法离开时，返回true。否则，返回 false。
 */
public class M_Smart_RobotBoundedInCircle_1041 {

    /**
     * 1 如果给定的指令遍历一次结束时，机器人回到原点，则将给定的指令完整遍历任意次之后，机器人都将位于原点，因此机器人总是位于一个环中。
     * 2.如果给定的指令遍历一次结束时，机器人没有回到原点，但是机器人的朝向和初始朝向不同，则将给定的指令遍历两次或四次之后，
     * 机器人一定可以回到原点，因此机器人总是位于一个环中。假设给定的指令遍历一次结束时，机器人位于坐标 (x,y),分别考虑机器人的不同朝向。
     */
    public boolean isRobotBounded(String instructions) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        int xPosition = 0, yPosition = 0;
        int length = instructions.length();
        for (int i = 0; i < length; i++) {
            char instruction = instructions.charAt(i);
            if (instruction == 'G') {
                xPosition += directions[directionIndex][0];
                yPosition += directions[directionIndex][1];
            } else if (instruction == 'L') {
                directionIndex = (directionIndex + 3) % 4;
            } else if (instruction == 'R') {
                directionIndex = (directionIndex + 1) % 4;
            }
        }
        return (xPosition == 0 && yPosition == 0) || directionIndex != 0;
    }

}
