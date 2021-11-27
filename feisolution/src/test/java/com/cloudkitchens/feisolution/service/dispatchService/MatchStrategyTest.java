package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierModel;
import com.cloudkitchens.feisolution.model.CourierState;
import com.cloudkitchens.feisolution.model.OrderModel;
import com.cloudkitchens.feisolution.model.OrderState;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class MatchStrategyTest {

    @Test
    public void testAfterReceiveOrder(){
        MatchStrategy s = new MatchStrategy();
        OrderModel o = new OrderModel();
        s.onReceiveOrder(o);

        //the order received, is immediately assigned courier
        Assert.assertNotNull(o.getCourierId());
    }

    @Test
    public void testScanAndPickupReadyOrders_noReady(){
        MatchStrategy s = new MatchStrategy();

        //make sure order not ready
        OrderModel o = new OrderModel();
        s.onReceiveOrder(o);
        o.setState(OrderState.RECEIVED, new Date());
        List<OrderModel> result =  s.scanAndPickupReadyOrders();

        Assert.assertEquals(result.size(), 0);

        Assert.assertEquals(s.getCouriersQueue().size(), 1);
        Assert.assertEquals(s.getOrdersQueue().size(), 1);
        Assert.assertEquals(s.getOrdersQueue().peek().getState(), OrderState.RECEIVED);
    }

    @Test
    public void testScanAndPickupReadyOrders_noCourierMatched(){
        MatchStrategy s = new MatchStrategy();

        // 2 orders are ready, but no couriers matched
        OrderModel o1 = new OrderModel();
        OrderModel o2 = new OrderModel();
        s.onReceiveOrder(o1);
        s.onReceiveOrder(o2);
        o1.setState(OrderState.READY, new Date());
        o2.setState(OrderState.READY, new Date());
        // no couriers matched
        Iterator<CourierModel> it = s.getCouriersQueue().iterator();
        while (it.hasNext()){
            CourierModel c = it.next();
            c.setId(UUID.randomUUID().toString() + "test");
            c.setState(CourierState.ARRIVED_KITCHEN, new Date());
        }

        List<OrderModel> result =  s.scanAndPickupReadyOrders();

        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void testScanAndPickupReadyOrders_1matched(){
        MatchStrategy s = new MatchStrategy();

        // 2 orders are ready, 1 courier arrived
        OrderModel o1 = new OrderModel();
        OrderModel o2 = new OrderModel();
        s.onReceiveOrder(o1);
        s.onReceiveOrder(o2);
        o1.setState(OrderState.READY, new Date());
        o2.setState(OrderState.READY, new Date());
        // 1 courier arrived
        s.getCouriersQueue().peek().setState(CourierState.ARRIVED_KITCHEN, new Date());
        List<OrderModel> result =  s.scanAndPickupReadyOrders();

        Assert.assertEquals(result.size(), 1);
    }

}
