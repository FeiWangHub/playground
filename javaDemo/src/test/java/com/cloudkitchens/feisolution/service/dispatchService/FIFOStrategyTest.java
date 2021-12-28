package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.*;
import com.cloudkitchens.feisolution.util.DateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class FIFOStrategyTest {

    KitchenDispatcher k;
    OrderModel order;
    CourierModel courier;

    @BeforeEach
    public void setUp(){
        k = new KitchenDispatcher(new FIFOStrategy());
        order = new OrderModel();
        courier = new CourierModel();
    }

    @Test
    public void testOnOrderReady_noCourier(){
        k.receiveOrder(order);
        order.setState(OrderState.READY, new Date());

        //no courier arrived
        k.onOrderReady(order);
        Assert.assertEquals(OrderState.READY, order.getState());
        Assert.assertEquals(1, k.getOrdersQueue().size());
        Assert.assertEquals(1, k.getCouriersQueue().size());
        Assert.assertEquals(0, k.getFinishedOrders().size());
    }

    @Test
    public void testOnOrderReady_manyCourierArrived(){
        //mock many courier arrived waiting
        CourierModel c1 = new CourierModel();
        CourierModel c2 = new CourierModel();

        Date earliestTime = new Date();
        courier.setState(CourierState.ARRIVED_KITCHEN, earliestTime);
        c1.setState(CourierState.ARRIVED_KITCHEN, DateUtil.addSecond(earliestTime, 1));
        c2.setState(CourierState.ARRIVED_KITCHEN, DateUtil.addSecond(earliestTime, 2));

        k.getCouriersQueue().add(courier);
        k.getCouriersQueue().add(c1);
        k.getCouriersQueue().add(c2);

        order.setState(OrderState.READY, new Date());
        k.onOrderReady(order);

        Assert.assertEquals(OrderState.PICKED_UP, order.getState());
        Assert.assertNotNull(order.getCourier());
        Assert.assertEquals(earliestTime, order.getCourier().getEstArriveTime());
        Assert.assertEquals(0, k.getOrdersQueue().size());
        Assert.assertEquals(2, k.getCouriersQueue().size());
        Assert.assertEquals(1, k.getFinishedOrders().size());
    }

    @Test
    public void testOnCourierArrived_noOrderReady(){
        k.receiveOrder(order);
        courier = order.getCourierDispatchedByThisOrder();
        courier.setState(CourierState.ARRIVED_KITCHEN, new Date());
        k.onCourierArrived(courier);

        Assert.assertEquals(CourierState.ARRIVED_KITCHEN, courier.getState());
        Assert.assertEquals(OrderState.RECEIVED, order.getState());
        Assert.assertEquals(1, k.getOrdersQueue().size());
        Assert.assertEquals(1, k.getCouriersQueue().size());
        Assert.assertEquals(0, k.getFinishedOrders().size());
    }

    @Test
    public void testOnCourierArrived_manyOrderReady(){
        OrderModel o1 = new OrderModel();
        OrderModel o2 = new OrderModel();
        k.receiveOrder(order);
        k.receiveOrder(o1);
        k.receiveOrder(o2);

        Date now = new Date();
        order.setState(OrderState.READY, now);
        o1.setState(OrderState.READY, now);
        o2.setState(OrderState.READY, now);

        courier = order.getCourierDispatchedByThisOrder();
        courier.setState(CourierState.ARRIVED_KITCHEN, now);
        k.onCourierArrived(courier);

        Assert.assertEquals(OrderState.PICKED_UP, order.getState());
        Assert.assertNotNull(order.getCourier());
        Assert.assertEquals(2, k.getOrdersQueue().size());
        Assert.assertEquals(2, k.getCouriersQueue().size());
        Assert.assertEquals(1, k.getFinishedOrders().size());
    }

}
