package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class MatchStrategyTest {

    @Test
    public void testAfterReceiveOrder() {
        KitchenDispatcher k = new KitchenDispatcher(new MatchStrategy());
        OrderModel o = new OrderModel();
        k.receiveOrder(o);

        //the order received, is immediately assigned courier
        Assert.assertNotNull(o.getCourier());
    }

    @Test
    public void testScanAndPickupReadyOrders_noReady() {
//        KitchenDispatcher k = new KitchenDispatcher(new MatchStrategy());
//
//        //make sure order not ready
//        OrderModel o = new OrderModel();
//        k.receiveOrder(o);
//        o.setState(OrderState.RECEIVED, new Date());
//        List<OrderModel> result = k.scanAndPickupReadyOrders();
//
//        Assert.assertEquals(0, result.size());
//
//        Assert.assertEquals(1, k.getCouriersQueue().size());
//        Assert.assertEquals(1, k.getOrdersQueue().size());
//        Assert.assertEquals(OrderState.RECEIVED, k.getOrdersQueue().peek().getState());
    }

    @Test
    public void testScanAndPickupReadyOrders_noCourierMatched() {
//        KitchenDispatcher k = new KitchenDispatcher(new MatchStrategy());
//
//        // 2 orders are ready, but no couriers matched
//        OrderModel o1 = new OrderModel();
//        OrderModel o2 = new OrderModel();
//        k.receiveOrder(o1);
//        k.receiveOrder(o2);
//        o1.setState(OrderState.READY, new Date());
//        o2.setState(OrderState.READY, new Date());
//
//        //mock no couriers matched
//        k.getCouriersQueue().clear();
//        for (int i = 0; i < 2; i++) {
//            CourierModel c = new CourierModel();
//            c.setId("test" + i);
//            c.setState(CourierState.ARRIVED_KITCHEN, new Date());
//            k.getCouriersQueue().add(c);
//        }
//
//        List<OrderModel> result = k.scanAndPickupReadyOrders();
//        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testScanAndPickupReadyOrders_1matched() {
//        KitchenDispatcher k = new KitchenDispatcher(new MatchStrategy());
//
//        // 2 orders are ready, 1 courier arrived
//        OrderModel o1 = new OrderModel();
//        OrderModel o2 = new OrderModel();
//        k.receiveOrder(o1);
//        k.receiveOrder(o2);
//        o1.setState(OrderState.READY, new Date());
//        o2.setState(OrderState.READY, new Date());
//        // 1 courier arrived
//        k.getCouriersQueue().peek().setState(CourierState.ARRIVED_KITCHEN, new Date());
//        List<OrderModel> result = k.scanAndPickupReadyOrders();
//
//        Assert.assertEquals(1, result.size());
    }

}
