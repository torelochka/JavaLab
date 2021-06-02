package ru.itis.zheleznov.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zheleznov.dto.StatisticDto;
import ru.itis.zheleznov.models.Statistic;
import ru.itis.zheleznov.repositories.StatisticRepository;

import java.util.Optional;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public StatisticServiceImpl(StatisticRepository statisticRepository, ModelMapper modelMapper) {
        this.statisticRepository = statisticRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StatisticDto updateStatistic(String methodName) {
        Optional<Statistic> statistic = statisticRepository.findAllByMethodName(methodName);

        if (statistic.isPresent()) {
            statistic.get().setCount(statistic.get().getCount() + 1);

            statisticRepository.save(statistic.get());
            return modelMapper.map(statistic.get(), StatisticDto.class);
        }

        Statistic newStatistic = Statistic.builder()
                .methodName(methodName)
                .count(1)
                .build();

        Statistic dbStatistic = statisticRepository.save(newStatistic);

        return modelMapper.map(dbStatistic, StatisticDto.class);
    }
}
