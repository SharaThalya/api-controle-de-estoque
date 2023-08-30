package controle.estoque.api.exceptionHandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3326775989913271084L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
