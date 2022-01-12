package com.fei.playground.algorithm.other;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1. User Input
 * 2. Shopping Chart match or not
 */
public class H_AmazonDemo {

    /**
     * Amazon Customer Reviews
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
     * Amazon Fresh Promo
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


    /**
     * 类似常规表达式的题目
     * 53/58 passed
     */
    public static int fooDP(List<String> codeList, List<String> shoppingCart) {
        // Write your code here
        // 1 transfer input as regExp
        List<String> pattern = new ArrayList<>();
        pattern.add("*");
        for(String codeStr: codeList){
            List<String> group = Arrays.asList(codeStr.split(" "));
            pattern.addAll(group);
            pattern.add("*");
        }
        System.out.println(pattern.toString());
        System.out.println(shoppingCart.toString());

        // 2 init dp
        boolean[][] dp = new boolean[pattern.size()+1][shoppingCart.size()+1];
        dp[0][0] = true;
        for(int x=1; x <= pattern.size(); x++){//gap of group, * is must true
            if(!pattern.get(x-1).equals("*")){
                break;
            }
            dp[x][0] = true;
        }

        // 3 lopp dp
        for(int x=1; x<= pattern.size(); x++){
            for(int y=1; y<= shoppingCart.size(); y++){
                //match
                if(pattern.get(x-1).equals(shoppingCart.get(y-1))
                        || pattern.get(x-1).equals("anything") ){
                    dp[x][y] = dp[x-1][y-1];
                } else if(pattern.get(x-1).equals("*")){ // *
                    dp[x][y] = dp[x-1][y] | dp[x][y-1];
                }
            }
        }

        return dp[pattern.size()][shoppingCart.size()] ? 1 : 0;
    }

    /**
     * 2022.01 Amazon正式OA面试题
     * teamSize是小组规模，skill是技能点数，maxDiff是最高和最低差
     * 求最多能组成多少team组合
     * input 6 5 1 2 1 4 5 3 2 exp 2
     */
    public static int countMaximumTeams(List<Integer> skill, int teamSize, int maxDiff) {
        // Write your code here
        // 1. sort skills asc, loop, find all possible combinaiton of starting here
        int count = 0;
        int len = skill.size();
        Collections.sort(skill);//asc
        System.out.println("teamSize:"+teamSize+"  maxDiff:"+maxDiff);
        System.out.println("Skills: "+ skill.toString());

        for(int i=0; i<len-2; i++){
            int countOfMeetMaxDiff = 0;
            int tempMax = skill.get(i) + maxDiff;

            for(int j=i+1; j<len; j++){
                if(skill.get(j) <= tempMax){
                    countOfMeetMaxDiff++;
                }else{
                    break;
                }
            }
            System.out.println("for pos: "+i+",matched count:"+countOfMeetMaxDiff);

            if(countOfMeetMaxDiff < teamSize-1){
                continue;
            }else{
                //wrong
                int combination = 1;
                for(int k=0; k<teamSize-2; k++){
                    combination *= countOfMeetMaxDiff;
                    countOfMeetMaxDiff--;
                }
                count += combination/2;
            }
        }
        return count;

        //following is too slow
        // for(int i=0; i<len; i++){
        //     for(int j=i+1; j<len; j++){
        //         for(int k=j+1; k<len; k++){
        //             if(Math.abs(skill.get(i)-skill.get(j)) <= maxDiff
        //                 && Math.abs(skill.get(j)-skill.get(k)) <= maxDiff
        //                 && Math.abs(skill.get(k)-skill.get(i)) <= maxDiff)
        //             count++;
        //         }
        //     }
        // }
        // return count;
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
