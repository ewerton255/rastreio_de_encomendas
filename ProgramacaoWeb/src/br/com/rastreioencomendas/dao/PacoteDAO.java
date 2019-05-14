package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.Pacote;

public class PacoteDAO {

	public List<Pacote> retornaListaDePacotes(){
		List<Pacote> lista = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sql = "SELECT p.id, p.codigo_rastreio, p.descricao, p.peso, p.cpf_cnpj_destinatario, " + 
				"p.data_postado, f.tipo as tipo_frete, (p.data_postado::date + f.qtd_dias) as previsao_entrega " + 
				"FROM rastreioencomendas.pacote p " + 
				"JOIN rastreioencomendas.frete f ON p.id_frete = f.id";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Pacote pacote = new Pacote();
				pacote.setCodigoRastreio(rs.getString("codigo_rastreio"));
				pacote.setDataPostado(rs.getDate("data_postado"));
				pacote.setCpfCnpjDestinatario(rs.getString("cpf_cnpj_destinatario"));
				pacote.setDescricao(rs.getString("descricao"));
				pacote.setId(rs.getInt("id"));
				pacote.getTipoFrete().setTipo(rs.getString("tipo_frete"));
				pacote.setPrevisaoEntrega(rs.getDate("previsao_entrega"));
				pacote.setPeso(rs.getDouble("peso"));
				
				lista.add(pacote);
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
		
		return lista;
	}
	
	public Boolean verificaSeCodigoJaExiste(String codigo) {
		Boolean existe = false;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sql = "SELECT id FROM rastreioencomendas.pacote WHERE codigo_rastreio = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, codigo);
			rs = ps.executeQuery();
			if(rs.next()) {
				existe = true;
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
		
		return existe;
	}
	
	public Boolean cadastrarPacote(Pacote pacote) {
		Boolean cadastrou = false;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Integer idEndereco = null;
		String sql = "INSERT INTO rastreioencomendas.endereco(cep, logradouro, cidade, bairro, numero, estado, complemento) VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING id";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pacote.getEnderecoDestinatario().getCep());
			ps.setString(2, pacote.getEnderecoDestinatario().getLogradouro());
			ps.setString(3, pacote.getEnderecoDestinatario().getCidade());
			ps.setString(4, pacote.getEnderecoDestinatario().getBairro());
			ps.setInt(5, pacote.getEnderecoDestinatario().getNumero());
			ps.setString(6, pacote.getEnderecoDestinatario().getEstado());
			ps.setString(7, pacote.getEnderecoDestinatario().getComplemento());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				idEndereco = rs.getInt("id");
			}
			
			sql = "INSERT INTO rastreioencomendas.pacote(codigo_rastreio, descricao, peso, cpf_cnpj_destinatario, id_frete, data_postado, id_endereco_destino)"
					+ " VALUES(?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?);";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, pacote.getCodigoRastreio());
			ps.setString(2, pacote.getDescricao());
			ps.setDouble(3, pacote.getPeso());
			ps.setString(4, pacote.getCpfCnpjDestinatario());
			ps.setInt(5, pacote.getTipoFrete().getId());
			ps.setInt(6, idEndereco);
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
