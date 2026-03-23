package br.com.hackathon.vacinas.paciente.service;

import br.com.hackathon.vacinas.paciente.dto.PacienteRequest;
import br.com.hackathon.vacinas.paciente.dto.PacienteResponse;
import br.com.hackathon.vacinas.paciente.mapper.PacienteMapper;
import br.com.hackathon.vacinas.paciente.model.Paciente;
import br.com.hackathon.vacinas.paciente.repository.PacienteRepository;
import br.com.hackathon.vacinas.paciente.shared.exception.RecursoNaoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("null")
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Long adicionar(@NonNull PacienteRequest request) {
        Paciente paciente = PacienteMapper.toEntity(request);
        Paciente salvo = pacienteRepository.save(paciente);
        return salvo.getId();
    }

    public void alterar(@NonNull Long id, @NonNull PacienteRequest request) {
        Paciente paciente = buscarEntidadePorId(id);
        PacienteMapper.updateEntity(paciente, request);
        pacienteRepository.save(paciente);
    }

    public void excluir(@NonNull Long id) {
        Paciente paciente = buscarEntidadePorId(id);
        pacienteRepository.delete(paciente);
    }

    public void deleteByIdFamilia(Long idFamilia) {
        pacienteRepository.findAll().stream()
                .filter(paciente -> paciente.getIdFamilia().equals(idFamilia))
                .forEach(paciente -> excluir(paciente.getId()));
    }

    public List<PacienteResponse> consultarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteMapper::toResponse)
                .toList();
    }

    public PacienteResponse consultarPorId(@NonNull Long id) {
        Paciente paciente = buscarEntidadePorId(id);
        return PacienteMapper.toResponse(paciente);
    }

    @NonNull
    private Paciente buscarEntidadePorId(@NonNull Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Paciente nao encontrado para o id: " + id));
    }

    public List<PacienteResponse> buscarPorFamilia(@NonNull Long idFamilia) {
        return pacienteRepository.findAll().stream()
                .filter(paciente -> idFamilia.equals(paciente.getIdFamilia())) // Ajuste o getter caso o seu seja diferente
                .map(PacienteMapper::toResponse)
                .toList();
    }
}