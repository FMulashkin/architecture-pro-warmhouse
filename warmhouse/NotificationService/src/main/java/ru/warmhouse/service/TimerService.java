package ru.warmhouse.service;

import ru.warmhouse.dto.TimerDto;

public interface TimerService {

    void checkTimers();

    TimerDto save(TimerDto timer);
}
