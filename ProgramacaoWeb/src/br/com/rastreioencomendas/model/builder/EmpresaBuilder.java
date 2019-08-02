package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Empresa;
import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.util.DBUtil;

import java.sql.ResultSet;

import static br.com.rastreioencomendas.util.DBUtil.retornaInteiro;
import static br.com.rastreioencomendas.util.DBUtil.retornaString;

public class EmpresaBuilder {

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
                .comId(retornaInteiro(rs, "id"))
                .comCnpj(retornaString(rs, "cnpj"))
                .comNomeFantasma(retornaString(rs, "nome_fantasma"))
                .comRazaoSocial(retornaString(rs, "razao_social"))
                .comEndereco(new EnderecoBuilder()
                        .comId(retornaInteiro(rs, "id_endereco"))
                        .comCep(retornaString(rs, "cep"))
                        .comLogradouro(retornaString(rs, "logradouro"))
                        .comBairro(retornaString(rs, "bairro"))
                        .comCidade(retornaString(rs, "cidade"))
                        .comComplemento(retornaString(rs, "complemento"))
                        .comEstado(retornaString(rs, "estado"))
                        .comNumero(retornaInteiro(rs, "numero"))
                        .build())
                .build();
    }

}
