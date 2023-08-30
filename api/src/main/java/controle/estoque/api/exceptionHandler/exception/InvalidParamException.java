package controle.estoque.api.exceptionHandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParamException extends RuntimeException{
    private final long serialVersionUID = -3326775989913271084L;

    public InvalidParamException() {
        super("Parametro preenchido de maneira inválida");
    }

    public InvalidParamException(String message) {
        super(message);
    }
}
