package controle.estoque.api.repository;

import controle.estoque.api.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Integer> {
    public List<Entrada> findAllByDataEntradaBetween(LocalDate dataInicio, LocalDate dataFim);
    public List<Entrada> findAllByDataEntradaBetweenAndProduto_Id(LocalDate dataInicio, LocalDate dataFim, Integer id);
}
