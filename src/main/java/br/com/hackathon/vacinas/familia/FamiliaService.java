package br.com.hackathon.vacinas.familia;

import br.com.hackathon.vacinas.paciente.repository.PacienteRepository;
import br.com.hackathon.vacinas.paciente.service.PacienteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamiliaService {
    private final FamiliaRepository familiaRepository;
    private final PacienteService pacienteService;

    public FamiliaService(FamiliaRepository familiaRepository, PacienteRepository pacienteRepository, PacienteService pacienteService) {
        this.familiaRepository = familiaRepository;
        this.pacienteService = pacienteService;
    }

    public Familia inserir(Familia familia) {
        return familiaRepository.save(familia);
    }

    public Familia atualizar(Long idFamilia, Familia dadosAtulizados) {
        return familiaRepository.findById(idFamilia).map(familia -> {
            familia.setNomeFamilia(dadosAtulizados.getNomeFamilia());
            familia.setTelefoneContato(dadosAtulizados.getTelefoneContato());
            return familiaRepository.save(familia);
        }).orElseThrow(() -> new RuntimeException("Familia Cadastrada"));
    }

    @Transactional
    public void deletar(Long idFamilia) {
        if (!familiaRepository.existsById(idFamilia)) {
            throw new RuntimeException("Familia Não encontrada");
        }
        pacienteService.deleteByIdFamilia(idFamilia);
        familiaRepository.deleteById(idFamilia);
    }

    public List<Familia> listarTodos() {
        return familiaRepository.findAll();
    }

    public Optional<Familia> buscarPorId(Long id) {
        return familiaRepository.findById(id);
    }

    public List<Familia> buscarPorNome(String nome) {
        return familiaRepository.findByNomeFamiliaContainingIgnoreCase(nome);
    }
}
