package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierState;
import com.cloudkitchens.feisolution.model.KitchenModel;
import com.cloudkitchens.feisolution.model.OrderModel;
import com.cloudkitchens.feisolution.model.OrderState;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class FIFOStrategyTest {

    @Test
    public void testScanAndPickupReadyOrders_noOrderReady(){
        KitchenModel k = new KitchenModel(new FIFOStrategy());

        //make sure order not ready
        OrderModel o = new OrderModel();
        k.receiveOrder(o);
        o.setState(OrderState.RECEIVED, new Date());
        List<OrderModel> result =  k.scanAndPickupReadyOrders();

        Assert.assertEquals(0, result.size());

        Assert.assertEquals(1, k.getCouriersQueue().size());
        Assert.assertEquals(1, k.getOrdersQueue().size());
        Assert.assertEquals(OrderState.RECEIVED, k.getOrdersQueue().peek().getState());
    }

    @Test
    public void testScanAndPickupReadyOrders_noCourierReady(){
        KitchenModel k = new KitchenModel(new FIFOStrategy());

        //order is ready but courier is not arrived
        OrderModel o = new OrderModel();
        k.receiveOrder(o);
        o.setState(OrderState.READY, new Date());
        k.getCouriersQueue().peek().setState(CourierState.DISPATCHED_TO_KITCHEN, new Date());
        List<OrderModel> result =  k.scanAndPickupReadyOrders();

        Assert.assertEquals(0, result.size());

        Assert.assertEquals(1, k.getCouriersQueue().size());
        Assert.assertEquals(1, k.getOrdersQueue().size());
        Assert.assertEquals(OrderState.READY, k.getOrdersQueue().peek().getState());
    }

    @Test
    public void testScanAndPickupReadyOrders_1CourierArrived(){
        KitchenModel k = new KitchenModel(new FIFOStrategy());

        // 2 orders are ready, and couriers is arrived
        OrderModel o1 = new OrderModel();
        OrderModel o2 = new OrderModel();
        k.receiveOrder(o1);
        k.receiveOrder(o2);
        o1.setState(OrderState.READY, new Date());
        o2.setState(OrderState.READY, new Date());
        k.getCouriersQueue().peek().setState(CourierState.ARRIVED_KITCHEN, new Date());
        List<OrderModel> result =  k.scanAndPickupReadyOrders();

        Assert.assertEquals(1, result.size());

        Assert.assertEquals(1, k.getCouriersQueue().size());//1 picked, and 1 gone
        Assert.assertEquals(CourierState.DISPATCHED_TO_KITCHEN, k.getCouriersQueue().peek().getState());
        Assert.assertEquals(1, k.getOrdersQueue().size());
        Assert.assertEquals(OrderState.READY, k.getOrdersQueue().peek().getState());
    }

}
