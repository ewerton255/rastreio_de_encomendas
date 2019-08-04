package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.Empresa;
import br.com.rastreioencomendas.model.builder.EmpresaBuilder;
import static br.com.rastreioencomendas.util.DBUtil.*;
import static br.com.rastreioencomendas.util.DateUtil.*;

public class EmpresaDAO {

    public List<Empresa> retornaListaEmpresa() {
        List<Empresa> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT em.id as id_empresa, em.cnpj, em.razao_social, en.id as id_endereco, " +
                "em.nome_fantasma, en.cep, en.logradouro, en.bairro, " +
                "en.cidade, en.complemento, en.cidade, en.numero, en.estado " +
                "FROM rastreioencomendas.empresa em " +
                "JOIN rastreioencomendas.endereco en ON en.id = em.id_endereco";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresa empresa = new EmpresaBuilder().mapear(rs);
                lista.add(empresa);
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

    public Boolean cadastrarEmpresa(Empresa empresa) {
        Boolean cadastrou = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "";
        Integer idEndereco = null;

        try {
            sql = "INSERT INTO rastreioencomendas.endereco(cep, logradouro, cidade, bairro, numero, estado, complemento) VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING id";
            ps = conn.prepareStatement(sql);
            ps.setString(1, empresa.getEndereco().getCep());
            ps.setString(2, empresa.getEndereco().getLogradouro());
            ps.setString(3, empresa.getEndereco().getCidade());
            ps.setString(4, empresa.getEndereco().getBairro());
            ps.setInt(5, empresa.getEndereco().getNumero());
            ps.setString(6, empresa.getEndereco().getEstado());
            ps.setString(7, empresa.getEndereco().getComplemento());
            rs = ps.executeQuery();
            if (rs.next()) {
                idEndereco = rs.getInt("id");
            }

            sql = "INSERT INTO rastreioencomendas.empresa(cnpj, id_endereco, nome_fantasma, razao_social) VALUES(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, empresa.getCnpj());
            ps.setInt(2, idEndereco);
            ps.setString(3, empresa.getNomeFantasma());
            ps.setString(4, empresa.getRazaoSocial());
            ps.executeUpdate();
            conn.commit();

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
}
