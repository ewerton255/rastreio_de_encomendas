package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.HistoricoPacote;
import br.com.rastreioencomendas.model.Pacote;
import br.com.rastreioencomendas.model.StatusPacote;

import java.util.Date;

public class HistoricoPacoteBuilder {

    private Integer id;
    private Pacote pacote;
    private Date dataHoraAtualizacao;
    private String dataHoraAtualizacaoFormatados;
    private String localizacao;
    private String observacao;
    private StatusPacote status;

    public HistoricoPacoteBuilder() {
        this.status = new StatusPacoteBuilder().build();
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
