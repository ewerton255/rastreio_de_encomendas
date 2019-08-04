package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.StatusPacote;

import java.sql.ResultSet;

import static br.com.rastreioencomendas.util.DBUtil.recuperaInteiro;
import static br.com.rastreioencomendas.util.DBUtil.recuperaString;

public class StatusPacoteBuilder implements Builder{

    private StatusPacote statusPacote;

    public StatusPacoteBuilder() {
        this.statusPacote = new StatusPacote();
    }

    public StatusPacoteBuilder comId(Integer id) {
        statusPacote.setId(id);
        return this;
    }

    public StatusPacoteBuilder comDescricao(String descricao) {
        statusPacote.setDescricao(descricao);
        return this;
    }

    public StatusPacote build() {
        return this.statusPacote;
    }

    public StatusPacote mapear(ResultSet rs){
        return this
                .comId(recuperaInteiro(rs, "id_status"))
                .comDescricao(recuperaString(rs, "descricao"))
                .build();
    }

}
