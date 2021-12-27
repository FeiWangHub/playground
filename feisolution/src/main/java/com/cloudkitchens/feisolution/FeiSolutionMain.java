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
 * 2021.12.24更新改版日志
 * - 输出的统计时间单位 从秒改为毫秒
 * - 改进策略模式的代码结构，将KitchenStrategy从抽象类改为接口
 * - 修复UT中assertEquals(expected, actual)的参数前后顺序
 * - pickup的时间记录更加精准，不在受测试运行的interval影响
 * - FIFO模式时，现在会正确的匹配"最早到来的courier"或"随机的order"
 * - 使用Timer，让order-ready和courier-arrived等事件，在准确的时间点上发生
 *
 * 3. 考虑去掉order的readyTime
 * 4. 重新梳理synchronized，也许不需要用ConcurrentLinkedQueue
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

//        KitchenDispatcher kitchen = new KitchenDispatcher(new MatchStrategy());//TODO DELETE

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

                    //TODO 设置order ready和
                    timer.schedule(
                            new TimerTask() {
                                @Override
                                public void run() {
                                    order.setState(OrderState.READY, order.getEstReadyTime());
                                    kitchen.onOrderReady(order);
                                }
                            },order.getEstReadyTime()
                    );

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            CourierModel c = order.getCourierDispatchedByThisOrder();
                            c.setState(CourierState.ARRIVED_KITCHEN, c.getEstArriveTime());
                            kitchen.onCourierArrived(c);
                        }
                    }, order.getCourierDispatchedByThisOrder().getEstArriveTime());

                }
                try {
                    sleep(Constants.ORDER_RECEIVE_FREQUENCY_SEC * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //3 checking progress
        while (totalSize != kitchen.getDispatchedOrders().size()) {
            sleep(2000);
            System.out.println();
            System.out.println(String.format("---- Progress %s/%s, Time incremented to %s ----",
                    kitchen.getDispatchedOrders().size(),
                    totalSize,
                    DateUtil.HHmmssSSS.format(new Date())));
        }

        //4 print all statistics
        timer.cancel();
        printStatistics(kitchen, totalSize);
    }

    private static void printStatistics(KitchenDispatcher kitchen, int totalSize) {
        System.out.println();
        System.out.println("---- Final Statistics -----");
        float sumFoodWaitTime = 0, sumCourierWaitTime = 0;
        for(OrderModel o: kitchen.getDispatchedOrders()){
            sumFoodWaitTime += o.calWaitingTime();
            sumCourierWaitTime += o.getCourier().calWaitingTime();
            System.out.println(String.format("Food waited %s ms, Courier waited %s ms", o.calWaitingTime(), o.getCourier().calWaitingTime()));
        }

        System.out.println(String.format("Average Food Wait Time: %s milliseconds", sumFoodWaitTime/totalSize));
        System.out.println(String.format("Average Courier Wait Time: %s milliseconds", sumCourierWaitTime/totalSize));
    }

}
