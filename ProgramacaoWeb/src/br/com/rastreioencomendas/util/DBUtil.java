package br.com.rastreioencomendas.util;

import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.model.builder.EnderecoBuilder;

import java.sql.*;

public class DBUtil {

    protected Endereco populaObjEndereco(ResultSet rs) {
        return new EnderecoBuilder()
                .id(retornaInteiro(rs, "id_endereco"))
                .cep(retornaString(rs, "cep"))
                .bairro(retornaString(rs, "bairro"))
                .cidade(retornaString(rs, "cidade"))
                .estado(retornaString(rs, "estado"))
                .complemento(retornaString(rs, "complemento"))
                .numero(retornaInteiro(rs, "numero"))
                .logradouro(retornaString(rs, "logradouro"))
                .build();
    }

    public static Integer retornaInteiro(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getInt(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }

    public static String retornaString(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getString(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }

    public static Date retornaDate(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getDate(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }

    public static Boolean retornaBoolean(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getBoolean(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }

    public static Timestamp retornaTimestamp(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getTimestamp(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }

    public static Double retornaDouble(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getDouble(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }
}
