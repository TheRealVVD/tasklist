package com.example.tasklist.web.controller;

import com.example.tasklist.model.task.Task;
import com.example.tasklist.model.task.TaskImage;
import com.example.tasklist.service.TaskService;
import com.example.tasklist.utils.validation.OnUpdate;
import com.example.tasklist.web.dto.task.TaskDto;
import com.example.tasklist.web.dto.task.TaskImageDto;
import com.example.tasklist.web.mappers.TaskImageMapper;
import com.example.tasklist.web.mappers.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Tag(name = "TaskController", description = "Task API")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final TaskImageMapper taskImageMapper;

    @PutMapping
    @PreAuthorize("canAccessTask(#taskDto.id)")
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task updatedTask = taskService.update(task);
        return taskMapper.toDto(updatedTask);
    }

    @GetMapping("/{id}")
    @PreAuthorize("canAccessTask(#id)")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getById(id);

        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("canAccessTask(#id)")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PostMapping("/{id}/image")
    @Operation(summary = "Upload image to task")
    @PreAuthorize("canAccessTask(#id)")
    public void uploadImage(@PathVariable Long id,
                            @Validated @ModelAttribute TaskImageDto imageDto) {
        TaskImage image = taskImageMapper.toEntity(imageDto);
        taskService.uploadImage(id, image);

    }

}
