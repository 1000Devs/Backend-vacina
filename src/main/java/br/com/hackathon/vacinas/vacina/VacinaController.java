package br.com.hackathon.vacinas.vacina;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/vacinas")
public class VacinaController {

    private VacinaService vacinaService;

    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    @GetMapping("/consultar")
    public List<VacinaDTO> consultarVacinas () {
        return vacinaService.consultarVacinas();
    }

    @PostMapping
    public void adicionarVacina (@RequestBody VacinaDTO vacina) {
        vacinaService.adicionar(vacina);
    }

    @PutMapping
    public VacinaDTO alterarVacina (@RequestBody VacinaDTO vacina) {
        return vacinaService.alterar(vacina);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirVacina (@PathVariable Long id) {
        vacinaService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/consultar/faixa_etaria/{faixa}")
    public ResponseEntity<?> consultarVacinaPorFaixaEtaria(@PathVariable PublicoAlvoEnum faixa) {
        List<VacinaDTO> vacinas = vacinaService.consultarVacinaPorFaixaEtaria(faixa);
        return ResponseEntity.ok(vacinas);
    }

    @GetMapping("/consultar/idade_maior/{meses}")
    public List<VacinaDTO> consultarVacinaAcimaLimite (@PathVariable int meses) {
        return vacinaService.consultarVacinaAcimaLimite(meses);
    }
}
