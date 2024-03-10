package com.example.tasklist.utils.exception;

/**
 * Исключение, выбрасываемое при неудачной попытке достать данные из БД
 */
public class ResourceMappingException extends RuntimeException {

    public ResourceMappingException(String message) {
        super(message);
    }

}
