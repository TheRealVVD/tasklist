package com.example.tasklist.web.dto.task;

import com.example.tasklist.model.task.Status;
import com.example.tasklist.utils.validation.OnCreate;
import com.example.tasklist.utils.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    @NotNull(message = "ID must be not NULL", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Title must be not NULL", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Title length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Length(max = 255, message = "Description length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String description;


    private Status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;

}
