package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.Empresa;
import br.com.rastreioencomendas.model.builder.EmpresaBuilder;
import br.com.rastreioencomendas.model.builder.StatementBuilder;

import static br.com.rastreioencomendas.util.DBUtil.*;

public class EmpresaDAO {

    private StatementBuilder statementBuilder = new StatementBuilder();

    public EmpresaDAO(){

    }

    public List<Empresa> retornaListaEmpresa() {
        List<Empresa> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;
        String sql = "SELECT em.id as id_empresa, em.cnpj, em.razao_social, en.id as id_endereco, " +
                "em.nome_fantasma, en.cep, en.logradouro, en.bairro, " +
                "en.cidade, en.complemento, en.cidade, en.numero, en.estado " +
                "FROM rastreioencomendas.empresa em " +
                "JOIN rastreioencomendas.endereco en ON en.id = em.id_endereco";
        try {
            rs = statementBuilder
                    .preparar(conn, sql)
                    .executarQuery();
            while (rs.next()) {
                Empresa empresa = new EmpresaBuilder().mapear(rs);
                lista.add(empresa);
            }
            statementBuilder.fecharStatament();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return lista;
    }

    public Boolean cadastrarEmpresa(Empresa empresa) {
        Boolean cadastrou = false;
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;
        String sql;
        Integer idEndereco = null;
        try {
            sql = "INSERT INTO rastreioencomendas.endereco(cep, logradouro, cidade, bairro, numero, estado, complemento) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING id";
            rs = statementBuilder
                    .preparar(conn, sql)
                    .comParametro(empresa.getEndereco().getCep())
                    .comParametro(empresa.getEndereco().getLogradouro())
                    .comParametro(empresa.getEndereco().getCidade())
                    .comParametro(empresa.getEndereco().getBairro())
                    .comParametro(empresa.getEndereco().getNumero())
                    .comParametro(empresa.getEndereco().getEstado())
                    .comParametro(empresa.getEndereco().getComplemento())
                    .executarQuery();
            if (rs.next()) {
                idEndereco = rs.getInt("id");
            }

            sql = "INSERT INTO rastreioencomendas.empresa(cnpj, id_endereco, nome_fantasma, razao_social) VALUES(?, ?, ?, ?)";
            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(empresa.getCnpj())
                    .comParametro(idEndereco)
                    .comParametro(empresa.getNomeFantasma())
                    .comParametro(empresa.getRazaoSocial())
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
}
