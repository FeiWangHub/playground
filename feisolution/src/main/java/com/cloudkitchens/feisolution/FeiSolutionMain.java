package com.cloudkitchens.feisolution;

import com.alibaba.fastjson.JSON;
import com.cloudkitchens.feisolution.model.KitchenModel;
import com.cloudkitchens.feisolution.model.OrderModel;
import com.cloudkitchens.feisolution.service.dispatchService.FIFOStrategy;
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
        KitchenModel kitchen = new KitchenModel(new FIFOStrategy());

        //start receiving orders, wait until all orders dispatched
        while (totalSize != kitchen.getDispatchedOrders().size()) {
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

            //3 scan and dispatch ready orders
//            kitchen.scanAndPickupReadyOrders();

            //4 mock time increment
            Thread.sleep(Constants.ORDER_RECEIVE_FREQUENCY_SEC * 1000);
            System.out.println(" ");
            System.out.println(String.format("完成进度%s/%s", kitchen.getDispatchedOrders().size(), totalSize));
            System.out.println(String.format("---- Time incremented to %s ----", DateUtil.HHmmssSSS.format(new Date())));
        }

        //print all statistics TODO
    }

}
