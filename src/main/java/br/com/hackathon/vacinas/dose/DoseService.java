package br.com.hackathon.vacinas.dose;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class DoseService {

    private final DoseRepository repository;

    public DoseService(DoseRepository repository) {
        this.repository = repository;
    }

    public Dose salvar(Dose dose) {
        return repository.save(dose);
    }

    public Dose atualizar(Long id, Dose dadosAtualizados) {
        return repository.findById(id).map(doseExistente -> {
            doseExistente.setIdVacina(dadosAtualizados.getIdVacina());
            doseExistente.setDescricaoDose(dadosAtualizados.getDescricaoDose());
            doseExistente.setIdadeRecomendaAplicacao(dadosAtualizados.getIdadeRecomendaAplicacao());
            return repository.save(doseExistente);
        }).orElseThrow(() -> new RuntimeException("Dose não encontrada."));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Dose> listarTodas() {
        return repository.findAll();
    }

    public Optional<Dose> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Dose> buscarPorVacina(Long idVacina) {
        return repository.findByIdVacina(idVacina);
    }
}