package com.cloudkitchens.feisolution.service.dispatchService;

import com.cloudkitchens.feisolution.model.CourierState;
import com.cloudkitchens.feisolution.model.OrderModel;
import com.cloudkitchens.feisolution.model.OrderState;
import com.cloudkitchens.feisolution.util.DateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class KitchenStrategyTest {

    @Test
    public void testConstructor(){
        KitchenStrategy k = new FIFOStrategy();
        Assert.assertNotNull(k.getCouriersQueue());
        Assert.assertNotNull(k.getOrdersQueue());
    }

    @Test
    public void testOnReceiveOrder(){
        KitchenStrategy k = new FIFOStrategy();
        OrderModel o = new OrderModel();
        k.onReceiveOrder(o);

        Assert.assertEquals(k.getOrdersQueue().size(), 1);
        Assert.assertEquals(k.getCouriersQueue().size(), 1);

        Assert.assertEquals(k.getOrdersQueue().peek().getState(), OrderState.RECEIVED);
        Assert.assertEquals(k.getCouriersQueue().peek().getState(), CourierState.DISPATCHED_TO_KITCHEN);
    }

    @Test
    public void testUpdateCouriersArriveState(){
        KitchenStrategy k = new FIFOStrategy();
        OrderModel o = new OrderModel();
        k.onReceiveOrder(o);

        //make order arrived
        Date mockArriveTime = DateUtil.addSecond(new Date(), -1);
        k.getCouriersQueue().peek().setEstArriveTime(mockArriveTime);
        k.updateCouriersArriveState();

        Assert.assertEquals(k.getCouriersQueue().peek().getState(), CourierState.ARRIVED_KITCHEN);
    }

    @Test
    public void testUpdateOrderReadyState(){
        KitchenStrategy k = new FIFOStrategy();
        OrderModel o = new OrderModel();
        k.onReceiveOrder(o);

        //make order ready
        Date mockReceiveTime = DateUtil.addSecond(new Date(), -10);
        o.setReadyTime(mockReceiveTime);
        o.setPrepTime(0);
        k.updateOrderReadyState();

        Assert.assertEquals(k.getOrdersQueue().peek().getState(), OrderState.READY);
    }

}
