package com.example.openapi.crontab;

import com.example.openapi.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 定时任务
 */
@Component
@EnableScheduling
public class Scheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 定时任务多实例全局锁
     *
     * @param ttl  加锁的时间单位秒.
     * @param flag 加锁标识,每个定时任务一个标识,不要重复.
     * @retrun true 表示加锁并执行成功, false表示已有其他实例加锁跳过本次任务.
     */
    private boolean runLock(String flag, long ttl) {
        String key = "scheduler-" + flag;
        String value = "lock";
        Object tv = redisUtil.get(key);
        if (tv != null && value.equals(tv.toString())) {
            return false;
        }
        redisUtil.set(key, value, ttl);
        return true;
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void syncDomainInfo() {
        boolean isRun = this.runLock("sync-domain-info", 5 * 60);
        if(isRun) {
            logger.info("crontab test");
        }
    }
}
