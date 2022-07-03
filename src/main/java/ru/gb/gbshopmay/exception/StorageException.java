package ru.gb.gbshopmay.exception;

/**
 * @author Artem Kropotov
 * created at 26.06.2022
 **/
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
