package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.controller.AbstractUsuarioMB;
import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.model.Usuario;

public class UsuarioDAO extends AbstractUsuarioDAO {

    public Usuario login(Usuario usuario) {
        Usuario usuarioLogado = null;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT id, nome, senha, email, admin FROM rastreioencomendas.usuario WHERE email = ? AND senha = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEmail().toLowerCase());
            ps.setString(2, usuario.getSenha().toLowerCase());
            rs = ps.executeQuery();
            if (rs.next()) {
                usuarioLogado = new Usuario();
                usuarioLogado.setEmail(rs.getString("email"));
                usuarioLogado.setNome(rs.getString("nome"));
                usuarioLogado.setAdmin(rs.getBoolean("admin"));
                usuarioLogado.setId(rs.getInt("id"));
                usuarioLogado.setSenha(rs.getString("senha"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarioLogado;
    }

    public List<Usuario> buscarUsuarios(String tipoBusca, Usuario usuarioParaBuscar) {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String parametroBusca = "";

        if (tipoBusca.equals(AbstractUsuarioMB.BUSCA_POR_NOME)) {
            parametroBusca = "us.nome";
        } else if (tipoBusca.equals(AbstractUsuarioMB.BUSCA_POR_EMAIL)) {
            parametroBusca = "us.email";
        }

        String sql = "SELECT us.id, us.nome, us.email, us.senha, us.admin, " +
                "ed.cep, ed.logradouro, ed.cidade, ed.bairro, ed.id as id_endereco, " +
                "ed.complemento, ed.estado, ed.numero " +
                "FROM rastreioencomendas.usuario us " +
                "JOIN rastreioencomendas.endereco ed ON ed.id = us.id_endereco " +
                "WHERE " + parametroBusca + " ILIKE ?";

        try {
            ps = con.prepareStatement(sql);
            if (tipoBusca.equals(AbstractUsuarioMB.BUSCA_POR_EMAIL)) {
                ps.setString(1, usuarioParaBuscar.getEmail() + AbstractUsuarioMB.SIMBOLO_PORCETAGEM);
            } else if (tipoBusca.equals(AbstractUsuarioMB.BUSCA_POR_NOME)) {
                ps.setString(1, usuarioParaBuscar.getNome() + AbstractUsuarioMB.SIMBOLO_PORCETAGEM);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAdmin(rs.getBoolean("admin"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEndereco(populaObjEndereco(rs));

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return usuarios;
    }

    public List<Usuario> retornaListaDeUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT us.id, us.admin, us.email, us.nome, us.senha, " +
                "ed.cep, ed.logradouro, ed.cidade, ed.bairro, ed.id as id_endereco, " +
                "ed.complemento, ed.estado, ed.numero " +
                "FROM rastreioencomendas.usuario us " +
                "JOIN rastreioencomendas.endereco ed ON ed.id = us.id_endereco " +
                "ORDER BY nome";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setAdmin(rs.getBoolean("admin"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNome(rs.getString("nome"));
                usuario.setId(rs.getInt("id"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEndereco(populaObjEndereco(rs));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }

    public Boolean editarUsuario(Usuario usuario) {
        Boolean editou = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        String sql = "";

        try {
            sql = "UPDATE rastreioencomendas.usuario SET nome = ?, email = ?, senha =?, admin = ? WHERE id = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome().toUpperCase());
            ps.setString(2, usuario.getEmail().toLowerCase());
            ps.setString(3, usuario.getSenha().toLowerCase());
            ps.setBoolean(4, usuario.getAdmin());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();

            sql = "UPDATE rastreioencomendas.endereco SET cep = ?, logradouro = ?, bairro = ?, cidade = ?, estado =?, complemento =?, numero = ?" +
                    " WHERE id = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEndereco().getCep());
            ps.setString(2, usuario.getEndereco().getLogradouro());
            ps.setString(3, usuario.getEndereco().getBairro());
            ps.setString(4, usuario.getEndereco().getCidade());
            ps.setString(5, usuario.getEndereco().getEstado());
            ps.setString(6, usuario.getEndereco().getComplemento());
            ps.setInt(7, usuario.getEndereco().getNumero());
            ps.setInt(8, usuario.getEndereco().getId());
            ps.executeUpdate();
            conn.commit();

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

    public Boolean excluirUsuario(Usuario usuario) {
        Boolean excluiu = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        String sql = "";


        try {
            sql = "DELETE FROM rastreioencomendas.usuario WHERE id = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ps.executeUpdate();

            sql = "DELETE FROM rastreioencomendas.endereco WHERE id = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getEndereco().getId());
            ps.executeUpdate();
            conn.commit();

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

    public Boolean cadastrarUsuario(Usuario usuario) {
        Boolean cadastrou = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        Integer idEndereco = null;

        String sql = "INSERT INTO rastreioencomendas.endereco(cep, logradouro, cidade, bairro, numero, estado, complemento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEndereco().getCep());
            ps.setString(2, usuario.getEndereco().getLogradouro());
            ps.setString(3, usuario.getEndereco().getCidade());
            ps.setString(4, usuario.getEndereco().getBairro());
            ps.setInt(5, usuario.getEndereco().getNumero());
            ps.setString(6, usuario.getEndereco().getEstado());
            ps.setString(7, usuario.getEndereco().getComplemento());

            rs = ps.executeQuery();
            if (rs.next()) {
                idEndereco = rs.getInt("id");

                if (idEndereco != null) {
                    sql = "INSERT INTO rastreioencomendas.usuario(nome, email, senha, admin, id_endereco) VALUES (?, ?, ?, ?, ?)";

                    ps = conn.prepareStatement(sql);
                    ps.setString(1, usuario.getNome().toUpperCase());
                    ps.setString(2, usuario.getEmail().toLowerCase());
                    ps.setString(3, usuario.getSenha().toLowerCase());
                    ps.setBoolean(4, usuario.getAdmin());
                    ps.setInt(5, idEndereco);
                    ps.executeUpdate();
                    conn.commit();

                    cadastrou = true;
                }
            }

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

    public Boolean verificaSeUsuarioJaExiste(Usuario usuario) {
        Boolean existe = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT id FROM rastreioencomendas.usuario WHERE email = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEmail().toLowerCase());
            rs = ps.executeQuery();
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return existe;
    }
}
