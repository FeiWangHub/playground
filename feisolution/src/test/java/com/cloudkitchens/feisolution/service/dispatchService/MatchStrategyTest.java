package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierState;
import com.cloudkitchens.feisolution.model.KitchenDispatcher;
import com.cloudkitchens.feisolution.model.OrderModel;
import com.cloudkitchens.feisolution.model.OrderState;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class MatchStrategyTest {

    KitchenDispatcher k;
    OrderModel o1, o2, o3;
    Date now;

    @BeforeEach
    public void setUp(){
        k = new KitchenDispatcher(new MatchStrategy());
        o1 = new OrderModel();
        o2 = new OrderModel();
        o3 = new OrderModel();
        k.receiveOrder(o1);
        k.receiveOrder(o2);
        k.receiveOrder(o3);
        now = new Date();
    }

    @Test
    public void testAfterReceiveOrder() {
        //the order received, is immediately assigned courier
        Assert.assertNotNull(o1.getCourier());
        Assert.assertEquals(o1.getCourier(), o1.getCourierDispatchedByThisOrder());
    }

    @Test
    public void testOnOrderReady_courierNotArrived(){
        //courier not arrived
        o1.setState(OrderState.READY, now);
        k.onOrderReady(o1);
        Assert.assertEquals(OrderState.READY, o1.getState());
        Assert.assertEquals(0, k.getFinishedOrders().size());
    }

    @Test
    public void testOnOrderReady_manyCourierArrived(){
        //many couriers arrived
        o1.getCourierDispatchedByThisOrder().setState(CourierState.ARRIVED_KITCHEN, now);
        o2.getCourierDispatchedByThisOrder().setState(CourierState.ARRIVED_KITCHEN, now);
        o3.getCourierDispatchedByThisOrder().setState(CourierState.ARRIVED_KITCHEN, now);

        o1.setState(OrderState.READY, now);
        k.onOrderReady(o1);

        Assert.assertEquals(OrderState.PICKED_UP, o1.getState());
        Assert.assertEquals(o1.getCourier(), o1.getCourierDispatchedByThisOrder());
        Assert.assertEquals(1, k.getFinishedOrders().size());
    }

    @Test
    public void testOnCourierArrived_orderNotReady(){
        o1.getCourierDispatchedByThisOrder().setState(CourierState.ARRIVED_KITCHEN, now);
        o2.getCourierDispatchedByThisOrder().setState(CourierState.ARRIVED_KITCHEN, now);
        o3.getCourierDispatchedByThisOrder().setState(CourierState.ARRIVED_KITCHEN, now);
        k.onCourierArrived(o1.getCourierDispatchedByThisOrder());

        Assert.assertEquals(OrderState.RECEIVED, o1.getState());
        Assert.assertEquals(CourierState.ARRIVED_KITCHEN, o1.getCourierDispatchedByThisOrder().getState());
        Assert.assertEquals(0, k.getFinishedOrders().size());
    }

    @Test
    public void testOnCourierArrived_manyOrdersReady(){
        o1.setState(OrderState.READY, now);
        o2.setState(OrderState.READY, now);
        o3.setState(OrderState.READY, now);
        o1.getCourierDispatchedByThisOrder().setState(CourierState.ARRIVED_KITCHEN, now);
        k.onCourierArrived(o1.getCourierDispatchedByThisOrder());

        Assert.assertEquals(OrderState.PICKED_UP, o1.getState());
        Assert.assertEquals(o1.getCourier(), o1.getCourierDispatchedByThisOrder());
        Assert.assertEquals(1, k.getFinishedOrders().size());
    }
}
