package io.github.wkktoria.wlepka.exception;

import lombok.Getter;

@Getter
public class NotUniqueException extends RuntimeException {

    private final String field;

    public NotUniqueException(final String field, final String message) {
        super(message);
        this.field = field;
    }

}
