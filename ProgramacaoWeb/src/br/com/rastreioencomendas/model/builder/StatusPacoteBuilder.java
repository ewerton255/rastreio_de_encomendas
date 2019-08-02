package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.StatusPacote;

public class StatusPacoteBuilder {

    private StatusPacote statusPacote;

    public StatusPacoteBuilder() {

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

}
