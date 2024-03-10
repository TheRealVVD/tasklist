package com.example.tasklist.utils.exception;

/**
 * Исключение, которое будет выбрасываться, если не найдет пользователь или задание
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
