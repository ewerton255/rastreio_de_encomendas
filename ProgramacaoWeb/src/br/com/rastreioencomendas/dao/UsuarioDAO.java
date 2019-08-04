package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.enums.TipoDeBuscaPorUsuario;
import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.builder.StatementBuilder;
import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.model.Usuario;
import br.com.rastreioencomendas.model.builder.UsuarioBuilder;

import static br.com.rastreioencomendas.util.DBUtil.*;

public class UsuarioDAO {

    private static final String SIMBOLO_PORCETAGEM = "%";
    private StatementBuilder statementBuilder = new StatementBuilder();

    public UsuarioDAO(){

    }

    public Usuario login(Usuario usuario) {
        Usuario usuarioLogado = null;
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;
        String sql = "SELECT id as id_usuario, nome, senha, email, admin FROM rastreioencomendas.usuario WHERE email = ? AND senha = ?";
        try {
            rs = statementBuilder
                    .preparar(conn, sql)
                    .comParametro(usuario.getEmail().toLowerCase())
                    .comParametro(usuario.getSenha().toLowerCase())
                    .executarQuery();
            if (rs.next()) {
                usuarioLogado = new UsuarioBuilder().mapear(rs);
            }
            statementBuilder.fecharStatament();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return usuarioLogado;
    }

    public List<Usuario> buscarUsuarios(TipoDeBuscaPorUsuario tipoBusca, Usuario usuarioParaBuscar) {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String parametroBusca = "";

        if (tipoBusca.equals(TipoDeBuscaPorUsuario.POR_NOME)) {
            parametroBusca = "us.nome";
        } else if (tipoBusca.equals(TipoDeBuscaPorUsuario.POR_EMAIL)) {
            parametroBusca = "us.email";
        }

        String sql = "SELECT us.id as id_usuario, us.nome, us.email, us.senha, us.admin, " +
                "ed.cep, ed.logradouro, ed.cidade, ed.bairro, ed.id as id_endereco, " +
                "ed.complemento, ed.estado, ed.numero " +
                "FROM rastreioencomendas.usuario us " +
                "JOIN rastreioencomendas.endereco ed ON ed.id = us.id_endereco " +
                "WHERE " + parametroBusca + " ILIKE ?";

        try {
            ps = conn.prepareStatement(sql);
            if (tipoBusca.equals(TipoDeBuscaPorUsuario.POR_EMAIL)) {
                ps.setString(1, usuarioParaBuscar.getEmail() + SIMBOLO_PORCETAGEM);
            } else if (tipoBusca.equals(TipoDeBuscaPorUsuario.POR_NOME)) {
                ps.setString(1, usuarioParaBuscar.getNome() + SIMBOLO_PORCETAGEM);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new UsuarioBuilder().mapear(rs);
                usuarios.add(usuario);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return usuarios;
    }

    public List<Usuario> retornaListaDeUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;
        String sql = "SELECT us.id as id_usuario, us.admin, us.email, us.nome, us.senha, " +
                "ed.cep, ed.logradouro, ed.cidade, ed.bairro, ed.id as id_endereco, " +
                "ed.complemento, ed.estado, ed.numero " +
                "FROM rastreioencomendas.usuario us " +
                "JOIN rastreioencomendas.endereco ed ON ed.id = us.id_endereco " +
                "ORDER BY nome";
        try {
            rs = statementBuilder
                    .preparar(conn, sql)
                    .executarQuery();

            while (rs.next()) {
                Usuario usuario = new UsuarioBuilder().mapear(rs);
                usuarios.add(usuario);
            }
            statementBuilder.fecharStatament();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return usuarios;
    }

    public Boolean editarUsuario(Usuario usuario) {
        Boolean editou = false;
        Connection conn = ConnectionFactory.getConnection();
        String sql;

        try {
            sql = "UPDATE rastreioencomendas.usuario SET nome = ?, email = ?, senha =?, admin = ? WHERE id = ?";

            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(usuario.getNome().toUpperCase())
                    .comParametro(usuario.getEmail().toLowerCase())
                    .comParametro(usuario.getSenha().toLowerCase())
                    .comParametro(usuario.getAdmin())
                    .comParametro(usuario.getId())
                    .executarUpdate();

            sql = "UPDATE rastreioencomendas.endereco SET cep = ?, logradouro = ?," +
                    " bairro = ?, cidade = ?, estado =?, complemento =?, numero = ?" +
                    " WHERE id = ?";

            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(usuario.getEndereco().getCep())
                    .comParametro(usuario.getEndereco().getLogradouro())
                    .comParametro(usuario.getEndereco().getBairro())
                    .comParametro(usuario.getEndereco().getCidade())
                    .comParametro(usuario.getEndereco().getEstado())
                    .comParametro(usuario.getEndereco().getComplemento())
                    .comParametro(usuario.getEndereco().getNumero())
                    .comParametro(usuario.getEndereco().getId())
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

    public Boolean excluirUsuario(Usuario usuario) {
        Boolean excluiu = false;
        Connection conn = ConnectionFactory.getConnection();
        String sql;

        try {
            sql = "DELETE FROM rastreioencomendas.usuario WHERE id = ?";

            statementBuilder.preparar(conn, sql)
                    .comParametro(usuario.getId())
                    .executarUpdate();

            sql = "DELETE FROM rastreioencomendas.endereco WHERE id = ?";

            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(usuario.getEndereco().getId())
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

    public Integer cadastraEnderecoEhRetornaId(Connection conn, ResultSet rs, Endereco endereco) throws SQLException {

        String sql = "INSERT INTO rastreioencomendas.endereco(cep, logradouro, cidade, bairro, numero, estado, complemento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        Integer id = null;

        rs = statementBuilder
                .preparar(conn, sql)
                .comParametro(endereco.getCep())
                .comParametro(endereco.getLogradouro())
                .comParametro(endereco.getCidade())
                .comParametro(endereco.getBairro())
                .comParametro(endereco.getNumero())
                .comParametro(endereco.getEstado())
                .comParametro(endereco.getComplemento())
                .executarQuery();
        if (rs.next()) {
            id = recuperaInteiro(rs, "id");
        }
        return id;
    }

    public Boolean cadastrarUsuario(Usuario usuario) {
        Boolean cadastrou = false;
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs = null;
        Integer idEndereco = null;
        String sql;

        try {
            idEndereco = cadastraEnderecoEhRetornaId(conn, rs, usuario.getEndereco());

            sql = "INSERT INTO rastreioencomendas.usuario(nome, email, senha, admin, id_endereco) VALUES (?, ?, ?, ?, ?)";

            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(usuario.getNome().toUpperCase())
                    .comParametro(usuario.getEmail().toLowerCase())
                    .comParametro(usuario.getSenha().toLowerCase())
                    .comParametro(usuario.getAdmin())
                    .comParametro(idEndereco)
                    .executarUpdate();

            conn.commit();
            statementBuilder.fecharStatament();
            cadastrou = true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return cadastrou;
    }

    public Boolean verificaSeUsuarioJaExiste(Usuario usuario) {
        Boolean existe = false;
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;
        String sql = "SELECT id AS id_usuario FROM rastreioencomendas.usuario WHERE email = ?";

        try {
            rs = statementBuilder
                    .preparar(conn, sql)
                    .comParametro(usuario.getEmail().toLowerCase())
                    .executarQuery();

            if (rs.next()) {
                existe = true;
            }
            statementBuilder.fecharStatament();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return existe;
    }
}
