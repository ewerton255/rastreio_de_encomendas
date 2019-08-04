package br.com.rastreioencomendas.util;

import java.sql.*;
import java.util.Date;

public class DBUtil {

    public static Integer recuperaInteiro(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getInt(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }

    public static String recuperaString(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getString(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }

    public static Date recuperaDate(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getDate(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }

    public static Boolean recuperaBoolean(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getBoolean(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }

    public static Timestamp recuperaTimestamp(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getTimestamp(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }

    public static Double recuperaDouble(ResultSet resultSet, String nomeColuna) {
        try {
            return resultSet.getDouble(nomeColuna);
        } catch (SQLException e) {
            return null;
        }
    }
}
