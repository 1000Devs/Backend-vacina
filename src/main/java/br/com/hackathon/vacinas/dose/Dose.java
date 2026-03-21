package br.com.hackathon.vacinas.dose;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name="dose")
public class Dose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dose")
    private Long idDose;

    @NotNull(message = "A chave estrangeira id_vacina é obrigatória")
    @Column(name = "id_vacina")
    private Long idVacina;
    
    @Column(name = "descricao_dose", length = 50)
    private String descricaoDose;
    
    @Column(name = "idade_recomenda_aplicacao")
    private Integer idadeRecomendaAplicacao;
    

    }
