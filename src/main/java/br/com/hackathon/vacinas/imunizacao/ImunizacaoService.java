package br.com.hackathon.vacinas.imunizacao;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ImunizacaoService {

    private final ImunizacaoRepository repository;

    public ImunizacaoService(ImunizacaoRepository repository) {
        this.repository = repository;
    }

    public Imunizacao salvar(Imunizacao imunizacao) {
        return repository.save(imunizacao);
    }

    public Imunizacao atualizar(Long id, Imunizacao dadosAtualizados) {
        return repository.findById(id).map(imunizacaoExistente -> {
            imunizacaoExistente.setIdPaciente(dadosAtualizados.getIdPaciente());
            imunizacaoExistente.setIdDose(dadosAtualizados.getIdDose());
            imunizacaoExistente.setDataAplicacao(dadosAtualizados.getDataAplicacao());
            imunizacaoExistente.setFabricante(dadosAtualizados.getFabricante());
            imunizacaoExistente.setLote(dadosAtualizados.getLote());
            imunizacaoExistente.setLocalAplicacao(dadosAtualizados.getLocalAplicacao());
            imunizacaoExistente.setProfissionalAplicador(dadosAtualizados.getProfissionalAplicador());
            return repository.save(imunizacaoExistente);
        }).orElseThrow(() -> new RuntimeException("Imunização não encontrada."));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Imunização não encontrada.");
        }
        repository.deleteById(id);
    }

    public void deletarPorPaciente(Long idPaciente) {
        repository.deleteByIdPaciente(idPaciente);
    }

    public List<Imunizacao> listarTodas() {
        return repository.findAll();
    }

    public Optional<Imunizacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Imunizacao> buscarPorPaciente(Long idPaciente) {
        return repository.findByIdPaciente(idPaciente);
    }

    public List<Imunizacao> buscarPorPacienteEPeriodo(Long idPaciente, LocalDate dtIni, LocalDate dtFim) {
        return repository.findByIdPacienteAndDataAplicacaoBetween(idPaciente, dtIni, dtFim);
    }
}