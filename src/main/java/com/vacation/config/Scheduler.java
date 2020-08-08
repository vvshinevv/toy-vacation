package com.vacation.config;

import com.vacation.service.VacationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Scheduler {

    @Autowired
    private VacationService vacationService;

    /**
     * 1년 매월 1월 1일에 휴가 지급 스케줄러
     * **/
    @Scheduled(cron = "0 0 1 1 ?")
    public void init() {
        log.debug("연차 지급 스케줄러 시작!!");
        vacationService.assignAnnualVacationToAllMember();
    }
}
