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
import static br.com.rastreioencomendas.util.DBUtil.*;
import static br.com.rastreioencomendas.util.DateUtil.*;

public class FreteDAO {

    public List<Frete> retornaListaDeFretes() {
        List<Frete> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT id as id_frete, qtd_dias, tipo FROM rastreioencomendas.frete";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Frete frete = new FreteBuilder().mapear(rs);
                lista.add(frete);
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

}
