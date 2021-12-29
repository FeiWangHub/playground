package com.fei.playground.algorithm.hackerRank;

import java.util.LinkedList;
import java.util.List;

/**
 * example, if  left rotations are performed on array , then the array would become.
 * Note that the lowest index item moves to the highest index in a rotation. This is called a circular array.
 * https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 */
public class E_RotateLeft {

    public static List<Integer> rotLeft(List<Integer> a, int d) {
        // Write your code here
        LinkedList<Integer> result = new LinkedList<>();
        for(int i=d; i<a.size(); i++){
            result.add(a.get(i));
        }

        for(int i=0; i<d; i++){
            result.add(a.get(i));
        }
        return result;
    }
}
