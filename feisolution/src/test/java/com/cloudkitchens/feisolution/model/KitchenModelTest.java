package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.service.dispatchService.FIFOStrategy;
import com.cloudkitchens.feisolution.util.DateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class KitchenModelTest {

    @Test
    public void testConstructor(){
        KitchenModel k = new KitchenModel(new FIFOStrategy());

        Assert.assertNotNull(k.getKitchenStrategy());
        Assert.assertNotNull(k.getDispatchedOrders());
        Assert.assertNotNull(k.getCouriersQueue());
        Assert.assertNotNull(k.getOrdersQueue());
    }

    @Test
    public void testReceiveOrder(){
        KitchenModel k = new KitchenModel(new FIFOStrategy());
        OrderModel o = new OrderModel();
        k.receiveOrder(o);

        Assert.assertEquals(k.getOrdersQueue().size(), 1);
        Assert.assertEquals(k.getCouriersQueue().size(), 1);

        Assert.assertEquals(k.getOrdersQueue().peek().getState(), OrderState.RECEIVED);
        Assert.assertEquals(k.getCouriersQueue().peek().getState(), CourierState.DISPATCHED_TO_KITCHEN);
    }

    @Test
    public void testUpdateCouriersArriveState(){
        KitchenModel k = new KitchenModel(new FIFOStrategy());
        OrderModel o = new OrderModel();
        k.receiveOrder(o);

        //make order arrived
        Date mockArriveTime = DateUtil.addSecond(new Date(), -1);
        k.getCouriersQueue().peek().setEstArriveTime(mockArriveTime);
        k.updateCouriersArriveState();

        Assert.assertEquals(k.getCouriersQueue().peek().getState(), CourierState.ARRIVED_KITCHEN);
    }

    @Test
    public void testUpdateOrderReadyState(){
        KitchenModel k = new KitchenModel(new FIFOStrategy());
        OrderModel o = new OrderModel();
        k.receiveOrder(o);

        //make order ready
        o.setPrepTime(-10);
        k.updateOrderReadyState();
        k.scanAndPickupReadyOrders();

        Assert.assertEquals(OrderState.READY, k.getOrdersQueue().peek().getState());
    }

    @Test
    public void testScanAndPickupReadyOrders(){
        KitchenModel k = new KitchenModel(new FIFOStrategy());

        //make sure ready order are added into dispatched orders
        OrderModel o1 = new OrderModel();
        OrderModel o2 = new OrderModel();
        k.receiveOrder(o1);
        k.receiveOrder(o2);
        o1.setState(OrderState.READY, new Date());
        o2.setState(OrderState.READY, new Date());
        //1 courier arrived
        k.getCouriersQueue().peek().setState(CourierState.ARRIVED_KITCHEN, new Date());
        k.updateOrderReadyState();
        k.scanAndPickupReadyOrders();

        Assert.assertEquals(1, k.getDispatchedOrders().size());
    }

}
