package br.com.rastreioencomendas.model;

public class Usuario {

    private Integer id;
    private String email;
    private String senha;
    private String nome;
    private Boolean admin;
    private Endereco endereco;

    public Usuario(Integer id, String email, String senha, String nome, Boolean admin, Endereco endereco) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.admin = admin;
        this.endereco = endereco;
    }

    public Boolean isNomeEhEmailNulos(){
        return this.nome.equals(null) && this.email.equals(null);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
