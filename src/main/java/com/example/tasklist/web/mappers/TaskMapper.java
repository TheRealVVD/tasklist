package com.example.tasklist.web.mappers;

import com.example.tasklist.model.task.Task;
import com.example.tasklist.web.dto.task.TaskDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(Task task);

    List<TaskDto> toDto(List<Task> tasks);

    Task toEntity(TaskDto dto);

    List<Task> toEntity(List<TaskDto> dto);

}
