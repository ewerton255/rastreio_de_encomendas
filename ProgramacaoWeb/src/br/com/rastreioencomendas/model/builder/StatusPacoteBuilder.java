package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.StatusPacote;

public class StatusPacoteBuilder {

    private Integer id;
    private String descricao;

    public StatusPacoteBuilder() {

    }

    public StatusPacoteBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public StatusPacoteBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public StatusPacote build() {
        return new StatusPacote(id, descricao);
    }

}
