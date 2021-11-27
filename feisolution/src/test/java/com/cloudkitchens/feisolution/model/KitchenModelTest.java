package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.service.dispatchService.FIFOStrategy;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class KitchenModelTest {

    @Test
    public void testConstructor(){
        KitchenModel k = new KitchenModel(new FIFOStrategy());

        Assert.assertNotNull(k.getKitchenStrategy());
        Assert.assertNotNull(k.getDispatchedOrders());
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
        k.getKitchenStrategy().getCouriersQueue().peek().setState(CourierState.ARRIVED_KITCHEN, new Date());
        k.updateOrderReadyState();

        Assert.assertEquals(k.getDispatchedOrders().size(), 1);
    }

}
