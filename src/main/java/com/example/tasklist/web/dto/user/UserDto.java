package com.example.tasklist.web.dto.user;

import com.example.tasklist.utils.validation.OnCreate;
import com.example.tasklist.utils.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "UserDTO")
public class UserDto {

    @Schema(description = "User id", example = "1")
    @NotNull(message = "ID must be not NULL", groups = OnUpdate.class)
    private Long id;

    @Schema(description = "User name", example = "John Doe")
    @NotNull(message = "Name must be not NULL", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Schema(description = "User username", example = "johndoe@gmail.com")
    @NotNull(message = "Username must be not NULL", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @Schema(description = "User password", example = "$2a$10$hjonEyJEIrhcy/wYoYqbb.utkwn6s3J1FmG0mFK2DoaTgQV8bxA9W")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // будем лишь принимать пароль, но не отправлять
    @NotNull(message = "Password must be not NULL", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @Schema(description = "User passwordConfirmation", example = "$2a$10$hjonEyJEIrhcy/wYoYqbb.utkwn6s3J1FmG0mFK2DoaTgQV8bxA9W")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // будем лишь принимать подтверждение
    @NotNull(message = "Password Confirmation must be not NULL", groups = {OnCreate.class})
    private String passwordConfirmation;

}
