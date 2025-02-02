package org.example.schedule.repository;

import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);

    Schedule findScheduleByIdOrElseThrow(Long id);

    List<ScheduleResponseDto> findAllSchedules();
}
