package br.com.hackathon.vacinas.imunizacao;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/imunizacao")
@CrossOrigin(origins = "*")
public class ImunizacaoController {

    private final ImunizacaoService service;

    public ImunizacaoController(ImunizacaoService service) {
        this.service = service;
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> inserir(@Valid @RequestBody Imunizacao imunizacao) {
        try {
            Imunizacao salva = service.salvar(imunizacao);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("codigo_imunizacao", salva.getIdImunizacao(), "mensagem", "Imunização cadastrada com sucesso."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao cadastrar imunização: " + e.getMessage()));
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody Imunizacao imunizacao) {
        try {
            service.atualizar(id, imunizacao);
            return ResponseEntity.ok(Map.of("mensagem", "Imunização atualizada com sucesso."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensagem", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao atualizar: " + e.getMessage()));
        }
    }

    @DeleteMapping("/excluir/{id_imunizacao}")
    public ResponseEntity<?> excluir(@PathVariable("id_imunizacao") Long idImunizacao) {
        try {
            service.deletar(idImunizacao);
            return ResponseEntity.ok(Map.of("mensagem", "Imunização excluída com sucesso."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensagem", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao excluir: " + e.getMessage()));
        }
    }

    @DeleteMapping("/excluir/paciente/{id_paciente}")
    public ResponseEntity<?> excluirPorPaciente(@PathVariable("id_paciente") Long idPaciente) {
        try {
            service.deletarPorPaciente(idPaciente);
            return ResponseEntity.ok(Map.of("mensagem", "Imunizações do paciente excluídas com sucesso."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao excluir as imunizações do paciente: " + e.getMessage()));
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<?> consultarTodas() {
        try {
            return ResponseEntity.ok(service.listarTodas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensagem", "Erro ao consultar imunizações: " + e.getMessage()));
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> consultarPorId(@PathVariable Long id) {
        try {
            Optional<Imunizacao> imunizacao = service.buscarPorId(id);
            if (imunizacao.isPresent()) {
                return ResponseEntity.ok(imunizacao.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensagem", "Imunização não encontrada."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensagem", "Erro ao consultar a imunização: " + e.getMessage()));
        }
    }

    @GetMapping("/consultar/paciente/{id}")
    public ResponseEntity<?> consultarPorIdPaciente(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorPaciente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensagem", "Erro ao consultar imunizações do paciente: " + e.getMessage()));
        }
    }

    @GetMapping("/consultar/paciente/{id}/aplicacao/{dt_ini}/{dt_fim}")
    public ResponseEntity<?> consultarPorPacienteEPeriodo(
            @PathVariable Long id,
            @PathVariable("dt_ini") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtIni,
            @PathVariable("dt_fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dtFim) {
        try {
            return ResponseEntity.ok(service.buscarPorPacienteEPeriodo(id, dtIni, dtFim));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensagem", "Erro ao consultar intervalo: " + e.getMessage()));
        }
    }
}