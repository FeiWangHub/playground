package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.util.DateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class CourierModelTest {

    CourierModel courier;

    @BeforeEach
    public void setUp(){
        courier = new CourierModel();
    }

    @Test
    public void testWaitingTime(){
        Date now = new Date();
        Date nowPlus1 = DateUtil.addSecond(now, 1);

        courier.setEstArriveTime(now);
        courier.setPickUpTime(nowPlus1);

        Assert.assertEquals(new Long(1000), courier.calWaitingTime());
    }

    @Test
    public void testSetState(){
        Date dispatch_time = new Date();
        Date arrived_time = DateUtil.addSecond(dispatch_time, 1);
        Date pickup_time = DateUtil.addSecond(arrived_time, 1);

        Assert.assertNull(courier.getState());

        courier.setState(CourierState.DISPATCHED_TO_KITCHEN, dispatch_time);
        Assert.assertEquals(CourierState.DISPATCHED_TO_KITCHEN, courier.getState());
        Assert.assertNotNull(courier.getEstArriveTime());

        courier.setState(CourierState.ARRIVED_KITCHEN, arrived_time);
        Assert.assertEquals(CourierState.ARRIVED_KITCHEN, courier.getState());

        courier.setState(CourierState.PICKED_UP_ORDER, pickup_time);
        Assert.assertEquals(CourierState.PICKED_UP_ORDER, courier.getState());
    }

    @Test
    public void testCalMockArriveTime(){
        Date orderReceiveTime = new Date();
        Date result = courier.calMockArriveTime(orderReceiveTime);
        Assert.assertTrue(result.after(orderReceiveTime));
    }

}
