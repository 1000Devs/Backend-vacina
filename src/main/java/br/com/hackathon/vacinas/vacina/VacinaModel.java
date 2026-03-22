package br.com.hackathon.vacinas.vacina;

import org.springframework.beans.BeanUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vacina")
public class VacinaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVacina;

    @Column(name = "nome_vacina", length = 50, nullable = false)
    private String nomeVacina;

    @Column(name = "descricao_vacina", length = 200, nullable = false)
    private String descricaoVacina;

    @Column(name = "limite_aplicacao", nullable = false)
    private int limiteAplicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "publico_alvo", nullable = false)
    private PublicoAlvoEnum publicoAlvo;

    
    
    public VacinaModel(VacinaDTO vacinaDTO) {
        BeanUtils.copyProperties(vacinaDTO, this);
    }

    public VacinaModel() {

    }

    public void setIdVacina(Long idVacina) {
        this.idVacina = idVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public void setDescricaoVacina(String descricaoVacina) {
        this.descricaoVacina = descricaoVacina;
    }

    public void setLimiteAplicacao(int limiteAplicacao) {
        this.limiteAplicacao = limiteAplicacao;
    }

    public void setPublicoAlvo(PublicoAlvoEnum publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public Long getIdVacina() {
        return idVacina;
    }

    public String getNomeVacina() {
        return nomeVacina;
    }

    public String getDescricaoVacina() {
        return descricaoVacina;
    }

    public int getLimiteAplicacao() {
        return limiteAplicacao;
    }

    public PublicoAlvoEnum getPublicoAlvo() {
        return publicoAlvo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idVacina == null) ? 0 : idVacina.hashCode());
        result = prime * result + ((nomeVacina == null) ? 0 : nomeVacina.hashCode());
        result = prime * result + ((descricaoVacina == null) ? 0 : descricaoVacina.hashCode());
        result = prime * result + limiteAplicacao;
        result = prime * result + ((publicoAlvo == null) ? 0 : publicoAlvo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VacinaModel other = (VacinaModel) obj;
        if (idVacina == null) {
            if (other.idVacina != null)
                return false;
        } else if (!idVacina.equals(other.idVacina))
            return false;
        if (nomeVacina == null) {
            if (other.nomeVacina != null)
                return false;
        } else if (!nomeVacina.equals(other.nomeVacina))
            return false;
        if (descricaoVacina == null) {
            if (other.descricaoVacina != null)
                return false;
        } else if (!descricaoVacina.equals(other.descricaoVacina))
            return false;
        if (limiteAplicacao != other.limiteAplicacao)
            return false;
        if (publicoAlvo != other.publicoAlvo)
            return false;
        return true;
    }

    

}