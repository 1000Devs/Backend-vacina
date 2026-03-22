package br.com.hackathon.vacinas.vacina;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

public class VacinaDTO {

    private Long idVacina;

    private String nomeVacina;

    private String descricaoVacina;

    private Integer limiteAplicacao;

    public VacinaDTO(VacinaModel vacinaModel) {
        BeanUtils.copyProperties(vacinaModel, this);
    }

    public VacinaDTO() {

    }

    private PublicoAlvoEnum publicoAlvo;

    
    public void setIdVacina(Long idVacina) {
        this.idVacina = idVacina;
    }

    public void setNomeVacina(String nomeVacina) {
        this.nomeVacina = nomeVacina;
    }

    public void setDescricaoVacina(String descricaoVacina) {
        this.descricaoVacina = descricaoVacina;
    }

    public void setLimiteAplicacao(Integer limiteAplicacao) {
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

    public Integer getLimiteAplicacao() {
        return limiteAplicacao;
    }

    public PublicoAlvoEnum getPublicoAlvo() {
        return publicoAlvo;
    }

}