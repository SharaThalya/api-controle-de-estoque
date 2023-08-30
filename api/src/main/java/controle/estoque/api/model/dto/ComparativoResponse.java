package controle.estoque.api.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ComparativoResponse {
    private Integer produtoId;
    private String descricaoProduto;
    private Integer estoqueMinimo;
    private Integer totalEntrada;
    private Integer totalSaida;

    public Integer getSaldoData(){
        return this.totalEntrada-this.totalSaida;
    }
}
