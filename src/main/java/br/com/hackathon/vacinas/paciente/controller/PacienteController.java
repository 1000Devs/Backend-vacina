package br.com.hackathon.vacinas.paciente.controller;

import br.com.hackathon.vacinas.paciente.dto.PacienteRequest;
import br.com.hackathon.vacinas.paciente.dto.PacienteResponse;
import br.com.hackathon.vacinas.paciente.service.PacienteService;
import br.com.hackathon.vacinas.paciente.shared.dto.ApiResponseMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> adicionar(@NonNull @Valid @RequestBody PacienteRequest request) {
        Long idGerado = pacienteService.adicionar(request);

        Map<String, Object> body = new HashMap<>();
        body.put("idPaciente", idGerado);
        body.put("mensagem", "Paciente cadastrado com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ApiResponseMessage> alterar(@NonNull @PathVariable Long id,
                                                     @NonNull @Valid @RequestBody PacienteRequest request) {
        pacienteService.alterar(id, request);
        return ResponseEntity.ok(new ApiResponseMessage("Paciente alterado com sucesso"));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<ApiResponseMessage> excluir(@NonNull @PathVariable Long id) {
        pacienteService.excluir(id);
        return ResponseEntity.ok(new ApiResponseMessage("Paciente excluido com sucesso"));
    }

    @GetMapping("/consultar")
    public ResponseEntity<List<PacienteResponse>> consultarTodos() {
        return ResponseEntity.ok(pacienteService.consultarTodos());
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<PacienteResponse> consultarPorId(@NonNull @PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.consultarPorId(id));
    }
}