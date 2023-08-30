package controle.estoque.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import controle.estoque.api.model.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProdutoRepository extends JpaRepository<Produto, Integer>, ProdutoCustomRepository {
    @Query("select t from produto t where LOWER(t.descricao) like %:descricao%")
    List<Produto> findByNomeLike(@Param("descricao") String descricao);
    boolean existsByDescricao(String descricao);
}
