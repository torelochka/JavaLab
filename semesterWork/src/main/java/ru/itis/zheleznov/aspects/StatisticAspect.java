package ru.itis.zheleznov.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.zheleznov.dto.StatisticDto;
import ru.itis.zheleznov.models.Statistic;
import ru.itis.zheleznov.services.StatisticService;

@Component
@Aspect
public class StatisticAspect {

    private final StatisticService statisticService;

    @Autowired
    public StatisticAspect(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @After("execution(* ru.itis.zheleznov.controllers.BasketController.*(..))")
    private void methodStatistic(JoinPoint joinPoint) {
        StatisticDto statisticDto = statisticService.updateStatistic(joinPoint.getSignature().getName());
        System.out.println("method statistic " + statisticDto);
    }
}
