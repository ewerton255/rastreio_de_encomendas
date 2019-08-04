package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.model.Usuario;
import java.sql.ResultSet;
import static br.com.rastreioencomendas.util.DBUtil.*;

public class UsuarioBuilder implements Builder{

    private Usuario usuario;

    public UsuarioBuilder() {
        this.usuario = new Usuario();
    }

    public UsuarioBuilder comId(Integer id) {
        usuario.setId(id);
        return this;
    }

    public UsuarioBuilder comEmail(String email) {
        usuario.setEmail(email);
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        usuario.setSenha(senha);
        return this;
    }

    public UsuarioBuilder comNome(String nome) {
        usuario.setNome(nome);
        return this;
    }

    public UsuarioBuilder comAdmin(Boolean admin) {
        usuario.setAdmin(admin);
        return this;
    }

    public UsuarioBuilder comEndereco(Endereco endereco) {
        usuario.setEndereco(endereco);
        return this;
    }

    public Usuario build() {
        return this.usuario;
    }

    public Usuario mapear(ResultSet rs){
        return this
                .comId(recuperaInteiro(rs, "id_usuario"))
                .comAdmin(recuperaBoolean(rs, "admin"))
                .comEmail(recuperaString(rs, "email"))
                .comNome(recuperaString(rs, "nome"))
                .comSenha(recuperaString(rs, "senha"))
                .comEndereco(new EnderecoBuilder().mapear(rs))
                .build();
    }

}
