package com.xeehoo.p2p.task;

import com.xeehoo.p2p.service.LoanRepayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LoanRepayService repayService;

    @Scheduled(cron="0 0 9 * * ?")
    public void repay(){
        logger.info("----------task repay---------");
        repayService.repayNow();
    }
}
