package com.example.tasklist.web.controller;

import com.example.tasklist.model.task.Task;
import com.example.tasklist.model.user.User;
import com.example.tasklist.service.TaskService;
import com.example.tasklist.service.UserService;
import com.example.tasklist.utils.validation.OnCreate;
import com.example.tasklist.utils.validation.OnUpdate;
import com.example.tasklist.web.dto.task.TaskDto;
import com.example.tasklist.web.dto.user.UserDto;
import com.example.tasklist.web.mappers.TaskMapper;
import com.example.tasklist.web.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "UserController", description = "User API") // для красивого отображения swagger на странице
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @PutMapping
    @Operation(summary = "Update user")
    @PreAuthorize("canAccessUser(#userDto.id)")
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.update(user);


        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get UserDTO by ID")
    @PreAuthorize("canAccessUser(#id)")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);

        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("canAccessUser(#id)")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @PreAuthorize("canAccessUser(#id)")
    public List<TaskDto> getTasksByUserId(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByUserId(id);

        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    @PreAuthorize("canAccessUser(#userID)")
    public TaskDto createTask(@PathVariable Long userID,
                              @Validated(OnCreate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task createdTask = taskService.create(task, userID);

        return taskMapper.toDto(createdTask);
    }


}
