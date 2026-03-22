package br.com.hackathon.vacinas.dose;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/dose")
@CrossOrigin(origins = "*")
public class DoseController {
    private final DoseService service;
    public  DoseController(DoseService service){
        this.service = service;
    }

    @PostMapping("/inserir")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody Dose dose) {
        Dose salva = service.salvar(dose);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("codigo_dose", salva.getIdDose(), "mensagem", "Dose cadastrada com sucesso."));  
        }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody Dose dose) {
        try {
            service.atualizar(id, dose);
            return ResponseEntity.ok(Map.of("mensagem", "Dose atualizada com sucesso."));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensagem", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao atualizar: " + e.getMessage()));
        }
    }

    @DeleteMapping("/excluir/{id_dose}")
    public ResponseEntity<?> excluir(@PathVariable("id_dose") Long idDose) {
        try {
            service.deletar(idDose);
            return ResponseEntity.ok(Map.of("mensagem", "Dose excluída com sucesso."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao excluir: " + e.getMessage()));
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<?> consultarTodas() {
        try {
            return ResponseEntity.ok(service.listarTodas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensagem", "Erro ao consultar doses: " + e.getMessage()));
        }
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> consultarPorId(@PathVariable Long id) {
        try {
            Optional<Dose> dose = service.buscarPorId(id);
            if (dose.isPresent()) {
                return ResponseEntity.ok(dose.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensagem", "Dose não encontrada."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensagem", "Erro ao consultar a dose: " + e.getMessage()));
        }
    }

    // Rota essencial para o Front-end: saber quais doses pertencem a qual vacina
    @GetMapping("/consultar/vacina/{id_vacina}")
    public ResponseEntity<?> consultarPorIdVacina(@PathVariable("id_vacina") Long idVacina) {
        try {
            return ResponseEntity.ok(service.buscarPorVacina(idVacina));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensagem", "Erro ao consultar doses da vacina: " + e.getMessage()));
        }
    
    }
}
