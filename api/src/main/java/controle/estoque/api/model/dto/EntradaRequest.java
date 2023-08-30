package controle.estoque.api.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntradaRequest {
    @NotNull
    private Integer idProduto;
    @NotNull
    private Integer quantidade;
}
