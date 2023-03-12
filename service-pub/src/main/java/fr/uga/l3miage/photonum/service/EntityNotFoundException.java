package fr.uga.l3miage.photonum.service;

/**
 * Thrown when a service is unable to fetch
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
