package br.com.hackathon.vacinas.paciente.dto;

import br.com.hackathon.vacinas.paciente.model.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PacienteRequest {

    @NotBlank(message = "nomePaciente e obrigatorio")
    @Size(max = 150, message = "nomePaciente deve ter no maximo 150 caracteres")
    private String nomePaciente;

    @Size(max = 14, message = "cpfPaciente deve ter no maximo 14 caracteres")
    @Pattern(regexp = "^$|^\\d{11}$|^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
            message = "cpfPaciente deve estar no formato 99999999999 ou 999.999.999-99")
    private String cpfPaciente;

    @NotNull(message = "sexo e obrigatorio")
    private Sexo sexo;

    @NotNull(message = "dataNascimento e obrigatoria")
    @PastOrPresent(message = "dataNascimento nao pode ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}