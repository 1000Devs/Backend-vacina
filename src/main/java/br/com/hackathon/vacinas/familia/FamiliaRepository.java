package br.com.hackathon.vacinas.familia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamiliaRepository extends JpaRepository<Familia, Long> {
    List<Familia> findByNomeFamiliaContainingIgnoreCase(String nomeFamilia);
}
