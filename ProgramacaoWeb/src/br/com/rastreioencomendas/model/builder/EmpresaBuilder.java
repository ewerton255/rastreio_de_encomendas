package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Empresa;
import br.com.rastreioencomendas.model.Endereco;

public class EmpresaBuilder {

    private Integer id;
    private String cnpj;
    private String nomeFantasma;
    private String razaoSocial;
    private Endereco endereco;

    public EmpresaBuilder() {
        this.endereco = new EnderecoBuilder().build();
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
