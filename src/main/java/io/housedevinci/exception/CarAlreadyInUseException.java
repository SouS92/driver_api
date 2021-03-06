package io.housedevinci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CarAlreadyInUseException extends Exception {
    static final long serialVersionUID = 5484292684098485784L;

    public CarAlreadyInUseException(String message)
    {
        super(message);
    }
}
