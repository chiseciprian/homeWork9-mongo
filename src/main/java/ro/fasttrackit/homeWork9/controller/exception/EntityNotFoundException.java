package ro.fasttrackit.homeWork9.controller.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String msg) {
        super(msg);
    }

}
