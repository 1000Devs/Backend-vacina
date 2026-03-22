package br.com.hackathon.vacinas.vacina;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacinaService {
    
    @Autowired
    private VacinaRepository vacinaRepository;

    public List<VacinaDTO> consultarVacinas () {
        List<VacinaModel> vacinas = vacinaRepository.findAll();
        return vacinas.stream().map(VacinaDTO::new).toList();
    }

    public void adicionar(VacinaDTO vacina) {
        VacinaModel vacinaModel = new VacinaModel(vacina);
        vacinaRepository.save(vacinaModel);
    }

    public VacinaDTO alterar(VacinaDTO vacina) {
        VacinaModel vacinaModel = new VacinaModel(vacina);
        return new VacinaDTO(vacinaRepository.save(vacinaModel));
    }

    public void excluir(Long id) {
        VacinaModel vacinaModel = vacinaRepository.findById(id).get();
        vacinaRepository.delete(vacinaModel);
    }

    public List<VacinaDTO> consultarVacinaPorFaixaEtaria(PublicoAlvoEnum faixaEtaria) {
        List<VacinaModel> vacinas = vacinaRepository.findByPublicoAlvo(faixaEtaria);
        return vacinas.stream().map(VacinaDTO::new).toList();
    }

    public List<VacinaDTO> consultarVacinaAcimaLimite (int limiteAplicacao) {
        List<VacinaModel> vacinas = vacinaRepository.findByLimiteAplicacaoGreaterThan(limiteAplicacao);
        return vacinas.stream().map(VacinaDTO::new).toList();
    }

}