package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.controller.AbstractUsuarioController;
import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.Usuario;

public class UsuarioDAO extends AbstractUsuarioController {

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
        String sql = "";

        if (tipoBusca.equals(BUSCA_POR_NOME)) {
            sql = "SELECT id, nome, email, senha, admin FROM rastreioencomendas.usuario WHERE nome ILIKE ?";
        } else if (tipoBusca.equals(BUSCA_POR_EMAIL)) {
            sql = "SELECT id, nome, email, senha, admin FROM rastreioencomendas.usuario WHERE email ILIKE ?";
        }

        try {
            ps = con.prepareStatement(sql);
            if (tipoBusca.equals(BUSCA_POR_EMAIL)) {
                ps.setString(1, usuarioParaBuscar.getEmail() + SIMBOLO_PORCETAGEM);
            } else if (tipoBusca.equals(BUSCA_POR_NOME)) {
                ps.setString(1, usuarioParaBuscar.getNome() + SIMBOLO_PORCETAGEM);
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
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT id, admin, email, nome, senha FROM rastreioencomendas.usuario ORDER BY nome";

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
        String sql = "UPDATE rastreioencomendas.usuario SET nome = ?, email = ?, senha =?, admin = ? WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome().toUpperCase());
            ps.setString(2, usuario.getEmail().toLowerCase());
            ps.setString(3, usuario.getSenha().toLowerCase());
            ps.setBoolean(4, usuario.getAdmin());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();

            editou = true;

            conn.commit();

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
        String sql = "DELETE FROM rastreioencomendas.usuario WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ps.executeUpdate();

            excluiu = true;

            conn.commit();

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
        String sql = "INSERT INTO rastreioencomendas.usuario(nome, email, senha, admin) VALUES (?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome().toUpperCase());
            ps.setString(2, usuario.getEmail().toLowerCase());
            ps.setString(3, usuario.getSenha().toLowerCase());
            ps.setBoolean(4, usuario.getAdmin());
            ps.executeUpdate();

            cadastrou = true;

            conn.commit();

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
