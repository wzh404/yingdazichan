package com.xeehoo.p2p.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wangzunhui on 2016/1/15.
 */
@Component
@EnableScheduling
public class ScheduledTasks {
    private final Logger logger = Logger.getLogger(ScheduledTasks.class);

    @Scheduled(fixedRate=3000)
    public void repay(){
        logger.info("----------task repay---------");
    }
}
