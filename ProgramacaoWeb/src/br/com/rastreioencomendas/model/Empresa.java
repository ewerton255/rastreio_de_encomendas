package br.com.rastreioencomendas.model;

public class Empresa {

    private Integer id;
    private String cnpj;
    private String nomeFantasma;
    private String razaoSocial;
    private Endereco endereco;

    public Empresa(Integer id, String cnpj, String nomeFantasma, String razaoSocial, Endereco endereco) {
        this.id = id;
        this.cnpj = cnpj;
        this.nomeFantasma = nomeFantasma;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasma() {
        return nomeFantasma;
    }

    public void setNomeFantasma(String nomeFantasma) {
        this.nomeFantasma = nomeFantasma;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
