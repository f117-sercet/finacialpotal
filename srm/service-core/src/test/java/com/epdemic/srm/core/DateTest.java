package com.epdemic.srm.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author:estic
 * @Date: 2021/6/9 17:27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DateTest {

    @Test
    public void test01(){

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        System.out.println(dateFormat.format(date));
    }

    @Test
    public void test02(){
        /**
         * Util包中的Calendar获取时间
         */
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        System.out.println(dateFormat.format(calendar.getTime()));

    }

    @Test
    public void test03(){

    }

}
