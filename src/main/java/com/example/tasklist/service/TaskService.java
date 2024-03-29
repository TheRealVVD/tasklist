package com.example.tasklist.service;

import com.example.tasklist.model.task.Task;
import com.example.tasklist.model.task.TaskImage;

import java.util.List;

public interface TaskService {

    Task getById(Long id);

    List<Task> getAllByUserId(Long userId);

    Task update(Task task);

    Task create(Task task, Long userId);

    void delete(Long taskId);

    void uploadImage(Long taskId, TaskImage image);

}
