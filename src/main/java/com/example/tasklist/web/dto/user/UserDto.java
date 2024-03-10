package com.example.tasklist.web.dto.user;

import com.example.tasklist.utils.validation.OnCreate;
import com.example.tasklist.utils.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {

    @NotNull(message = "ID must be not NULL", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name must be not NULL", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "Username must be not NULL", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // будем лишь принимать пароль, но не отправлять
    @NotNull(message = "Password must be not NULL", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // будем лишь принимать подтверждение
    @NotNull(message = "Password Confirmation must be not NULL", groups = {OnCreate.class})
    private String passwordConfirmation;

}
