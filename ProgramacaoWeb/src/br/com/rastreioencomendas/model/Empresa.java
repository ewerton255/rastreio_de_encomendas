package br.com.rastreioencomendas.model;

public class Empresa {

    private Integer id;
    private String cnpj;
    private String nomeFantasma;
    private String razaoSocial;
    private Endereco endereco;

    private Empresa(Integer id, String cnpj, String nomeFantasma, String razaoSocial, Endereco endereco) {
        this.id = id;
        this.cnpj = cnpj;
        this.nomeFantasma = nomeFantasma;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
    }

    public static class EmpresaBuilder {
        private Integer id;
        private String cnpj;
        private String nomeFantasma;
        private String razaoSocial;
        private Endereco endereco;

        public EmpresaBuilder() {
            this.endereco = new Endereco.EnderecoBuilder().build();
        }

        public EmpresaBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public EmpresaBuilder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public EmpresaBuilder nomeFantasma(String nomeFantasma) {
            this.nomeFantasma = nomeFantasma;
            return this;
        }

        public EmpresaBuilder razaoSocial(String razaoSocial) {
            this.razaoSocial = razaoSocial;
            return this;
        }

        public EmpresaBuilder endereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public Empresa build() {
            return new Empresa(id, cnpj, nomeFantasma, razaoSocial, endereco);
        }
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
