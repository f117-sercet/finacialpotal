package com.atguigu.srb.core;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @org.junit.Test
    public  void test(){

        StringBuilder d= new StringBuilder("dsc");
        StringBuilder ds= new StringBuilder("dsc");
        String dsc = String.valueOf(d.append(ds));
        System.out.println(dsc);

    }
}
