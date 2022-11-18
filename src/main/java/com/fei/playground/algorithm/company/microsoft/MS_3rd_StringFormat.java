package com.fei.playground.algorithm.company.microsoft;

/**
 * 2022.01.28
 * format "this is {0}, and this is {1}", ['a','b']
 */
public class MS_3rd_StringFormat {

    public static String strFormat(String pattern, int[] arr) {
        StringBuilder resultBuilder = new StringBuilder();
        StringBuilder idxBuilder = new StringBuilder();
        boolean openBracket = false;
        char cur = ' ';

        for (int i = 0; i < pattern.length(); i++) {
            cur =pattern.charAt(i);
            if(cur=='{'){//left bracket
                openBracket = true;
            }else if(cur=='}'){//right bracket
                openBracket = false;
                int id = Integer.parseInt(idxBuilder.toString());//TODO boundary check
                resultBuilder.append(arr[id]);
                idxBuilder = new StringBuilder();//TODO improve maybe
            }else if(openBracket){//number in bracket
                idxBuilder.append(cur);
            }else{//normal char
                resultBuilder.append(cur);
            }
        }

        return resultBuilder.toString();
    }

}
