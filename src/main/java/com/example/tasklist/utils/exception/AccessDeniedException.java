package com.example.tasklist.utils.exception;

/**
 * Исключение, которое будет выбрасываться при попытке неправомерного доступа
 * Например, когда один пользователь будет пытаться получить задачи другого пользователя
 */
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super();
    }
}
