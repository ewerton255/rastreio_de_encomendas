package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.HistoricoModel;
import br.com.rastreioencomendas.model.Pacote;

public class PacoteDAO {

    public List<Pacote> retornaListaDePacotes() {
        List<Pacote> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT p.id, p.codigo_rastreio, p.descricao, p.peso, p.cpf_cnpj_destinatario, "
                + "p.data_postado, f.tipo as tipo_frete, (p.data_postado::date + f.qtd_dias) as previsao_entrega "
                + "FROM rastreioencomendas.pacote p " + "JOIN rastreioencomendas.frete f ON p.id_frete = f.id";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
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

    public Boolean verificaSeCodigoJaExiste(String codigo) {
        Boolean existe = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT id FROM rastreioencomendas.pacote WHERE codigo_rastreio = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, codigo.toUpperCase());
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

    public Boolean cadastrarAtualizacao(Pacote pacote, HistoricoModel atualizacao) {
        Boolean cadastrou = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        String sql = "INSERT INTO rastreioencomendas.historico_pacote(id_pacote, datahora_atualizacao, id_status, observacao, localizacao) "
                + "VALUES(?, CURRENT_TIMESTAMP, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pacote.getId());
            ps.setInt(2, atualizacao.getStatus().getId());
            ps.setString(3, atualizacao.getObservacao());
            ps.setString(4, atualizacao.getLocalizacao());
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

    public Boolean excluirAtualizacao(HistoricoModel atualizacao) {
        Boolean excluiu = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        String sql = "DELETE FROM rastreioencomendas.historico_pacote WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, atualizacao.getId());
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

    public Boolean editarAtualizacao(HistoricoModel atualizacao) {
        Boolean editou = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        String sql = "UPDATE rastreioencomendas.historico_pacote SET id_status = ?, observacao = ?, localizacao = ? WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, atualizacao.getStatus().getId());
            ps.setString(2, atualizacao.getObservacao());
            ps.setString(3, atualizacao.getLocalizacao());
            ps.setInt(4, atualizacao.getId());
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

    public List<HistoricoModel> retornalIstaParaRastreio(String codigo) {
        List<HistoricoModel> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT hp.id_pacote, hp.datahora_atualizacao, hp.observacao, st.id as id_status,"
                + " st.descricao as descricao_status, hp.localizacao " + "FROM rastreioencomendas.historico_pacote hp "
                + "JOIN rastreioencomendas.pacote P ON p.id = hp.id_pacote "
                + "JOIN rastreioencomendas.status st ON st.id = hp.id_status "
                + "WHERE p.codigo_rastreio = ? ORDER BY hp.datahora_atualizacao";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, codigo.toUpperCase());
            rs = ps.executeQuery();
            while (rs.next()) {
                HistoricoModel historico = new HistoricoModel();
                historico.setDataHoraAtualizacao(rs.getDate("datahora_atualizacao"));
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                historico.setDataHoraAtualizacaoFormatados(
                        sdf.format(rs.getTimestamp("datahora_atualizacao").getTime()));
                historico.setObservacao(rs.getString("observacao"));
                historico.setLocalizacao(rs.getString("localizacao"));
                historico.getStatus().setId(rs.getInt("id_status"));
                historico.getStatus().setDescricao(rs.getString("descricao_status"));

                lista.add(historico);
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

        return lista;
    }

    public Boolean cadastrarPacote(Pacote pacote) {
        Boolean cadastrou = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        Integer idEndereco = null;
        Integer idPacote = null;
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
            if (rs.next()) {
                idEndereco = rs.getInt("id");
            }

            sql = "INSERT INTO rastreioencomendas.pacote(codigo_rastreio, descricao, peso, cpf_cnpj_destinatario, id_frete, data_postado, id_endereco_destino)"
                    + " VALUES(?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?) RETURNING id;";

            ps = conn.prepareStatement(sql);
            ps.setString(1, pacote.getCodigoRastreio().toUpperCase());
            ps.setString(2, pacote.getDescricao());
            ps.setDouble(3, pacote.getPeso());
            ps.setString(4, pacote.getCpfCnpjDestinatario());
            ps.setInt(5, pacote.getTipoFrete().getId());
            ps.setInt(6, idEndereco);
            rs = ps.executeQuery();
            if (rs.next()) {
                idPacote = rs.getInt("id");
            }

            sql = "INSERT INTO rastreioencomendas.historico_pacote(id_pacote, id_status, datahora_atualizacao) VALUES(?, ?, CURRENT_TIMESTAMP)";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPacote);
            ps.setInt(2, 1);
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

    public List<HistoricoModel> retornaListaDeHistorico(Integer idPacote) {
        List<HistoricoModel> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT hp.id, hp.datahora_atualizacao, hp.observacao, hp.localizacao,"
                + "s.descricao as status, s.id as id_status " + "FROM rastreioencomendas.historico_pacote hp "
                + "JOIN rastreioencomendas.status s ON s.id = hp.id_status " + "WHERE hp.id_pacote = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPacote);
            rs = ps.executeQuery();
            while (rs.next()) {
                HistoricoModel atualizacao = new HistoricoModel();
                atualizacao.setId(rs.getInt("id"));
                atualizacao.setDataHoraAtualizacao(rs.getDate("datahora_atualizacao"));
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                atualizacao.setDataHoraAtualizacaoFormatados(
                        sdf.format(rs.getTimestamp("datahora_atualizacao").getTime()));
                atualizacao.setObservacao(rs.getString("observacao"));
                atualizacao.setLocalizacao(rs.getString("localizacao"));
                atualizacao.getStatus().setDescricao(rs.getString("status"));
                atualizacao.getStatus().setId(rs.getInt("id_status"));

                lista.add(atualizacao);
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

        return lista;
    }
}
