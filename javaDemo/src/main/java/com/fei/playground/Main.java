package com.fei.playground;

import com.fei.playground.util.DateUtil;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("---- Started, time now is %s ----", DateUtil.HHmmssSSS.format(new Date())));

        //1 init with user input
        if(args.length != 0 && args[0].equals("matched")){

        }else{

        }
    }

}
