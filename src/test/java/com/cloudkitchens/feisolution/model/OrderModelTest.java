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

        Assert.assertEquals(new Long(1000), o.calWaitingTime());
    }

    @Test
    public void testSetState(){
        Date receivedTime = new Date();
        Date readyTime = DateUtil.addSecond(receivedTime, 1);
        Date pickupTime = DateUtil.addSecond(receivedTime, 2);

        CourierModel c = new CourierModel();
        OrderModel o = new OrderModel();
        o.setCourier(c);

        //received
        o.setState(OrderState.RECEIVED, receivedTime);
        Assert.assertEquals(OrderState.RECEIVED, o.getState());
        Assert.assertEquals(receivedTime, o.getReceiveTime());

        //ready
        o.setState(OrderState.READY, readyTime);
        Assert.assertEquals(OrderState.READY, o.getState());
        Assert.assertEquals(readyTime, o.getReadyTime());

        //picked_up
        o.setState(OrderState.PICKED_UP, pickupTime);
        Assert.assertEquals(OrderState.PICKED_UP, o.getState());
        Assert.assertEquals(pickupTime, o.getPickedUpTime());
    }

}
