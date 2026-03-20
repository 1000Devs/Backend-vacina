package br.com.hackathon.vacinas.vacina;


public class VacinaModel {

    private int idVacina;
    private String nomeVacina;
    private String descricaoVacina;
    private int limiteAplicacao;
    private PublicoAlvoEnum publicoAlvo;

    
    public void setIdVacina(int idVacina) {
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

    public int getIdVacina() {
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


}