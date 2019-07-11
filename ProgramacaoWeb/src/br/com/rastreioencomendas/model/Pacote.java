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

    public static class PacoteBuilder {
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
            this.tipoFrete = new Frete.FreteBuilder().build();
            this.enderecoDestinatario = new Endereco.EnderecoBuilder().build();
            this.empresaRemetente = new Empresa.EmpresaBuilder().build();
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