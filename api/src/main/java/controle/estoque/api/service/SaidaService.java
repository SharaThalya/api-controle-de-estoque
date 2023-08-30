package controle.estoque.api.service;

import controle.estoque.api.exceptionHandler.exception.InvalidParamException;
import controle.estoque.api.exceptionHandler.exception.ResourceNotFoundException;
import controle.estoque.api.model.dto.SaidaRequest;
import controle.estoque.api.model.Saida;
import controle.estoque.api.repository.SaidaRepository;
import controle.estoque.api.util.CustomGenericMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SaidaService {
    private final SaidaRepository saidaRepository;
    private final CustomGenericMapper mapper;
    private final ProdutoService produtoService;
    @Transactional
    public Saida cadastrarSaida(SaidaRequest saidaRequest){
        Saida saidaEntity = mapper.map(saidaRequest, Saida.class);
        saidaEntity.setDataSaida(LocalDate.now());
        saidaEntity.setEstornado(false);
        if(produtoService.buscarProdutoPorId(saidaRequest.getIdProduto()).isEmpty()){
            throw new ResourceNotFoundException("Id do produto não cadastrado");
        }
        saidaEntity.setProduto(produtoService.buscarProdutoPorId(saidaRequest.getIdProduto()).get());
        produtoService.atualizarSaldoSaidaProduto(saidaEntity.getQuantidade(), saidaEntity.getProduto().getId());
        Saida saidaSalva = saidaRepository.save(saidaEntity);
        return saidaSalva;
    }

    public List<Saida> buscarSaidasPorData(Integer produtoId, LocalDate dataInicio, LocalDate dataFim){
        if(produtoId == null){
            List<Saida> allSaidas = saidaRepository.findAllByDataSaidaBetween(dataInicio, dataFim);
            return allSaidas;
        }
        List<Saida> saidasEspecificas = saidaRepository.findAllByDataSaidaBetweenAndProduto_Id(dataInicio, dataFim, produtoId);
        return saidasEspecificas;
    }
    @Transactional
    public Saida estornarSaidaPorId(Integer id){
        if(!saidaRepository.existsById(id)){
            throw new ResourceNotFoundException("Id de saida não encontrado");
        }
        Saida saidaEntity = saidaRepository.findById(id).get();
        if(saidaEntity.isEstornado()){
            throw new InvalidParamException("Saida já é estornada");
        }
        produtoService.atualizarSaldoEntradaProduto(saidaEntity.getQuantidade() ,saidaEntity.getProduto().getId());
        saidaEntity.setEstornado(true);
        Saida saidaEstornada = saidaRepository.save(saidaEntity);
        return saidaEstornada;
    }
}
