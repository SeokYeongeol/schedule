package org.example.schedule.repository;

import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);

    Schedule findScheduleByIdOrElseThrow(Long id);

    Schedule findPassword(Long id);

    List<ScheduleResponseDto> findAllSchedules();

    int updateScheduleById(Long id, String name, String contents, String password);
}
