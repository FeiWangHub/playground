package com.cloudkitchens.feisolution;

import com.alibaba.fastjson.JSON;
import com.cloudkitchens.feisolution.model.KitchenModel;
import com.cloudkitchens.feisolution.model.OrderModel;
import com.cloudkitchens.feisolution.service.dispatchService.FIFOStrategy;
import com.cloudkitchens.feisolution.service.dispatchService.MatchStrategy;
import com.cloudkitchens.feisolution.util.Constants;
import com.cloudkitchens.feisolution.util.DateUtil;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class FeiSolutionMain {

    public static void main(String[] args) throws InterruptedException {
        //init mock order data
        List<OrderModel> ordersList = JSON.parseArray(Constants.ORDERS_JSON, OrderModel.class);
        LinkedList<OrderModel> ordersPool = new LinkedList<>(ordersList);
        final int totalSize = ordersPool.size();

        System.out.println(String.format("---- Kitchen Started, time now is %s ----", DateUtil.HHmmssSSS.format(new Date())));

        //init Kitchen FIFO
//        KitchenModel kitchen = new KitchenModel(new FIFOStrategy());
        KitchenModel kitchen = new KitchenModel(new MatchStrategy());

        //start receiving orders, wait until all orders dispatched
        while (totalSize != kitchen.getDispatchedOrders().size()) {
            System.out.println();
            //1 mock receive certain qty of orders every second
            for (int i = 0; i < Constants.ORDERS_PER_RECEIVE; i++) {
                if (ordersPool.isEmpty()) {
                    break;
                }
                kitchen.receiveOrder(ordersPool.pop());
            }

            //2 update couriers' arrive state
            kitchen.updateCouriersArriveState();
            kitchen.updateOrderReadyState();

            //3 mock time increment
            Thread.sleep(Constants.ORDER_RECEIVE_FREQUENCY_SEC * 1000);
            System.out.println(String.format("---- Progress %s/%s, Time incremented to %s ----",
                    kitchen.getDispatchedOrders().size(),
                    totalSize,
                    DateUtil.HHmmssSSS.format(new Date())));
        }

        //print all statistics
        System.out.println();
        System.out.println("---- Final Statistics -----");
        float sumFoodWaitTime = 0, sumCourierWaitTime = 0;
        for(OrderModel o: kitchen.getDispatchedOrders()){
            sumFoodWaitTime += o.calWaitingTime();
            sumCourierWaitTime += o.getCourierWaitTime();
            System.out.println(String.format("Food: %s, Courier %s", o.calWaitingTime(), o.getCourierWaitTime()));
        }

        System.out.println(String.format("Average Food Wait Time: %s seconds", sumFoodWaitTime/totalSize));
        System.out.println(String.format("Average Courier Wait Time: %s seconds", sumCourierWaitTime/totalSize));
    }

}
