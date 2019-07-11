package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.StatusPacote;
import br.com.rastreioencomendas.util.DBUtil;

public class StatusPacoteDAO extends DBUtil {

    public List<StatusPacote> retornaListaDeStatus() {
        List<StatusPacote> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT id, descricao FROM rastreioencomendas.status ORDER by id";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                StatusPacote status = new StatusPacote.StatusPacoteBuilder()
                        .id(retornaInteiro(rs, "id"))
                        .descricao(retornaString(rs, "descricao"))
                        .build();
                lista.add(status);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    public Boolean cadastrarStatus(StatusPacote status) {
        Boolean cadastrou = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        String sql = "INSERT INTO rastreioencomendas.status(descricao) VALUES (?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, status.getDescricao().toUpperCase());
            ps.executeUpdate();
            conn.commit();
            ps.close();
            cadastrou = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cadastrou;
    }

    public Boolean editarStatus(StatusPacote status) {
        Boolean editou = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        String sql = "UPDATE rastreioencomendas.status SET descricao = ? WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, status.getDescricao().toUpperCase());
            ps.setInt(2, status.getId());
            ps.executeUpdate();
            conn.commit();
            ps.close();
            editou = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return editou;
    }

    public Boolean excluirStatus(StatusPacote status) {
        Boolean excluiu = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        String sql = "DELETE FROM rastreioencomendas.status WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status.getId());
            ps.executeUpdate();
            conn.commit();
            ps.close();
            excluiu = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return excluiu;
    }
}
