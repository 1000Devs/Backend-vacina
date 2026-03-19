package br.com.hackathon.vacinas.shared.dto;

public class ApiResponseMessage {

    private String mensagem;

    public ApiResponseMessage() {
    }

    public ApiResponseMessage(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}