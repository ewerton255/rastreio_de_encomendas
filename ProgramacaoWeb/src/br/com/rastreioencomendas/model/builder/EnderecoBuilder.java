package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.util.DBUtil;

import java.sql.ResultSet;

import static br.com.rastreioencomendas.util.DBUtil.retornaInteiro;
import static br.com.rastreioencomendas.util.DBUtil.retornaString;

public class EnderecoBuilder {

    private Endereco endereco;

    public EnderecoBuilder() {
        this.endereco = new Endereco();
    }

    public EnderecoBuilder comId(Integer id) {
        endereco.setId(id);
        return this;
    }

    public EnderecoBuilder comCep(String cep) {
        endereco.setCep(cep);
        return this;
    }

    public EnderecoBuilder comLogradouro(String logradouro) {
        endereco.setLogradouro(logradouro);
        return this;
    }

    public EnderecoBuilder comCidade(String cidade) {
        endereco.setCidade(cidade);
        return this;
    }

    public EnderecoBuilder comBairro(String bairro) {
        endereco.setBairro(bairro);
        return this;
    }

    public EnderecoBuilder comNumero(Integer numero) {
        endereco.setNumero(numero);
        return this;
    }

    public EnderecoBuilder comEstado(String estado) {
        endereco.setEstado(estado);
        return this;
    }

    public EnderecoBuilder comComplemento(String complemento) {
        endereco.setComplemento(complemento);
        return this;
    }

    public Endereco build() {
        return this.endereco;
    }

    public Endereco mapear(ResultSet rs) {
        return this
                .comId(retornaInteiro(rs, "id_endereco"))
                .comCep(retornaString(rs, "cep"))
                .comLogradouro(retornaString(rs, "logradouro"))
                .comBairro(retornaString(rs, "bairro"))
                .comCidade(retornaString(rs, "cidade"))
                .comComplemento(retornaString(rs, "complemento"))
                .comEstado(retornaString(rs, "estado"))
                .comNumero(retornaInteiro(rs, "numero"))
                .build();
    }

}
