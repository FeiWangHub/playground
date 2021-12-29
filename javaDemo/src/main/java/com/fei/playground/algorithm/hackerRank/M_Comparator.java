package com.fei.playground.algorithm.hackerRank;

import java.util.Comparator;

/**
 * sort by score, descending
 * if score equals, sort by name alphabetically
 * https://www.hackerrank.com/challenges/ctci-comparator-sorting/problem?h_l=interview&isFullScreen=true&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=sorting
 */
public class M_Comparator {

    class Player {
        String name;
        int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    class Checker implements Comparator<Player> {
        // complete this method
        public int compare(Player a, Player b) {
            if(a.score > b.score){
                return -1;
            }else if(a.score < b.score){
                return 1;
            }else{
                return a.name.compareTo(b.name);
            }
        }
    }

}
