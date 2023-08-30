package controle.estoque.api.service;

import controle.estoque.api.exceptionHandler.exception.InvalidParamException;
import controle.estoque.api.exceptionHandler.exception.ResourceNotFoundException;
import controle.estoque.api.model.dto.EntradaRequest;
import controle.estoque.api.model.Entrada;
import controle.estoque.api.repository.EntradaRepository;
import controle.estoque.api.util.CustomGenericMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EntradaService {
    private final EntradaRepository entradaRepository;
    private final ProdutoService produtoService;
    private final CustomGenericMapper mapper;
    @Transactional
    public Entrada cadastrarEntrada(EntradaRequest entradaRequest){
        Entrada entradaEntity = mapper.map(entradaRequest, Entrada.class);
        entradaEntity.setDataEntrada(LocalDate.now());
        entradaEntity.setEstornado(false);
        if(produtoService.buscarProdutoPorId(entradaRequest.getIdProduto()).isEmpty()){
            throw new ResourceNotFoundException("Id do produto não cadastrado");
        }
        entradaEntity.setProduto(produtoService.buscarProdutoPorId(entradaRequest.getIdProduto()).get());
        produtoService.atualizarSaldoEntradaProduto(entradaEntity.getQuantidade(), entradaEntity.getProduto().getId());
        Entrada entradaSalva = entradaRepository.save(entradaEntity);
        return entradaSalva;
    }

    public List<Entrada> buscarEntradasPorData(Integer produtoId, LocalDate dataInicio, LocalDate dataFim){
        if(produtoId == null){
            List<Entrada> allEntradas = entradaRepository.findAllByDataEntradaBetween(dataInicio, dataFim);
            return allEntradas;
        }
        List<Entrada> entradasEspecificas = entradaRepository.findAllByDataEntradaBetweenAndProduto_Id(dataInicio, dataFim, produtoId);
        return entradasEspecificas;
    }
    @Transactional
    public Entrada estornarEntradaPorId(Integer id){
        if(!entradaRepository.existsById(id)){
            throw new ResourceNotFoundException("Id de entrada não encontrado");
        }
        Entrada entradaEntity = entradaRepository.findById(id).get();
        if(entradaEntity.isEstornado()){
            throw new InvalidParamException("Saida já é estornada");
        }
        produtoService.atualizarSaldoSaidaProduto(entradaEntity.getQuantidade() ,entradaEntity.getProduto().getId());
        entradaEntity.setEstornado(true);
        Entrada entradaEstornada = entradaRepository.save(entradaEntity);
        return entradaEstornada;
    }
}
