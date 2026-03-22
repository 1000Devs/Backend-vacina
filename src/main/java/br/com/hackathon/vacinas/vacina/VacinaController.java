package br.com.hackathon.vacinas.vacina;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vacinas")
@CrossOrigin(origins = "*")
public class VacinaController {

    private VacinaService vacinaService;

    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    @GetMapping("/consultar")
    public ResponseEntity<?> consultarVacinas() {
        List<VacinaDTO> vacinas = vacinaService.consultarVacinas();
        return ResponseEntity.ok(vacinas);
    }

    @PostMapping
    public ResponseEntity<?> adicionarVacina(@RequestBody VacinaDTO vacina) {
        vacinaService.adicionar(vacina);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Vacina cadastrada com sucesso."));
    }

    @PutMapping
    public ResponseEntity<?> alterarVacina(@RequestBody VacinaDTO vacina) {
        VacinaDTO vacinaAtualizada = vacinaService.alterar(vacina);
        return ResponseEntity.ok(
                Map.of(
                        "mensagem", "Vacina atualizada com sucesso.",
                        "vacina", vacinaAtualizada
                )
        );
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirVacina(@PathVariable Long id) {
        vacinaService.excluir(id);
        return ResponseEntity.ok(
                Map.of("mensagem", "Vacina excluída com sucesso.")
        );
    }

    @GetMapping("/consultar/faixa_etaria/{faixa}")
    public ResponseEntity<?> consultarVacinaPorFaixaEtaria(@PathVariable PublicoAlvoEnum faixa) {
        List<VacinaDTO> vacinas = vacinaService.consultarVacinaPorFaixaEtaria(faixa);
        return ResponseEntity.ok(vacinas);
    }

    @GetMapping("/consultar/idade_maior/{meses}")
    public ResponseEntity<?> consultarVacinaAcimaLimite(@PathVariable int meses) {
        List<VacinaDTO> vacinas = vacinaService.consultarVacinaAcimaLimite(meses);
        return ResponseEntity.ok(vacinas);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensagem", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensagem", "Erro na requisição: " + e.getMessage()));
    }
}