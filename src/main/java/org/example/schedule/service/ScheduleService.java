package org.example.schedule.service;

import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    ScheduleResponseDto findScheduleById(Long id);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto updateScheduleById(Long id, String name, String contents, String password);
}
