package br.com.hackathon.vacinas.paciente.repository;

import br.com.hackathon.vacinas.paciente.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}