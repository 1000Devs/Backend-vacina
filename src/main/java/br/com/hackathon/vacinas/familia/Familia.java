package br.com.hackathon.vacinas.familia;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "familia")
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_familia")
    private Long idFamilia;

    @NotBlank(message = "Nome da familia é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name = "nome_familia", length = 100)
    private String nomeFamilia;

    @Size(max = 15, message = "Telefone deve ter no máximo 15 catacteres")
    @Column(name = "telefone_contato")
    private String telefoneContato;

}
