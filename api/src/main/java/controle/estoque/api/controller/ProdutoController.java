package controle.estoque.api.controller;

import controle.estoque.api.model.dto.ComparativoResponse;
import controle.estoque.api.model.dto.ProdutoRequest;
import controle.estoque.api.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import controle.estoque.api.model.Produto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?>cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) throws Exception {
        Produto produtoSalvo = produtoService.cadastrarProduto(produtoRequest);
        return ResponseEntity.ok(produtoSalvo);

    }

    @GetMapping("/buscar-id/{id}")
    public ResponseEntity<?>buscarProdutoPorId(@PathVariable Integer id) {
        Optional<Produto> produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?>deletarProdutoPorId(@PathVariable Integer id) throws Exception {
        Optional <Produto> produtoDeletado = produtoService.deletarProdutoPorId(id);
        return ResponseEntity.ok(produtoDeletado);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarTodosProdutos(){
        List<Produto> produtoList = produtoService.buscarTodosProdutos();
        return ResponseEntity.ok(produtoList);
    }

    @GetMapping("/buscar-descricao/{descricao}")
    public ResponseEntity<?> buscarProdutoPorDescricao(@PathVariable String descricao){
        List<Produto> produtoEncontrado = produtoService.buscarProdutoPorDescricao(descricao);
        return ResponseEntity.ok(produtoEncontrado);
    }

    @PutMapping("/atualizar-produto/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoRequest produto){
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @GetMapping("/comparacao-entrada-saida")
    public ResponseEntity<?> compararEntradaSaida(@RequestParam(name = "tipoPeriodo")String tipoPeriodo, @RequestParam(name="dia", required = false)LocalDate data, @RequestParam(name="anoMes", required = false)YearMonth anoMes, @RequestParam(name="ano", required = false)Year ano) throws Exception {
        List<ComparativoResponse> comparativoResponse = produtoService.compararEntradaSaida(tipoPeriodo, data, anoMes, ano);

        return ResponseEntity.ok(comparativoResponse);
    }
}

