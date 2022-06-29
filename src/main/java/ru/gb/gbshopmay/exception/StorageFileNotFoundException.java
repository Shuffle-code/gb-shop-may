package ru.gb.gbshopmay.exception;

/**
 * @author Artem Kropotov
 * created at 26.06.2022
 **/
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
