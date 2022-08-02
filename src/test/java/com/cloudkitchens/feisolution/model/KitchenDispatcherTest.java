package com.cloudkitchens.feisolution.model;

import com.alibaba.fastjson.JSON;
import com.cloudkitchens.feisolution.service.dispatchService.FIFOStrategy;
import com.cloudkitchens.feisolution.util.Constants;
import com.cloudkitchens.feisolution.util.DateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.lang.Thread.sleep;

public class KitchenDispatcherTest {

    KitchenDispatcher k;
    OrderModel order;

    @BeforeEach
    public void setUp(){
        k = new KitchenDispatcher(new FIFOStrategy());
        order = new OrderModel();
    }

    @Test
    public void testConstructor(){
        Assert.assertNotNull(k.getDispatchStrategy());
        Assert.assertNotNull(k.getFinishedOrders());
        Assert.assertNotNull(k.getCouriersQueue());
        Assert.assertNotNull(k.getOrdersQueue());
    }

    @Test
    public void testReceiveOrder(){
        k.receiveOrder(order);

        Assert.assertEquals(1, k.getOrdersQueue().size());
        Assert.assertEquals(1, k.getCouriersQueue().size());

        Assert.assertEquals(OrderState.RECEIVED, k.getOrdersQueue().peek().getState());
        Assert.assertEquals(CourierState.DISPATCHED_TO_KITCHEN, k.getCouriersQueue().peek().getState());
    }

    @Test
    public void testOnOrderPickedUp(){
        k.receiveOrder(order);
        CourierModel courier = order.getCourierDispatchedByThisOrder();
        order.setCourier(courier);

        //mock ready and arrived state
        Date mockReadyTime = new Date();
        Date mockArriveTime = DateUtil.addSecond(mockReadyTime, -1);
        order.setState(OrderState.READY, mockReadyTime);
        courier.setState(CourierState.ARRIVED_KITCHEN, mockArriveTime);

        k.onOrderPickedUp(order, courier);

        Assert.assertEquals(1, k.getFinishedOrders().size());
        Assert.assertEquals(0, k.getCouriersQueue().size());
        Assert.assertEquals(0, k.getOrdersQueue().size());
        Assert.assertEquals(mockReadyTime.getTime(), order.getPickedUpTime().getTime());
        Assert.assertEquals(mockReadyTime.getTime(), courier.getPickUpTime().getTime());
    }

    /**
     * at any time, the ordersQueue and courierQueue should has same length
     */
    @Test
    public void testThreadSafe(){
        //mock orders
        List<OrderModel> ordersList = JSON.parseArray(Constants.ORDERS_JSON, OrderModel.class);
        LinkedList<OrderModel> ordersPool = new LinkedList<>(ordersList);
        final int totalSize = ordersPool.size();

        Timer timer = new Timer();
        Random random = new Random();
        Date now = new Date();
        Thread orderReceiveThread = new Thread(()->{
            while (!ordersPool.isEmpty()) {
                OrderModel order = ordersPool.pop();
                k.receiveOrder(order);

                //mock "order ready" in another thread
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        order.setState(OrderState.READY, now);
                        k.onOrderReady(order);
                    }
                }, Long.valueOf(random.nextInt(5)));

                //mock "courier arrived" in another thread
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        CourierModel c = order.getCourierDispatchedByThisOrder();
                        c.setState(CourierState.ARRIVED_KITCHEN, now);
                        k.onCourierArrived(c);
                    }
                }, Long.valueOf(random.nextInt(5)));

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        orderReceiveThread.start();
        //check correctness
        while (totalSize != k.getFinishedOrders().size()) {
            Assert.assertTrue(k.isOrderAndCourierQueueEqual());
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
