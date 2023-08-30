package controle.estoque.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse implements Serializable {
    private static final long serialVersionUID = -8403004552608817446L;

    private Integer status;
    private Date data;
    private String mensagem;
    private String detalhes;
}
