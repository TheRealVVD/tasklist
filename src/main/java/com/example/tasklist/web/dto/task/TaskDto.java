package com.example.tasklist.web.dto.task;

import com.example.tasklist.model.task.Status;
import com.example.tasklist.utils.validation.OnCreate;
import com.example.tasklist.utils.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Task DTO")
public class TaskDto {

    @Schema(description = "Task id", example = "1")
    @NotNull(message = "ID must be not NULL", groups = OnUpdate.class)
    private Long id;

    @Schema(description = "Task title", example = "Buy cheese")
    @NotNull(message = "Title must be not NULL", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Title length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Schema(description = "Task description", example = "Ask about gym")
    @Length(max = 255, message = "Description length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String description;

    @Schema(description = "Task status", example = "IN_PROGRESS")
    private Status status;

    @Schema(description = "Task expirationDate", example = "2023-02-02 11:00:00.000000")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //добавляем картинки через контроллер
    private List<String> images;

}
