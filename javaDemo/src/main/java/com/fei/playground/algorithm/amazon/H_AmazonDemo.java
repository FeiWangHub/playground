package com.fei.playground.algorithm.amazon;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1. User Input
 * 2. Shopping Chart match or not
 */
public class H_AmazonDemo {

    /**
     * 用户敲至少2个字符之后开始返回suggestion，最多返还3个选项
      * @param repository
     * @param customerQuery
     * @return
     */
    public static List<List<String>> searchSuggestions(List<String> repository, String customerQuery) {
        // Write your code here

        //sugget 3 keywords, after user type 2 characters. result sort 1st alphabetically, case-insensitive
        //retur lower case; [] array if not found;
        customerQuery = customerQuery.toLowerCase();
        int qLen = customerQuery.length();
        List<List<String>> result = new ArrayList<>(qLen-1);
        repository = repository.stream().map(s->s.toLowerCase()).sorted().collect(Collectors.toList());

        for(int i=1; i<qLen; i++){
            List<String> suggestions = new LinkedList<>();
            if(i!=0){
                //search all possible
                String curQuery = customerQuery.substring(0, i+1);
                for(String s:repository){
                    if(s.startsWith(curQuery)){
                        suggestions.add(s);
                        if(suggestions.size()==3) break;
                    }
                }
            }
            result.add(suggestions);
        }

        return result;
    }

    /**
     * define whether 判断用户的shopping列表 是否符合codeList的pattern；
     * 符合的话中将返回1；Anything可以代表任意；codeList的group和group之间可以任意数量
     * @param codeList
     * @param shoppingCart
     * @return
     */
    public static int foo(List<String> codeList, List<String> shoppingCart) {
        // Write your code here
        System.out.println(codeList);
        int chartIdx = 0;
        for(String codeStr: codeList){
            List<String> group = Arrays.asList(codeStr.split(" "));

            //loop group
            for(int i=0; i<group.size(); i++){
                //end of chart
                if(chartIdx == shoppingCart.size()){
                    return 0;
                }

                String nextTarget = group.get(i);
                //anything
                if(nextTarget.equals("anything")){
                    chartIdx++;
                }else if(!shoppingCart.get(chartIdx).equals(nextTarget)){
                    //not found, search this group again
                    i=-1;
                    continue;
                }else{
                    //found
                    chartIdx++;
                }
            }
        }
        return 1;
    }



    public static void main(String[] args) {
        List<String> strArrays = Arrays.asList("Abc","B", "CD");
        strArrays = strArrays.stream().map(s->s.toLowerCase()).sorted().collect(Collectors.toList());

        //自定义排序
        strArrays = strArrays.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }).collect(Collectors.toList());
        System.out.println(strArrays);
    }

//    input: [orange, apple apple, banana orange apple, banana] expect 1
//    input: [apple apricot, banana anything guava, papaya anything] expect 0

}
