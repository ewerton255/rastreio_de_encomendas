package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.model.Usuario;

public class UsuarioBuilder {

    private Integer id;
    private String email;
    private String senha;
    private String nome;
    private Boolean admin;
    private Endereco endereco;

    public UsuarioBuilder() {
        this.endereco = new EnderecoBuilder().build();
    }

    public UsuarioBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder senha(String senha) {
        this.senha = senha;
        return this;
    }

    public UsuarioBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder admin(Boolean admin) {
        this.admin = admin;
        return this;
    }

    public UsuarioBuilder endereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public Usuario build() {
        return new Usuario(id, email, senha, nome, admin, endereco);
    }

}
