package br.com.hackathon.vacinas.vacina;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<VacinaModel, Long> {
    List<VacinaModel> findByPublicoAlvo(PublicoAlvoEnum publicoAlvo);

    List<VacinaModel> findByIdadeRecomendada(int idadeRecomendada);
}
