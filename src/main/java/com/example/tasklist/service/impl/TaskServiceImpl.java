package com.example.tasklist.service.impl;

import com.example.tasklist.model.task.Status;
import com.example.tasklist.model.task.Task;
import com.example.tasklist.model.task.TaskImage;
import com.example.tasklist.model.user.User;
import com.example.tasklist.repository.TaskRepository;
import com.example.tasklist.service.ImageService;
import com.example.tasklist.service.TaskService;
import com.example.tasklist.service.UserService;
import com.example.tasklist.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ImageService imageService;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "TaskService::getById", key = "#id")
    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    @CachePut(value = "TaskService::getById", key = "#task.id")
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO);
        }

        taskRepository.save(task);

        return task;
    }

    @Override
    @Transactional
    @Cacheable(value = "TaskService::getById", key = "#task.id")
    public Task create(Task task, Long userId) {

        User user = userService.getById(userId);
        task.setStatus(Status.TODO);
        user.getTasks().add(task);
        userService.update(user);
//
//        taskRepository.save(task);
//        taskRepository.assignToUserById(task.getId(), userId);

        return task;
    }

    @Override
    @Transactional
    @CacheEvict(value = "TaskService::delete", key = "#taskId")
    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    @CacheEvict(value = "TaskService::getById", key = "#taskId")
    public void uploadImage(Long taskId, TaskImage image) {
        Task task = getById(taskId);
        String fileName = imageService.upload(image);
        task.getImages().add(fileName);
        taskRepository.save(task);
    }
}
