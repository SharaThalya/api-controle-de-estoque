package controle.estoque.api.exceptionHandler;

import controle.estoque.api.exceptionHandler.exception.InvalidParamException;
import controle.estoque.api.exceptionHandler.exception.RequiredParameterException;
import controle.estoque.api.exceptionHandler.exception.ResourceNotFoundException;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public final ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception ex, WebRequest request){
        ApiErrorResponse exceptionResponse = new ApiErrorResponse(500, new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ApiErrorResponse> handleNotFoundExceptions(Exception ex, WebRequest request){
        ApiErrorResponse exceptionResponse = ApiErrorResponse.builder().status(404).data(new Date()).mensagem(ex.getMessage()).detalhes(request.getDescription(false)).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequiredParameterException.class)
    public final ResponseEntity<ApiErrorResponse> handleRequiredObject(Exception ex, WebRequest request){
        ApiErrorResponse exceptionResponse = ApiErrorResponse.builder().status(400).data(new Date()).mensagem(ex.getMessage()).detalhes(request.getDescription(false)).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParamException.class)
    public final ResponseEntity<ApiErrorResponse> handleInvalidParam(Exception ex, WebRequest request){
        ApiErrorResponse exceptionResponse = ApiErrorResponse.builder().status(400).data(new Date()).mensagem(ex.getMessage()).detalhes(request.getDescription(false)).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
