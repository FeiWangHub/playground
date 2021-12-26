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

import static java.lang.Thread.sleep;

/**
 * 2021.12.24日志
 * - 输出的统计时间单位 从秒改为毫秒
 * - 改进策略模式的代码结构，将KitchenStrategy从抽象类改为接口
 * - 修复UT中assertEquals(expected, actual)的参数前后顺序
 * - pickup的时间记录更加精准，不在受测试运行的interval影响
 *
 * 4. 重新梳理synchronized，也许不需要用ConcurrentLinkedQueue
 * 5. maybe 新起一个线程模拟订单到达，一个线程监控courier，一个监控
 * 6. maybe 尝试多个线程访问厨房测试
 * TODO If there are multiple orders available, pick up an arbitrary order.
 * TODO 是否要将kitchen的updateOrderReadyState 改成onCourierArrived和onOrderReady? 变成事件，完全模拟现实?
 */
public class FeiSolutionMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("---- Kitchen Started, time now is %s ----", DateUtil.HHmmssSSS.format(new Date())));

        //1 init Kitchen with user input, use FIFO as default strategy
        KitchenModel kitchen;
        if(args.length != 0 && args[0].equals("matched")){
            kitchen = new KitchenModel(new MatchStrategy());
            System.out.println("--- using Matched strategy ---");
        }else{
            kitchen = new KitchenModel(new FIFOStrategy());
            System.out.println("--- using FIFO strategy ---");
        }

        //2 init mock order data, mock receive certain qty of orders every second
        List<OrderModel> ordersList = JSON.parseArray(Constants.ORDERS_JSON, OrderModel.class);
        LinkedList<OrderModel> ordersPool = new LinkedList<>(ordersList);
        final int totalSize = ordersPool.size();
        new Thread(() -> {
            while (!ordersPool.isEmpty()) {
                for (int i = 0; i < Constants.ORDERS_PER_RECEIVE; i++) {
                    if (ordersPool.isEmpty()) {
                        break;
                    }
                    kitchen.receiveOrder(ordersPool.pop());
                }
                try {
                    sleep(Constants.ORDER_RECEIVE_FREQUENCY_SEC * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //3 mock start receiving orders, wait until all orders dispatched
        while (totalSize != kitchen.getDispatchedOrders().size()) {
            System.out.println();

            //3.1 update couriers' arrive state
            kitchen.updateCouriersArriveState();
            kitchen.updateOrderReadyState();

            //3.2 mock time increment
            sleep(1000);
            System.out.println(String.format("---- Progress %s/%s, Time incremented to %s ----",
                    kitchen.getDispatchedOrders().size(),
                    totalSize,
                    DateUtil.HHmmssSSS.format(new Date())));
        }

        //4 print all statistics
        printStatistics(kitchen, totalSize);
    }

    private static void printStatistics(KitchenModel kitchen, int totalSize) {
        System.out.println();
        System.out.println("---- Final Statistics -----");
        float sumFoodWaitTime = 0, sumCourierWaitTime = 0;
        for(OrderModel o: kitchen.getDispatchedOrders()){
            sumFoodWaitTime += o.calWaitingTime();
            sumCourierWaitTime += o.getCourierWaitTime();
            System.out.println(String.format("Food: %s, Courier %s", o.calWaitingTime(), o.getCourierWaitTime()));
        }

        System.out.println(String.format("Average Food Wait Time: %s milliseconds", sumFoodWaitTime/totalSize));
        System.out.println(String.format("Average Courier Wait Time: %s milliseconds", sumCourierWaitTime/totalSize));
    }

}
