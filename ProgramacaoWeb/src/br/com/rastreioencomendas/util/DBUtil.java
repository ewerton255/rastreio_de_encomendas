package br.com.rastreioencomendas.util;

import br.com.rastreioencomendas.model.Endereco;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBUtil {


    protected Endereco populaObjEndereco(ResultSet rs) throws SQLException {

        Endereco endereco = new Endereco.EnderecoBuilder()
                .id(rs.getInt("id_endereco"))
                .cep(rs.getString("cep"))
                .bairro(rs.getString("bairro"))
                .cidade(rs.getString("cidade"))
                .estado(rs.getString("estado"))
                .complemento(rs.getString("complemento"))
                .numero(rs.getInt("numero"))
                .logradouro(rs.getString("logradouro"))
                .build();
        return endereco;
    }

    public static Integer retornaInteiro(ResultSet resultSet, String nomeColuna){
        try{
            return resultSet.getInt(nomeColuna);
        }catch (SQLException e){
            return null;
        }
    }

    public static String retornaString(ResultSet resultSet, String nomeColuna){
        try{
            return resultSet.getString(nomeColuna);
        }catch (SQLException e){
            return null;
        }
    }

    public static  Date retornaDate(ResultSet resultSet, String nomeColuna){
        try{
            return resultSet.getDate(nomeColuna);
        }catch (SQLException e){
            return null;
        }
    }

    public static  Boolean retornaBoolean(ResultSet resultSet, String nomeColuna){
        try{
            return resultSet.getBoolean(nomeColuna);
        }catch (SQLException e){
            return null;
        }
    }

    public static  Timestamp retornaTimestamp(ResultSet resultSet, String nomeColuna){
        try{
            return resultSet.getTimestamp(nomeColuna);
        }catch (SQLException e){
            return null;
        }
    }

    public static  Double retornaDouble(ResultSet resultSet, String nomeColuna){
        try{
            return resultSet.getDouble(nomeColuna);
        }catch (SQLException e){
            return null;
        }
    }
}
