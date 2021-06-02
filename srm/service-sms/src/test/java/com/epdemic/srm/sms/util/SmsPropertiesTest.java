package com.epdemic.srm.sms.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author:estic
 * @Date: 2021/6/2 17:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
 public class SmsPropertiesTest {

    @Test
    public void testProperties(){
        System.out.println(SmsProperties.KEY_ID);
        System.out.println(SmsProperties.KEY_SECRET);
        System.out.println(SmsProperties.REGION_Id);

    }

}
