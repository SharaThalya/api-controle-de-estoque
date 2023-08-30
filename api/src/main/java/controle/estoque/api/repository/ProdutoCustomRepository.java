package controle.estoque.api.repository;

import controle.estoque.api.model.dto.ComparativoResponse;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoCustomRepository {
    List<ComparativoResponse> compararMovimentacoesByData(LocalDate dataInicio, LocalDate dataFim);
}
