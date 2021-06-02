package ru.itis.zheleznov.services;

import ru.itis.zheleznov.dto.StatisticDto;
import ru.itis.zheleznov.models.Statistic;

public interface StatisticService {

    StatisticDto updateStatistic(String methodName);
}

