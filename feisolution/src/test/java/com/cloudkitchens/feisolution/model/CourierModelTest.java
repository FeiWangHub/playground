package com.cloudkitchens.feisolution.model;

import com.cloudkitchens.feisolution.util.DateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class CourierModelTest {

    @Test
    public void testWaitingTime(){
        Date now = new Date();
        Date nowPlus1 = DateUtil.addSecond(now, 1);

        CourierModel co = new CourierModel();
        co.setEstArriveTime(now);
        co.setPickUpTime(nowPlus1);

        Assert.assertEquals(co.calWaitingTime(), new Float(1));
    }

}
