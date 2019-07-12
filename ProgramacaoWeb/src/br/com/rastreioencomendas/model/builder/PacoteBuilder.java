package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Empresa;
import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.model.Frete;
import br.com.rastreioencomendas.model.Pacote;

import java.util.Date;

public class PacoteBuilder {

    private Integer id;
    private String codigoRastreio;
    private String descricao;
    private Double peso;
    private String cpfCnpjDestinatario;
    private Date dataPostado;
    private Date dataAtualizacao;
    private Date previsaoEntrega;
    private Frete tipoFrete;
    private Endereco enderecoDestinatario;
    private Empresa empresaRemetente;

    public PacoteBuilder() {
        this.tipoFrete = new FreteBuilder().build();
        this.enderecoDestinatario = new EnderecoBuilder().build();
        this.empresaRemetente = new EmpresaBuilder().build();
    }

    public PacoteBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public PacoteBuilder codigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
        return this;
    }

    public PacoteBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public PacoteBuilder peso(Double peso) {
        this.peso = peso;
        return this;
    }

    public PacoteBuilder cpfCnpjDestinatario(String cpfCnpjDestinatario) {
        this.cpfCnpjDestinatario = cpfCnpjDestinatario;
        return this;
    }

    public PacoteBuilder dataPostado(Date dataPostado) {
        this.dataPostado = dataPostado;
        return this;
    }

    public PacoteBuilder dataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
        return this;
    }

    public PacoteBuilder previsaoEntrega(Date previsaoEntrega) {
        this.previsaoEntrega = previsaoEntrega;
        return this;
    }

    public PacoteBuilder tipoFrete(Frete tipoFrete) {
        this.tipoFrete = tipoFrete;
        return this;
    }

    public PacoteBuilder enderecoDestinatario(Endereco enderecoDestinatario) {
        this.enderecoDestinatario = enderecoDestinatario;
        return this;
    }

    public PacoteBuilder empresaRemetente(Empresa empresaRemetente) {
        this.empresaRemetente = empresaRemetente;
        return this;
    }

    public Pacote build() {
        return new Pacote(id, codigoRastreio, descricao, peso, cpfCnpjDestinatario, dataPostado, dataAtualizacao,
                previsaoEntrega, tipoFrete, enderecoDestinatario, empresaRemetente);
    }

}
