package br.com.rastreioencomendas.dao;

import br.com.rastreioencomendas.model.Endereco;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractUsuarioDAO {

    protected Endereco populaObjEndereco(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();

        endereco.setId(rs.getInt("id_endereco"));
        endereco.setCep(rs.getString("cep"));
        endereco.setBairro(rs.getString("bairro"));
        endereco.setCidade(rs.getString("cidade"));
        endereco.setEstado(rs.getString("estado"));
        endereco.setComplemento(rs.getString("complemento"));
        endereco.setNumero(rs.getInt("numero"));
        endereco.setLogradouro(rs.getString("logradouro"));

        return endereco;
    }

}
