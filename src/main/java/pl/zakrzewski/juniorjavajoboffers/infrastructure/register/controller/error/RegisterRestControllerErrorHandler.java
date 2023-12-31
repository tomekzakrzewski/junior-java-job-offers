package pl.zakrzewski.juniorjavajoboffers.infrastructure.register.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.zakrzewski.juniorjavajoboffers.domain.register.exceptions.*;

@ControllerAdvice
@Log4j2
public class RegisterRestControllerErrorHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExists(UserAlreadyExistException exception) {
        String message = exception.getMessage();
        log.error(message);
        return new ErrorResponse(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidEmailAddressException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidEmailAddress(InvalidEmailAddressException exception) {
        String message = exception.getMessage();
        log.error(message);
        return new ErrorResponse(message, HttpStatus.BAD_REQUEST);
    }
}
