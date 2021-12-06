package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.util.DateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class OrderModelTest {

    @Test
    public void testCalWaitingTime(){
        Date now = new Date();
        Date nowPlus1 = DateUtil.addSecond(now, 1);

        OrderModel o = new OrderModel();
        o.setReadyTime(now);
        o.setPickedUpTime(nowPlus1);

        Assert.assertEquals(o.calWaitingTime(), new Float(1));
    }

    @Test
    public void testSetState(){
        Date receivedTime = new Date();
        Date readyTime = DateUtil.addSecond(receivedTime, 1);
        Date pickupTime = DateUtil.addSecond(receivedTime, 2);

        OrderModel o = new OrderModel();

        //received
        o.setState(OrderState.RECEIVED, receivedTime);
        Assert.assertEquals(o.getState(), OrderState.RECEIVED);
        Assert.assertEquals(o.getReceiveTime(), receivedTime);

        //ready
        o.setState(OrderState.READY, readyTime);
        Assert.assertEquals(o.getState(), OrderState.READY);
        Assert.assertEquals(o.getReadyTime(), readyTime);

        //picked_up
        o.setState(OrderState.PICKED_UP, pickupTime);
        Assert.assertEquals(o.getState(), OrderState.PICKED_UP);
        Assert.assertEquals(o.getPickedUpTime(), pickupTime);
    }

}
