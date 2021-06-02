package com.epdemic.srm.core;

import com.epdemic.srm.core.mapper.DictMapper;
import com.epdemic.srm.core.pojo.entity.Dict;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author:estic
 * @Date: 2021/6/2 14:52
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTests {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private DictMapper dictMapper;

    @Test
    public void saveDict(){

        Dict dict = dictMapper.selectById(1);
        //向数据库中存储String类型键值对，过期时间5min
        redisTemplate.opsForValue().set("dict",dict,10, TimeUnit.MINUTES);
        Dict dict1 = (Dict)redisTemplate.opsForValue().get("dict");
        System.out.println(dict1);

    }

    @Test
    public void getDict(){
        Dict dict1 = (Dict)redisTemplate.opsForValue().get("dict");
        System.out.println(dict1);
    }
}
