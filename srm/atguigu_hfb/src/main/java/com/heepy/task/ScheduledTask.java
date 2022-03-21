package com.heepy.task;

import com.heepy.config.NotifyThread;
import com.heepy.model.NotifyVo;
import com.sun.corba.se.spi.orbutil.threadpool.NoSuchThreadPoolException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description： 定时任务
 *
 * @author: 段世超
 * @aate: Created in 2022/3/21 9:25
 */
@Component
@Slf4j
@EnableScheduling
public class ScheduledTask {

    public static Queue<NotifyVo> queue = new LinkedList<>();

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;


    /**
     * 每秒钟执行一次
     */
    @Scheduled(cron = "0/1 * *  * * ?")
    public void task1() {
        log.info("task1");
        for(int i=0; i<5; i++) {
            NotifyVo notifyVo = queue.poll();
            if(null != notifyVo) {
                threadPoolExecutor.submit(new NotifyThread(notifyVo.getNotifyUrl(), notifyVo.getParamMap()));
            }
        }
    }
}