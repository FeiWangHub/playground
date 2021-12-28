package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.service.dispatchService.FIFOStrategy;
import com.cloudkitchens.feisolution.util.DateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

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


    @Test
    public void testThreadSafe(){
        //TODO
        //use 100 thread, keep receive/dispatch order. the total of orders should be always equals

    }

}
