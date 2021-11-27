package com.cloudkitchens.feisolution.util;

import org.junit.Assert;
import org.junit.Test;

public class MathUtilTest {

    @Test
    public void testGetRandomWithinRange(){
        int min = 5, max = 10;

        for (int i=0; i<10; i++){
            int result = MathUtil.getRandomWithinRange(min, max);
            Assert.assertTrue(result >= min);
            Assert.assertTrue(result <= max);
        }
    }

}
