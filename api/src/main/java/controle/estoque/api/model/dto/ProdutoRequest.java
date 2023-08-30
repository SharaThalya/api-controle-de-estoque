package controle.estoque.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequest {
    
    private Integer estoqueMinimo;

    private String descricao;

    private Double valor;

}
