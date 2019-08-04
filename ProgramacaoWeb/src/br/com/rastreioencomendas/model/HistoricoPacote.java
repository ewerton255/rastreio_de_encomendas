package br.com.rastreioencomendas.model;

import java.util.Date;

public class HistoricoPacote {

    private Integer id;
    private Pacote pacote;
    private Date dataHoraAtualizacao;
    private String dataHoraAtualizacaoFormatados;
    private String localizacao;
    private String observacao;
    private StatusPacote status;

    public HistoricoPacote(){
        status = new StatusPacote();
        pacote = new Pacote();
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public String getDataHoraAtualizacaoFormatados() {
        return dataHoraAtualizacaoFormatados;
    }

    public void setDataHoraAtualizacaoFormatados(String dataHoraAtualizacaoFormatados) {
        this.dataHoraAtualizacaoFormatados = dataHoraAtualizacaoFormatados;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusPacote getStatus() {
        return status;
    }

    public void setStatus(StatusPacote status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
