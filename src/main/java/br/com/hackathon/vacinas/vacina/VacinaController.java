package br.com.hackathon.vacinas.vacina;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/vacinas")
public class VacinaController {

    private VacinaService vacinaService;

    @GetMapping("/consultar")
    public List<VacinaDTO> consultarVacinas () {
        return vacinaService.consultarVacinas();
    }

    @PostMapping
    public void adicionarVacina (VacinaDTO vacina) {
        vacinaService.adicionar(vacina);
    }

    @PutMapping
    public VacinaDTO alterarVacina (VacinaDTO vacina) {
        return vacinaService.alterar(vacina);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirVacina (Long id) {
        vacinaService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/consultar/faixa_etaria/{faixa}")
    public List<VacinaDTO> consultarVacinaPorFaixaEtaria (PublicoAlvoEnum faixaEtaria) {
        return vacinaService.consultarVacinaPorFaixaEtaria(faixaEtaria);
    }

    @GetMapping("/consultar/idade_maior/{meses}")
    public List<VacinaDTO> consultarVacinaPorIdadeRecomendada (int limiteAplicacao) {
        return vacinaService.consultarVacinaPorIdadeRecomendada(limiteAplicacao);
    }
}
