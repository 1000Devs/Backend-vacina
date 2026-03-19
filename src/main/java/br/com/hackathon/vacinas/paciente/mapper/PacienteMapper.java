package br.com.hackathon.vacinas.paciente.mapper;

import br.com.hackathon.vacinas.paciente.dto.PacienteRequest;
import br.com.hackathon.vacinas.paciente.dto.PacienteResponse;
import br.com.hackathon.vacinas.paciente.model.Paciente;
import org.springframework.lang.NonNull;

public final class PacienteMapper {

    private PacienteMapper() {
    }

    @NonNull
    public static Paciente toEntity(@NonNull PacienteRequest request) {
        Paciente paciente = new Paciente();
        paciente.setNomePaciente(request.getNomePaciente());
        paciente.setCpfPaciente(request.getCpfPaciente());
        paciente.setSexo(request.getSexo());
        paciente.setDataNascimento(request.getDataNascimento());
        return paciente;
    }

    public static void updateEntity(@NonNull Paciente paciente, @NonNull PacienteRequest request) {
        paciente.setNomePaciente(request.getNomePaciente());
        paciente.setCpfPaciente(request.getCpfPaciente());
        paciente.setSexo(request.getSexo());
        paciente.setDataNascimento(request.getDataNascimento());
    }

    @NonNull
    public static PacienteResponse toResponse(@NonNull Paciente paciente) {
        PacienteResponse response = new PacienteResponse();
        response.setId(paciente.getId());
        response.setNomePaciente(paciente.getNomePaciente());
        response.setCpfPaciente(paciente.getCpfPaciente());
        response.setSexo(paciente.getSexo());
        response.setDataNascimento(paciente.getDataNascimento());
        return response;
    }
}