package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Endereco;

public class EnderecoBuilder {

    private Integer id;
    private String cep;
    private String logradouro;
    private String cidade;
    private String bairro;
    private Integer numero;
    private String estado;
    private String complemento;

    public EnderecoBuilder() {

    }

    public EnderecoBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public EnderecoBuilder cep(String cep) {
        this.cep = cep;
        return this;
    }

    public EnderecoBuilder logradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public EnderecoBuilder cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public EnderecoBuilder bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public EnderecoBuilder numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public EnderecoBuilder estado(String estado) {
        this.estado = estado;
        return this;
    }

    public EnderecoBuilder complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public Endereco build() {
        return new Endereco(id, cep, logradouro, cidade, bairro, numero, estado, complemento);
    }

}
