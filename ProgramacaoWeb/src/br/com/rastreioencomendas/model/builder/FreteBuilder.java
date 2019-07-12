package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Frete;

public class FreteBuilder {

    private Integer id;
    private String tipo;
    private Integer qtdDias;

    public FreteBuilder() {

    }

    public FreteBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public FreteBuilder tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public FreteBuilder qtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
        return this;
    }

    public Frete build() {
        return new Frete(id, tipo, qtdDias);
    }

}
