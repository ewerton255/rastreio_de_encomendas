package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.HistoricoPacote;
import br.com.rastreioencomendas.model.Pacote;
import br.com.rastreioencomendas.model.StatusPacote;

import java.sql.ResultSet;
import java.util.Date;

import static br.com.rastreioencomendas.util.DBUtil.*;

public class HistoricoPacoteBuilder implements Builder{

    private HistoricoPacote historicoPacote;

    public HistoricoPacoteBuilder() {
        this.historicoPacote = new HistoricoPacote();
    }

    public HistoricoPacoteBuilder comId(Integer id) {
        historicoPacote.setId(id);
        return this;
    }

    public HistoricoPacoteBuilder comPacote(Pacote pacote) {
        historicoPacote.setPacote(pacote);
        return this;
    }

    public HistoricoPacoteBuilder comDataHoraAtualizacao(Date dataHoraAtualizacao) {
        historicoPacote.setDataHoraAtualizacao(dataHoraAtualizacao);
        return this;
    }

    public HistoricoPacoteBuilder comDataHoraAtualizacaoFormatados(String dataHoraAtualizacaoFormatados) {
        historicoPacote.setDataHoraAtualizacaoFormatados(dataHoraAtualizacaoFormatados);
        return this;
    }

    public HistoricoPacoteBuilder comLocalizacao(String localizacao) {
        historicoPacote.setLocalizacao(localizacao);
        return this;
    }

    public HistoricoPacoteBuilder comObservacao(String observacao) {
        historicoPacote.setObservacao(observacao);
        return this;
    }

    public HistoricoPacoteBuilder comStatus(StatusPacote status) {
        historicoPacote.setStatus(status);
        return this;
    }

    public HistoricoPacote build() {
        return this.historicoPacote;
    }

    public HistoricoPacote mapear(ResultSet rs){
        return this
                .comId(recuperaInteiro(rs, "id_historico"))
                .comDataHoraAtualizacao(recuperaDate(rs, "datahora_atualizacao"))
                .comLocalizacao(recuperaString(rs, "localizacao"))
                .comObservacao(recuperaString(rs, "observacao"))
                .comStatus(new StatusPacoteBuilder().mapear(rs))
                .build();
    }

}
