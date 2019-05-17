package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.Usuario;

public class UsuarioDAO {

	public Usuario login(Usuario usuario) {
		Usuario usuarioLogado = null;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sql = "SELECT * FROM rastreioencomendas.usuario WHERE email = ? AND senha = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getEmail().toLowerCase());
			ps.setString(2, usuario.getSenha().toLowerCase());
			rs = ps.executeQuery();
			if(rs.next()) {
				usuarioLogado = new Usuario();
				usuarioLogado.setEmail(rs.getString("email"));
				usuarioLogado.setNome(rs.getString("nome"));
				usuarioLogado.setAdmin(rs.getBoolean("admin"));
				usuarioLogado.setId(rs.getInt("id"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuarioLogado;
	}
	
	public List<Usuario> retornaListaDeUsuarios(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sql = "SELECT * FROM rastreioencomendas.usuario ORDER BY nome";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setEmail(rs.getString("email"));
				usuario.setNome(rs.getString("nome"));
				usuario.setId(rs.getInt("id"));
				usuarios.add(usuario);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (SQLException e) {
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
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail().toLowerCase());
			ps.setString(3, usuario.getSenha().toLowerCase());
			ps.setBoolean(4, usuario.getAdmin());
			ps.setInt(5, usuario.getId());
			ps.executeUpdate();
			editou = true;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (SQLException e) {
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
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (SQLException e) {
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
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail().toLowerCase());
			ps.setString(3, usuario.getSenha().toLowerCase());
			ps.setBoolean(4, usuario.getAdmin());
			ps.executeUpdate();
			cadastrou = true;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cadastrou;
	}
}
