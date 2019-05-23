package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.StatusPacote;

public class StatusPacoteDAO {

	public List<StatusPacote> retornaListaDeStatus(){
		List<StatusPacote> lista = new ArrayList<>();
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String sql = "SELECT * FROM rastreioencomendas.status ORDER by descricao";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StatusPacote status = new StatusPacote();
				status.setId(rs.getInt("id"));
				status.setDescricao(rs.getString("descricao"));
				
				lista.add(status);
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
	
}
