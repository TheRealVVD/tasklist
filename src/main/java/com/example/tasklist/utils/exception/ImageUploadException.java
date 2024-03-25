package com.example.tasklist.utils.exception;

/**
 * Исключение, которое будет выбрасываться при попытке неправомерного доступа
 * Например, когда один пользователь будет пытаться получить задачи другого пользователя
 */
public class ImageUploadException extends RuntimeException {

    public ImageUploadException(String message) {
        super(message);
    }
}
