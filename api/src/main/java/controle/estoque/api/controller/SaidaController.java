package controle.estoque.api.controller;

import controle.estoque.api.model.dto.SaidaRequest;
import controle.estoque.api.model.Saida;
import controle.estoque.api.service.SaidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/saida")
public class SaidaController {
    private final SaidaService saidaService;

    @PostMapping
    public ResponseEntity<?> cadastrarSaida(@RequestBody SaidaRequest saidaRequest) throws Exception {
        Saida saidaCadastrada = saidaService.cadastrarSaida(saidaRequest);
        return ResponseEntity.ok(saidaCadastrada);
    }

    @GetMapping("/saidas-de-produtos")
    public ResponseEntity<?> buscarSaidasPorData(@RequestParam (name = "id",required = false)Integer produtoId, @RequestParam(name = "dataInicio", required = false) LocalDate dataInicio, @RequestParam(name = "dataFim", required = false) LocalDate dataFim){
        List<Saida> saidas = saidaService.buscarSaidasPorData(produtoId, dataInicio, dataFim);
        return ResponseEntity.ok(saidas);
    }

    @GetMapping("/estornar-saida/{id}")
    public ResponseEntity<?> estornarSaidaPorId(@PathVariable Integer id) throws Exception {
        Saida saida = saidaService.estornarSaidaPorId(id);
        return ResponseEntity.ok(saida);
    }
}
