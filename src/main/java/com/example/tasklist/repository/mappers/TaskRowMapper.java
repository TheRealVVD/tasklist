package com.example.tasklist.repository.mappers;

import com.example.tasklist.model.task.Status;
import com.example.tasklist.model.task.Task;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TaskRowMapper {

    @SneakyThrows
    public static Task mapRow(ResultSet rs) {
        if (rs.next()) {
            Task task = new Task();

            task.setId(rs.getLong("task_id"));
            task.setTitle(rs.getString("task_title"));
            task.setDescription(rs.getString("task_description"));
            task.setStatus(Status.valueOf(rs.getString("task_status")));

            Timestamp timestamp = rs.getTimestamp("task_expiration_date");
            if (timestamp != null) {
                task.setExpirationDate(timestamp.toLocalDateTime());
            }

            return task;
        }
        return null;
    }

    @SneakyThrows
    public static List<Task> mapRows(ResultSet rs) {

        List<Task> tasks = new ArrayList<>();

        while (rs.next()) {
            Task task = new Task();

            task.setId(rs.getLong("task_id"));
            if (!rs.wasNull()) {
                task.setTitle(rs.getString("task_title"));
                task.setDescription(rs.getString("task_description"));
                task.setStatus(Status.valueOf(rs.getString("task_status")));

                Timestamp timestamp = rs.getTimestamp("task_expiration_date");
                if (timestamp != null) {
                    task.setExpirationDate(timestamp.toLocalDateTime());
                }
                tasks.add(task);
            }
        }
        return tasks;
    }

}
