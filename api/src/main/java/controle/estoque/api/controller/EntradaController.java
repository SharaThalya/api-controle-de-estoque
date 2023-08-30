package controle.estoque.api.controller;

import controle.estoque.api.model.dto.EntradaRequest;
import controle.estoque.api.model.Entrada;
import controle.estoque.api.service.EntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entrada")
public class EntradaController {
    private final EntradaService entradaService;

    @PostMapping
    public ResponseEntity<?> cadastrarEntrada(@RequestBody EntradaRequest entradaRequest) throws Exception {
        Entrada entradaCadastrada = entradaService.cadastrarEntrada(entradaRequest);
        return ResponseEntity.ok(entradaCadastrada);
    }

    @GetMapping("/entradas-de-produtos")
    public ResponseEntity<?> buscarEntradasPorData(@RequestParam(name = "id",required = false)Integer produtoId, @RequestParam(name = "dataInicio", required = false) LocalDate dataInicio, @RequestParam(name = "dataFim", required = false) LocalDate dataFim){
        List<Entrada> entradas = entradaService.buscarEntradasPorData(produtoId, dataInicio, dataFim);
        return ResponseEntity.ok(entradas);
    }

    @GetMapping("/estornar-entrada/{id}")
    public ResponseEntity<?> estornarEntradaPorId(@PathVariable Integer id) throws Exception {
        Entrada entrada = entradaService.estornarEntradaPorId(id);
        return ResponseEntity.ok(entrada);
    }
}
