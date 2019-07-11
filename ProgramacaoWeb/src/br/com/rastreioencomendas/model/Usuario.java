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

    public static class UsuarioBuilder {
        private Integer id;
        private String email;
        private String senha;
        private String nome;
        private Boolean admin;
        private Endereco endereco;

        public UsuarioBuilder() {
            this.endereco = new Endereco.EnderecoBuilder().build();
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
