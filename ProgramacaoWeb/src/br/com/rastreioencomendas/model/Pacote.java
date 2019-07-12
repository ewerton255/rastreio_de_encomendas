package br.com.rastreioencomendas.model;

import java.util.Date;

public class Pacote {

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

    public Pacote(Integer id, String codigoRastreio, String descricao, Double peso, String cpfCnpjDestinatario,
                  Date dataPostado, Date dataAtualizacao, Date previsaoEntrega, Frete tipoFrete, Endereco enderecoDestinatario,
                  Empresa empresaRemetente) {
        this.id = id;
        this.codigoRastreio = codigoRastreio;
        this.descricao = descricao;
        this.peso = peso;
        this.cpfCnpjDestinatario = cpfCnpjDestinatario;
        this.dataPostado = dataPostado;
        this.dataAtualizacao = dataAtualizacao;
        this.previsaoEntrega = previsaoEntrega;
        this.tipoFrete = tipoFrete;
        this.enderecoDestinatario = enderecoDestinatario;
        this.empresaRemetente = empresaRemetente;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCpfCnpjDestinatario() {
        return cpfCnpjDestinatario;
    }

    public void setCpfCnpjDestinatario(String cpfCnpjDestinatario) {
        this.cpfCnpjDestinatario = cpfCnpjDestinatario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataPostado() {
        return dataPostado;
    }

    public void setDataPostado(Date dataPostado) {
        this.dataPostado = dataPostado;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Frete getTipoFrete() {
        return tipoFrete;
    }

    public Date getPrevisaoEntrega() {
        return previsaoEntrega;
    }

    public void setPrevisaoEntrega(Date previsaoEntrega) {
        this.previsaoEntrega = previsaoEntrega;
    }

    public Endereco getEnderecoDestinatario() {
        return enderecoDestinatario;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Empresa getEmpresaRemetente() {
        return empresaRemetente;
    }
}