package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Empresa;
import br.com.rastreioencomendas.model.Endereco;

import java.sql.ResultSet;

import static br.com.rastreioencomendas.util.DBUtil.recuperaInteiro;
import static br.com.rastreioencomendas.util.DBUtil.recuperaString;

public class EmpresaBuilder implements Builder{

    private Empresa empresa;

    public EmpresaBuilder() {
        this.empresa = new Empresa();
    }

    public EmpresaBuilder comId(Integer id) {
        empresa.setId(id);
        return this;
    }

    public EmpresaBuilder comCnpj(String cnpj) {
        empresa.setCnpj(cnpj);
        return this;
    }

    public EmpresaBuilder comNomeFantasma(String nomeFantasma) {
        empresa.setNomeFantasma(nomeFantasma);
        return this;
    }

    public EmpresaBuilder comRazaoSocial(String razaoSocial) {
        empresa.setRazaoSocial(razaoSocial);
        return this;
    }

    public EmpresaBuilder comEndereco(Endereco endereco) {
        empresa.setEndereco(endereco);
        return this;
    }

    public Empresa build() {
        return this.empresa;
    }

    public Empresa mapear(ResultSet rs){
        return this
                .comId(recuperaInteiro(rs, "id_empresa"))
                .comCnpj(recuperaString(rs, "cnpj"))
                .comNomeFantasma(recuperaString(rs, "nome_fantasma"))
                .comRazaoSocial(recuperaString(rs, "razao_social"))
                .comEndereco(new EnderecoBuilder().mapear(rs))
                .build();
    }

}
