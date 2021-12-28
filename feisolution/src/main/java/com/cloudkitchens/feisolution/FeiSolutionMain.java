package com.cloudkitchens.feisolution;

import com.alibaba.fastjson.JSON;
import com.cloudkitchens.feisolution.model.*;
import com.cloudkitchens.feisolution.service.dispatchService.FIFOStrategy;
import com.cloudkitchens.feisolution.service.dispatchService.MatchStrategy;
import com.cloudkitchens.feisolution.util.Constants;
import com.cloudkitchens.feisolution.util.DateUtil;

import java.util.*;

import static java.lang.Thread.sleep;

/**
 * KitchenDispatcher simulation
 */
public class FeiSolutionMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("---- Kitchen Started, time now is %s ----", DateUtil.HHmmssSSS.format(new Date())));

        //1 init Kitchen with user input, use FIFO as default strategy
        KitchenDispatcher kitchen;
        if(args.length != 0 && args[0].equals("matched")){
            kitchen = new KitchenDispatcher(new MatchStrategy());
            System.out.println("--- using Matched strategy ---");
        }else{
            kitchen = new KitchenDispatcher(new FIFOStrategy());
            System.out.println("--- using FIFO strategy ---");
        }

        //2 init mock order data, mock receive 2 orders every second
        List<OrderModel> ordersList = JSON.parseArray(Constants.ORDERS_JSON, OrderModel.class);
        LinkedList<OrderModel> ordersPool = new LinkedList<>(ordersList);
        final int totalSize = ordersPool.size();

        Timer timer = new Timer();
        new Thread(() -> {
            while (!ordersPool.isEmpty()) {
                for (int i = 0; i < Constants.ORDERS_PER_RECEIVE; i++) {
                    if (ordersPool.isEmpty()) {
                        break;
                    }
                    OrderModel order = ordersPool.pop();
                    kitchen.receiveOrder(order);

                    //mock the "order get ready" event
                    timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                order.setState(OrderState.READY, order.getEstReadyTime());
                                kitchen.onOrderReady(order);
                            }
                        },order.getEstReadyTime()
                    );

                    //mock the "courier arrived kitchen" event
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            CourierModel c = order.getCourierDispatchedByThisOrder();
                            c.setState(CourierState.ARRIVED_KITCHEN, c.getEstArriveTime());
                            kitchen.onCourierArrived(c);
                        }
                    }, order.getCourierDispatchedByThisOrder().getEstArriveTime());

                }

                //receive order every 1 second
                try {
                    sleep(Constants.ORDER_RECEIVE_FREQUENCY_SEC * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println();
                System.out.println(String.format("------- Progress %s/%s, Time incremented to %s -------",
                        kitchen.getFinishedOrders().size(),
                        totalSize,
                        DateUtil.HHmmssSSS.format(new Date())));
            }
        }).start();

        //3 checking progress
        while (totalSize != kitchen.getFinishedOrders().size()) {
            sleep(2000);
        }

        //4 print all statistics
        timer.cancel();
        printStatistics(kitchen, totalSize);
    }

    private static void printStatistics(KitchenDispatcher kitchen, int totalSize) {
        System.out.println();
        float sumFoodWaitTime = 0, sumCourierWaitTime = 0;
        for(OrderModel o: kitchen.getFinishedOrders()){
            sumFoodWaitTime += o.calWaitingTime();
            sumCourierWaitTime += o.getCourier().calWaitingTime();
            System.out.println(String.format("Food waited %s ms, Courier waited %s ms", o.calWaitingTime(), o.getCourier().calWaitingTime()));
        }

        System.out.println();
        System.out.println("---- Final Statistics -----");
        System.out.println(String.format("Average Food Wait Time: %s milliseconds", sumFoodWaitTime/totalSize));
        System.out.println(String.format("Average Courier Wait Time: %s milliseconds", sumCourierWaitTime/totalSize));
    }

}
