package controle.estoque.api.exceptionHandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredParameterException extends RuntimeException{
    private final long serialVersionUID = -3326775989913271084L;

    public RequiredParameterException() {
        super("Parametro obrigatório não preenchido");
    }

    public RequiredParameterException(String message) {
        super(message);
    }
}
