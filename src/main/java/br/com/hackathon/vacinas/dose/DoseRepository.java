package br.com.hackathon.vacinas.dose;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoseRepository extends JpaRepository<Dose, Long> {
    
    List<Dose> findByIdVacina(Long idVacina);
}
