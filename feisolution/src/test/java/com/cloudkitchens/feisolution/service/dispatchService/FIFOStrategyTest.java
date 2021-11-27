package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierState;
import com.cloudkitchens.feisolution.model.OrderModel;
import com.cloudkitchens.feisolution.model.OrderState;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class FIFOStrategyTest {

    @Test
    public void testScanAndPickupReadyOrders_noOrderReady(){
        FIFOStrategy f = new FIFOStrategy();

        //make sure order not ready
        OrderModel o = new OrderModel();
        f.onReceiveOrder(o);
        o.setState(OrderState.RECEIVED, new Date());
        List<OrderModel> result =  f.scanAndPickupReadyOrders();

        Assert.assertEquals(result.size(), 0);

        Assert.assertEquals(f.getCouriersQueue().size(), 1);
        Assert.assertEquals(f.getOrdersQueue().size(), 1);
        Assert.assertEquals(f.getOrdersQueue().peek().getState(), OrderState.RECEIVED);
    }

    @Test
    public void testScanAndPickupReadyOrders_noCourierReady(){
        FIFOStrategy f = new FIFOStrategy();

        //order is ready but courier is not arrived
        OrderModel o = new OrderModel();
        f.onReceiveOrder(o);
        o.setState(OrderState.READY, new Date());
        f.getCouriersQueue().peek().setState(CourierState.DISPATCHED_TO_KITCHEN, new Date());
        List<OrderModel> result =  f.scanAndPickupReadyOrders();

        Assert.assertEquals(result.size(), 0);

        Assert.assertEquals(f.getCouriersQueue().size(), 1);
        Assert.assertEquals(f.getOrdersQueue().size(), 1);
        Assert.assertEquals(f.getOrdersQueue().peek().getState(), OrderState.READY);
    }

    @Test
    public void testScanAndPickupReadyOrders_1CourierArrived(){
        FIFOStrategy f = new FIFOStrategy();

        // 2 orders are ready, and couriers is arrived
        OrderModel o1 = new OrderModel();
        OrderModel o2 = new OrderModel();
        f.onReceiveOrder(o1);
        f.onReceiveOrder(o2);
        o1.setState(OrderState.READY, new Date());
        o2.setState(OrderState.READY, new Date());
        f.getCouriersQueue().peek().setState(CourierState.ARRIVED_KITCHEN, new Date());
        List<OrderModel> result =  f.scanAndPickupReadyOrders();

        Assert.assertEquals(result.size(), 1);

        Assert.assertEquals(f.getCouriersQueue().size(), 1);//1 picked, and 1 gone
        Assert.assertEquals(f.getCouriersQueue().peek().getState(), CourierState.DISPATCHED_TO_KITCHEN);
        Assert.assertEquals(f.getOrdersQueue().size(), 1);
        Assert.assertEquals(f.getOrdersQueue().peek().getState(), OrderState.READY);
    }

}
