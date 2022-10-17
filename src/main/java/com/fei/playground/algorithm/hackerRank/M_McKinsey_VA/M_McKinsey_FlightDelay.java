package com.fei.playground.algorithm.hackerRank.M_McKinsey_VA;

import java.util.*;

/**
 * https://www.hackerrank.com/codepair/ybrihyopiuxmtobpotyifdtoimienkle/questions/2?b=eyJpbnRlcnZpZXdfaWQiOjM1Njc1NDIsInJvbGUiOiJpbnRlcnZpZXdlciIsInNob3J0X3VybCI6Imh0dHBzOi8vaHIuZ3MvYzY1OTFmMSIsImNhbmRpZGF0ZV91cmwiOiJodHRwczovL2hyLmdzL2IwMzViNTQifQ
 * 2022 Sep 20
 * A network of flights contains flight nodes number of flights denoted by
 * [1, 2,..., flight nodes]. There is a list of dependencies among flights
 * denoted by the arrays flight fromll, flights leaving a city, and flight toll,
 * flights arriving in a city. Each pair (flight fromlil, flight toil} denotes that
 * flight fromli] depends on flight_ toil and must depart only after
 * flight toli has landed. If a flight is delayed, all the flights dependent on
 * this flight and their corresponding dependencies are also delayed.
 * <p>
 * Given a list of k initially delayed flights and the network as described, find
 * the list of all delayed flights. Return the list sorted in increasing order of
 * flight numbers.
 * <p>
 * Example
 * Consider flight nodes = 4, and the number of dependencies m = 2.
 * flight from = [4, 3]
 * flight to = [1, 2]
 * The number of delaved flights k = 2, and delayed = [1, 3].
 * <p>
 * Flight 1 is delaved.
 * o Flight 4 depends on flight 1, so flight 4 is delayed.
 * • Flight 3 is delaved.
 * • There are no flights dependent on flight 3
 * <p>
 * Return the sorted array of delaved flights, [1, 3, 4].
 * <p>
 * <p>
 * Function Description
 * Complete the function countDelayedFlights in the editor below.
 * countDelayedFlights has the following parameters:
 * int flight nodes: the number of flights
 * int flight from[mj: the details of the flight dependencies
 * int flight_to[mj: the details of the flight dependencies
 * int delayed[kj: the flights delayed initially
 * Returns
 * int[] 7. the sorted list of all delayed flights
 * <p>
 * Constraints
 * 2 ≤ flight _nodes ≤105
 * 1 ≤ m s min(flight_nodes * (flight_nodes - 1) / 2, 105)
 * 1 ≤ k ≤ flight _nodes
 * 1 < flight _fromlil, flight_toli] < n
 * flight _from[i] ‡ flight_to[il
 * 1 ≤ delayed[i] ≤ flight _nodes
 * The pair {flight _fromli], flight_to[il} will only be given once in the input.
 */
public class M_McKinsey_FlightDelay {

    public static List<Integer> countDelayedFlights(int flightNodes, List<Integer> flightFrom,
                                                    List<Integer> flightTo, List<Integer> delayed) {
        HashMap<Integer, LinkedList<Integer>> toFromMap = new HashMap<>(flightFrom.size());
        Set<Integer> countedSet = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.addAll(delayed);

        //build dependency map
        LinkedList<Integer> temp;
        for (int i = 0; i < flightFrom.size(); i++) {
            temp = toFromMap.computeIfAbsent(flightTo.get(i), a -> new LinkedList<>());
            temp.add(flightFrom.get(i));
        }
        System.out.println(toFromMap);

        int curFlight;
        while (!q.isEmpty()) {
            curFlight = q.poll();
            countedSet.add(curFlight);
            //check dependency
            if (toFromMap.containsKey(curFlight)) {
                for (Integer from : toFromMap.get(curFlight)) {
                    if (!countedSet.contains(from)) {
                        q.offer(from);
                    }
                }
            }
        }

        LinkedList<Integer> result = new LinkedList<>(countedSet);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
//        List<Integer> flightFrom = Arrays.asList(4, 3);
//        List<Integer> flightTo = Arrays.asList(1, 2);
//        List<Integer> delayed = Arrays.asList(1, 3);
//        int nodes = 4;
//        System.out.println(countDelayedFlights(nodes, flightFrom, flightTo, delayed));

        //sample case 0
//        List<Integer> flightFrom = Arrays.asList(1, 2, 3, 1);
//        List<Integer> flightTo = Arrays.asList(4, 1, 2, 3);
//        List<Integer> delayed = Arrays.asList(1);
//        int nodes = 4;
//        System.out.println(countDelayedFlights(nodes, flightFrom, flightTo, delayed));

        //sample case 1
        List<Integer> flightFrom = Arrays.asList(1, 1, 2, 3, 4);
        List<Integer> flightTo = Arrays.asList(2, 4, 3, 5, 5);
        List<Integer> delayed = Arrays.asList(2, 4);
        int nodes = 5;
        System.out.println(countDelayedFlights(nodes, flightFrom, flightTo, delayed));
    }
}
