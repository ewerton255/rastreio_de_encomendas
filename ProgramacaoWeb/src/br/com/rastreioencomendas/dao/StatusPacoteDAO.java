package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.StatusPacote;
import br.com.rastreioencomendas.model.builder.StatementBuilder;
import br.com.rastreioencomendas.model.builder.StatusPacoteBuilder;

import static br.com.rastreioencomendas.util.DBUtil.*;

public class StatusPacoteDAO {

    private StatementBuilder statementBuilder = new StatementBuilder();

    public StatusPacoteDAO() {

    }

    public List<StatusPacote> retornaListaDeStatus() {
        List<StatusPacote> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;
        String sql = "SELECT id as id_status, descricao FROM rastreioencomendas.status ORDER by id";
        try {
            rs = statementBuilder
                    .preparar(conn, sql)
                    .executarQuery();

            while (rs.next()) {
                StatusPacote status = new StatusPacoteBuilder().mapear(rs);
                lista.add(status);
            }
            statementBuilder.fecharStatament();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return lista;
    }

    public Boolean cadastrarStatus(StatusPacote status) {
        Boolean cadastrou = false;
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO rastreioencomendas.status(descricao) VALUES (?)";
        try {
            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(status.getDescricao().toUpperCase())
                    .executarUpdate();
            conn.commit();
            statementBuilder.fecharStatament();
            cadastrou = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return cadastrou;
    }

    public Boolean editarStatus(StatusPacote status) {
        Boolean editou = false;
        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE rastreioencomendas.status SET descricao = ? WHERE id = ?";
        try {
            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(status.getDescricao().toUpperCase())
                    .comParametro(status.getId())
                    .executarUpdate();
            conn.commit();
            statementBuilder.fecharStatament();
            editou = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return editou;
    }

    public Boolean excluirStatus(StatusPacote status) {
        Boolean excluiu = false;
        Connection conn = ConnectionFactory.getConnection();
        String sql = "DELETE FROM rastreioencomendas.status WHERE id = ?";
        try {
            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(status.getId())
                    .executarUpdate();
            conn.commit();
            statementBuilder.fecharStatament();
            excluiu = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return excluiu;
    }
}
