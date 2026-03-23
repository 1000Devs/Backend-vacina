package br.com.hackathon.vacinas.imunizacao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "imunizacoes")
public class Imunizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imunizacao")
    private Long idImunizacao;

    @NotNull(message = "A chave estrangeira id_paciente é obrigatória")
    @Column(name = "id_paciente")
    private Long idPaciente;

    @NotNull(message = "A chave estrangeira id_dose é obrigatória")
    @Column(name = "id_dose")
    private Long idDose;

    @NotNull(message = "A data de aplicação é obrigatória")
    @Column(name = "data_aplicacao")
    private LocalDate dataAplicacao;

    @Column(name = "fabricante", length = 45)
    private String fabricante;

    @Column(name = "lote", length = 45)
    private String lote;

    @Column(name = "local_aplicacao", length = 45)
    private String localAplicacao;

    @Column(name = "profissional_aplicador", length = 45)
    private String profissionalAplicador;
}