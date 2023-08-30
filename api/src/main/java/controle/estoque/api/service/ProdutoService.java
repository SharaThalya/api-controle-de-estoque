package controle.estoque.api.service;

import controle.estoque.api.exceptionHandler.exception.InvalidParamException;
import controle.estoque.api.exceptionHandler.exception.RequiredParameterException;
import controle.estoque.api.exceptionHandler.exception.ResourceNotFoundException;
import controle.estoque.api.model.dto.ComparativoResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import controle.estoque.api.model.dto.ProdutoRequest;
import controle.estoque.api.model.Produto;
import controle.estoque.api.repository.ProdutoRepository;
import controle.estoque.api.util.CustomGenericMapper;

@Service
@AllArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CustomGenericMapper mapper;
    @Transactional
    public Produto cadastrarProduto(ProdutoRequest produto) {
        Produto produtoEntity = mapper.map(produto, Produto.class);
        if(produtoRepository.existsByDescricao(produto.getDescricao())){
            throw new InvalidParamException("Produto com este nome já cadastrado");
        }
        produtoEntity.setSaldo(0);
        produtoEntity.checkStatus();
        Produto produtoSalvo = produtoRepository.save(produtoEntity);
        return produtoSalvo;
    }

    public Optional<Produto> buscarProdutoPorId(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto;
    }

    public List<Produto> buscarTodosProdutos(){
        List<Produto> listaProdutos = produtoRepository.findAll();
        return listaProdutos;
    }
    @Transactional
    public Optional<Produto> deletarProdutoPorId(Integer id){
        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFoundException("Id de produto não cadastrado");
        }
        Optional<Produto> produtoDeletado = buscarProdutoPorId(id);
        produtoRepository.deleteById(id);
        return produtoDeletado;
    }

    public List<Produto> buscarProdutoPorDescricao(String descricao){
        List<Produto> produtoEncontrado = produtoRepository.findByNomeLike(descricao);
        return produtoEncontrado;
    }
    @Transactional
    public Produto atualizarProduto(Integer id ,ProdutoRequest produto){
        Produto produtoEntity = buscarProdutoPorId(id).get();
        produtoEntity.setDescricao(produto.getDescricao());
        produtoEntity.setEstoqueMinimo(produto.getEstoqueMinimo());
        produtoEntity.setValor(produto.getValor());
        produtoEntity.checkStatus();
        return produtoEntity;
    }

    public List<ComparativoResponse> compararEntradaSaida(String tipoPeriodo, LocalDate data, YearMonth mesAno, Year ano) {
        List<ComparativoResponse> listComparativo;
        if ("dia".equals(tipoPeriodo)) {
            if(data==null){
                throw new RequiredParameterException("Parametro dia é obrigatório ao pesquisar na modalidade dia");
            }
            listComparativo = compararEntradaSaidaPorDia(data);
        } else if ("mes".equals(tipoPeriodo)) {
            if(mesAno==null){
                throw new RequiredParameterException("Parametro mesAno é obrigatório ao pesquisar na modalidade mes");
            }
            listComparativo = compararEntradaSaidaPorMes(mesAno);
        } else if ("ano".equals(tipoPeriodo)) {
            if(ano==null){
                throw new RequiredParameterException("Parametro ano é obrigatório ao pesquisar na modalidade ano");
            }
            listComparativo = compararEntradaSaidaPorAno(ano);
        } else {
            throw new InvalidParamException("tipo de periodo inválido( periodos: dia, mes, ano )");
        }
        return listComparativo;
    }

    public List<ComparativoResponse> compararEntradaSaidaPorAno(Year ano){
        LocalDate dataMontadaInicio = LocalDate.of(ano.getValue(), 1, 1);
        LocalDate dataMontadaFinal = LocalDate.of(ano.getValue(), 12, 31);
        List<ComparativoResponse> listaComparativo = produtoRepository.compararMovimentacoesByData(dataMontadaInicio, dataMontadaFinal);
        return listaComparativo;
    }
    public List<ComparativoResponse> compararEntradaSaidaPorMes(YearMonth mes){
        LocalDate dataMontadaInicio = LocalDate.of(mes.getYear(), mes.getMonth(), 01);
        LocalDate dataMontadaFim = LocalDate.of(mes.getYear(), mes.getMonth(), mes.lengthOfMonth());
        List<ComparativoResponse> listaComparativo = produtoRepository.compararMovimentacoesByData(dataMontadaInicio, dataMontadaFim);
        return listaComparativo;
    }
    public List<ComparativoResponse> compararEntradaSaidaPorDia(LocalDate data){
        List<ComparativoResponse> listaComparativo = produtoRepository.compararMovimentacoesByData(data, data);
        return listaComparativo;
    }
    @Transactional
    public void atualizarSaldoEntradaProduto(Integer quantidade, Integer produtoId) {
        Optional<Produto> produtoAtualizado = produtoRepository.findById(produtoId);
        if(produtoAtualizado.isEmpty()){
            throw new ResourceNotFoundException("Id de produto não encontrado");
        }
        produtoAtualizado.get().setSaldo(produtoAtualizado.get().getSaldo() + quantidade);
        produtoAtualizado.get().checkStatus();
    }
    @Transactional
    public void atualizarSaldoSaidaProduto(Integer quantidade, Integer produtoId){
        Optional<Produto> produtoAtualizado = produtoRepository.findById(produtoId);
        if(produtoAtualizado.isEmpty()){
            throw new ResourceNotFoundException("Id de produto não encontrado");
        }
        if(produtoAtualizado.get().getSaldo()<quantidade){
            throw new InvalidParamException("Tentativa de saida maior que o saldo atual do produto");
        }
        produtoAtualizado.get().setSaldo(produtoAtualizado.get().getSaldo()-quantidade);
        produtoAtualizado.get().checkStatus();
    }
}
