package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Frete;

import java.sql.ResultSet;

import static br.com.rastreioencomendas.util.DBUtil.recuperaInteiro;
import static br.com.rastreioencomendas.util.DBUtil.recuperaString;

public class FreteBuilder implements Builder{

    private Frete frete;

    public FreteBuilder() {
        this.frete = new Frete();
    }

    public FreteBuilder comId(Integer id) {
        frete.setId(id);
        return this;
    }

    public FreteBuilder comTipo(String tipo) {
        frete.setTipo(tipo);
        return this;
    }

    public FreteBuilder comQtdDias(Integer qtdDias) {
        frete.setQtdDias(qtdDias);
        return this;
    }

    public Frete build() {
        return this.frete;
    }

    public Frete mapear(ResultSet rs){
        return this
                .comId(recuperaInteiro(rs, "id_frete"))
                .comTipo(recuperaString(rs, "tipo"))
                .comQtdDias(recuperaInteiro(rs, "qtd_dias"))
                .build();
    }

}
