package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Frete;
import br.com.rastreioencomendas.util.DBUtil;

import java.sql.ResultSet;

import static br.com.rastreioencomendas.util.DBUtil.retornaInteiro;
import static br.com.rastreioencomendas.util.DBUtil.retornaString;

public class FreteBuilder {

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
                .comId(retornaInteiro(rs, "id_frete"))
                .comTipo(retornaString(rs, "tipo"))
                .comQtdDias(retornaInteiro(rs, "qtd_dias"))
                .build();
    }

}
