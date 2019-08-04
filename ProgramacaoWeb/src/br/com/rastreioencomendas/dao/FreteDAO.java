package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.Frete;
import br.com.rastreioencomendas.model.builder.FreteBuilder;
import br.com.rastreioencomendas.model.builder.StatementBuilder;

import static br.com.rastreioencomendas.util.DBUtil.*;

public class FreteDAO {

    private StatementBuilder statementBuilder = new StatementBuilder();

    public FreteDAO(){

    }

    public List<Frete> retornaListaDeFretes() {
        List<Frete> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;
        String sql = "SELECT id AS id_frete, qtd_dias, tipo FROM rastreioencomendas.frete";
        try {
            rs = statementBuilder
                    .preparar(conn, sql)
                    .executarQuery();
            while (rs.next()) {
                Frete frete = new FreteBuilder().mapear(rs);
                lista.add(frete);
            }
            statementBuilder.fecharStatament();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return lista;
    }

}
