package controle.estoque.api.repository;

import controle.estoque.api.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface SaidaRepository extends JpaRepository<Saida, Integer> {
    List<Saida> findAllByDataSaidaBetween(LocalDate dataInicio, LocalDate dataFim);
    List<Saida> findAllByDataSaidaBetweenAndProduto_Id(LocalDate dataInicio, LocalDate dataFim, Integer produtoId);
}
