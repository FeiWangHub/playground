package com.cloudkitchens.feisolution.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateUtilTest {

    @Test
    public void testAddSecond(){
        Date now = new Date();
        Date result = DateUtil.addSecond(now, 10);
        Assert.assertEquals(result.getTime(), now.getTime() + 10 * 1000);
    }

}
