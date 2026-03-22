package br.com.hackathon.vacinas.familia;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/familia")
@CrossOrigin(origins = "*")
public class FamiliaController {
    private FamiliaService service;

    public FamiliaController(FamiliaService service) {
        this.service = service;
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> inserir(@Valid @RequestBody Familia familia) {
        try {
            Familia salva = service.inserir(familia);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("codigo_familia",
                            salva.getIdFamilia(),
                            "message",
                            "Familia inserida com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", e.getMessage()));
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<?> consultarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/consultar/{id}")
    public ResponseEntity<?> consultarPorId(@PathVariable Long id) {
        Optional<Familia> familia = service.buscarPorId(id);
        if (familia.isPresent()) {
            return ResponseEntity.ok(familia.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Familia não encontrada."));
    }

    @GetMapping("/excluir/{id_familia}")
    public ResponseEntity<?> excluirPorId(@PathVariable Long id_familia) {
        try {
            service.deletar(id_familia);
            return ResponseEntity.ok(Map.of("mensagem", "Familia excluida com sucesso."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", e.getMessage()));
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> consultarPorNome(@PathVariable String nome) {
        try {
            List<Familia> familias = service.buscarPorNome(nome);
            if (familias.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", "Familia não encontrada."));
            }
            return ResponseEntity.ok(familias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("mensagem", "Erro ao buscar famílias por nome."));
        }
    }
}
