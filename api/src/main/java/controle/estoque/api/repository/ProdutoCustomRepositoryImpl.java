package controle.estoque.api.repository;

import controle.estoque.api.model.dto.ComparativoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class ProdutoCustomRepositoryImpl implements ProdutoCustomRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<ComparativoResponse> compararMovimentacoesByData(LocalDate dataInicio, LocalDate dataFim) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "select\n" +
                "p.id as produtoId,\n" +
                "p.descricao as descricaoProduto,\n" +
                "p.estoque_minimo,\n" +
                "coalesce(SUM(e.quantidade),\n" +
                "0) as total_entrada,\n" +
                "coalesce(SUM(s.quantidade),\n" +
                "0) as total_saida\n" +
                "from\n" +
                "produto p\n" +
                "left join (\n" +
                "select\n" +
                "produto_id,\n" +
                "SUM(quantidade) as quantidade\n" +
                "from\n" +
                "entrada\n" +
                "where\n" +
                "data_entrada between :dataInicio and :dataFim\n" +
                "and is_estornado = false\n" +
                "group by produto_id\n" +
                ") e on\n" +
                "p.id = e.produto_id\n" +
                "left join (\n" +
                "select\n" +
                "produto_id,\n" +
                "SUM(quantidade) as quantidade\n" +
                "from\n" +
                "saida\n" +
                "where " +
                "data_saida between :dataInicio and :dataFim\n" +
                "and is_estornado = false\n" +
                "group by produto_id\n" +
                ") s on\n" +
                "p.id = s.produto_id\n" +
                "group by\n" +
                "p.id,\n" +
                "p.descricao;\n";

        parameters.addValue("dataInicio", dataInicio);
        parameters.addValue("dataFim", dataFim);


        return namedParameterJdbcTemplate.query(query, parameters, new BeanPropertyRowMapper<>(ComparativoResponse.class));
    }
}
