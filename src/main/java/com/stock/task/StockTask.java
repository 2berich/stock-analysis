package com.stock.task;

import com.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lei
 * @date 2019/4/19
 **/
@Slf4j
@Component
public class StockTask {

    @Autowired
    private StockService stockService;

    @Scheduled(cron = "* 0/5 * * * *")
    public void scheduled() {
        stockService.fetchBehaviours();
    }
}
