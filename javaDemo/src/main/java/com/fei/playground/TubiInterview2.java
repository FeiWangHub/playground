package com.fei.playground;

import com.fei.playground.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class TubiInterview2 {

    /**
     * Fetch the following csv and get top 10 rating movies.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(String.format("---- Started, time now is %s ----", DateUtil.HHmmssSSS.format(new Date())));

//        val ranking = Ranking("poc", List(1, 2, 3))
//        val container = Container("poc", List(0, 3, 4))
//        [1, 6, 3, 9, 2, 4]

//        Container c = new Container("a", new ArrayList<>(Arrays.asList(0,3,4)));
        Container c = new Container("a", new ArrayList<>(Arrays.asList(1, 6, 3, 9, 2, 4)));
//        Ranking r = new Ranking("a", new ArrayList<>(Arrays.asList(1,2,3)));
        Ranking r = new Ranking("a", new ArrayList<>(Arrays.asList(5,4,3,2,1)));//4,3,2,1,6,9
        //expected 3,0,4
        //expected 1 2 3
        Container result = r.rank(c);
        System.out.println(c.getItems());
    }

    @Data
    @AllArgsConstructor
    @ToString
    static
    class Container{
        String userId;
        ArrayList<Integer> items;
    }

    @Data
    @AllArgsConstructor
    @ToString
    static
    class Ranking{
        String userId;
        ArrayList<Integer> criteria;//有序 数组

        public Container rank(Container con){
            ArrayList<Integer> new_items = new ArrayList<>(con.getItems().size());

            //方案二 init hashmap
            int c_len = criteria.size();
            Integer temp_crite;
            //外部排序 [] container K item V index

            //StableSort
            HashMap<Integer, Integer> criteria_map = new HashMap<>(c_len);
            for (int i = 0; i <c_len ; i++) {
                temp_crite = criteria.get(i);
                criteria_map.put(temp_crite, temp_crite);
            }
            // find matched
            int con_len = con.getItems().size();
            Integer current_criteria;
            for (int i = 0; i < c_len; i++) {
                current_criteria = criteria.get(i);
                if(con.getItems().contains(current_criteria)){
                    new_items.add(current_criteria);
                }
            }

            //1 find match criteria : m+n
//            Integer current_criteria;
//            for (int i = 0; i < c_len; i++) {
//                current_criteria = criteria.get(i);
//                if(con.getItems().contains(current_criteria)){
//                    new_items.add(current_criteria);
//                }//TODO 这里可以用iterator优化直接remove
//            }

            //2 add rest of un-criteria-ed items: m*m
            Integer curr_item;
            for (int i = 0; i < con_len; i++) {
                curr_item = con.getItems().get(i);
                if(!new_items.contains(curr_item)){
                    new_items.add(curr_item);
                }
            }

            con.setItems(new_items);
            return con;
        }

    }
}
