package org.example.schedule.repository;

import org.example.schedule.dto.ScheduleResponseDto;
import org.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", schedule.getName());
        parameters.put("password", schedule.getPassword());
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());
        parameters.put("createdAt", schedule.getCreatedAt());
        parameters.put("modifiedAt", schedule.getModifiedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getName(), schedule.getPassword(), schedule.getTitle(), schedule.getContents(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id : " + id));
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule", scheduleRowMapperV2());
        return result;
    }

    @Override
    public Schedule findPassword(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV3(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id : " + id));
    }

    @Override
    public int updateScheduleById(Long id, String name, String contents, String password) {
        return jdbcTemplate.update("update schedule set name = ?, contents = ?, password = ?, modifiedAt = now() where id = ?", name, contents, password, id);
    }

    @Override
    public int updateScheduleTitleById(Long id, String title, String password) {
        return jdbcTemplate.update("update schedule set title = ?, password = ?, modifiedAt = now() where id = ?", title, password, id);
    }

    @Override
    public int deleteScheduleById(Long id, String password) {
        return jdbcTemplate.update("delete from schedule where id = ? and password = ?", id, password);
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("modifiedAt").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapperV2() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("modifiedAt").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV3() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("password")
                );
            }
        };
    }
}
