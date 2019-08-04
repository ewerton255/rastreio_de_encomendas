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

    public Pacote(){
        tipoFrete = new Frete();
        enderecoDestinatario = new Endereco();
        empresaRemetente = new Empresa();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getCpfCnpjDestinatario() {
        return cpfCnpjDestinatario;
    }

    public void setCpfCnpjDestinatario(String cpfCnpjDestinatario) {
        this.cpfCnpjDestinatario = cpfCnpjDestinatario;
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

    public Date getPrevisaoEntrega() {
        return previsaoEntrega;
    }

    public void setPrevisaoEntrega(Date previsaoEntrega) {
        this.previsaoEntrega = previsaoEntrega;
    }

    public Frete getTipoFrete() {
        return tipoFrete;
    }

    public void setTipoFrete(Frete tipoFrete) {
        this.tipoFrete = tipoFrete;
    }

    public Endereco getEnderecoDestinatario() {
        return enderecoDestinatario;
    }

    public void setEnderecoDestinatario(Endereco enderecoDestinatario) {
        this.enderecoDestinatario = enderecoDestinatario;
    }

    public Empresa getEmpresaRemetente() {
        return empresaRemetente;
    }

    public void setEmpresaRemetente(Empresa empresaRemetente) {
        this.empresaRemetente = empresaRemetente;
    }
}