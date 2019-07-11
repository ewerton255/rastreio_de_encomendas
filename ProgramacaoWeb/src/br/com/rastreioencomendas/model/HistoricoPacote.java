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

    public HistoricoPacote(Integer id, Pacote pacote, Date dataHoraAtualizacao,
                            String dataHoraAtualizacaoFormatados, String localizacao, String observacao, StatusPacote status) {
        this.id = id;
        this.pacote = pacote;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.dataHoraAtualizacaoFormatados = dataHoraAtualizacaoFormatados;
        this.localizacao = localizacao;
        this.observacao = observacao;
        this.status = status;
    }

    public static class HistoricoPacoteBuilder {
        private Integer id;
        private Pacote pacote;
        private Date dataHoraAtualizacao;
        private String dataHoraAtualizacaoFormatados;
        private String localizacao;
        private String observacao;
        private StatusPacote status;

        public HistoricoPacoteBuilder() {
            this.status = new StatusPacote.StatusPacoteBuilder().build();
        }

        public HistoricoPacoteBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public HistoricoPacoteBuilder pacote(Pacote pacote) {
            this.pacote = pacote;
            return this;
        }

        public HistoricoPacoteBuilder dataHoraAtualizacao(Date dataHoraAtualizacao) {
            this.dataHoraAtualizacao = dataHoraAtualizacao;
            return this;
        }

        public HistoricoPacoteBuilder dataHoraAtualizacaoFormatados(String dataHoraAtualizacaoFormatados) {
            this.dataHoraAtualizacaoFormatados = dataHoraAtualizacaoFormatados;
            return this;
        }

        public HistoricoPacoteBuilder localizacao(String localizacao) {
            this.localizacao = localizacao;
            return this;
        }

        public HistoricoPacoteBuilder observacao(String observacao) {
            this.observacao = observacao;
            return this;
        }

        public HistoricoPacoteBuilder status(StatusPacote status) {
            this.status = status;
            return this;
        }

        public HistoricoPacote build() {
            return new HistoricoPacote(id, pacote, dataHoraAtualizacao, dataHoraAtualizacaoFormatados, localizacao, observacao, status);
        }
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
