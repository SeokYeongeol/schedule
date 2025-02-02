package org.example.schedule.service;

import org.example.schedule.dto.ScheduleRequestDto;
import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getName(), dto.getPassword(), dto.getTitle(), dto.getContents(), new Date());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return new ScheduleResponseDto(scheduleRepository.findScheduleByIdOrElseThrow(id));
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() { return scheduleRepository.findAllSchedules(); }

    @Transactional
    @Override
    public ScheduleResponseDto updateScheduleById(Long id, String name, String contents, String password) {
        if(name == null || contents == null || password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name, contents and password are required values.");
        }
        else if(!password.equals(scheduleRepository.findPassword(id).getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect");
        }

        int updatedRow = scheduleRepository.updateScheduleById(id, name, contents, password);

        if(updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id : " + id);
        }

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateScheduleTitleById(Long id, String title, String password) {
        if(title == null || password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and password are required values.");
        }
        else if(!password.equals(scheduleRepository.findPassword(id).getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect");
        }

        int updatedRow = scheduleRepository.updateScheduleTitleById(id, title, password);

        if(updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id : " + id);
        }

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public void deleteScheduleById(Long id, String password) {
        if(password == null) { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is required value."); }

        else if(!password.equals(scheduleRepository.findPassword(id).getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect");
        }

        int deletedRow = scheduleRepository.deleteScheduleById(id, password);

        if(deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id : " + id);
        }
    }
}