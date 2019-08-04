package br.com.rastreioencomendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rastreioencomendas.factory.ConnectionFactory;
import br.com.rastreioencomendas.model.HistoricoPacote;
import br.com.rastreioencomendas.model.Pacote;
import br.com.rastreioencomendas.model.builder.HistoricoPacoteBuilder;
import br.com.rastreioencomendas.model.builder.PacoteBuilder;
import static br.com.rastreioencomendas.util.DBUtil.*;
import static br.com.rastreioencomendas.util.DateUtil.*;

public class PacoteDAO{

    public List<Pacote> retornaListaDePacotes() {
        List<Pacote> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT p.id as id_pacote, p.codigo_rastreio, p.descricao, p.peso, p.cpf_cnpj_destinatario, "
                + "p.data_postado, f.tipo as tipo_frete, (p.data_postado::date + f.qtd_dias) as previsao_entrega "
                + "FROM rastreioencomendas.pacote p " + "JOIN rastreioencomendas.frete f ON p.id_frete = f.id";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pacote pacote = new PacoteBuilder().mapear(rs);
                lista.add(pacote);
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

    public Boolean verificaSeCodigoJaExiste(String codigo) {
        Boolean existe = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT id as id_pacote FROM rastreioencomendas.pacote WHERE codigo_rastreio = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, codigo.toUpperCase());
            rs = ps.executeQuery();
            if (rs.next()) {
                existe = true;
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

        return existe;
    }

    public Boolean cadastrarAtualizacao(Pacote pacote, HistoricoPacote atualizacao) {
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
            ps.close();
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

    public Boolean excluirAtualizacao(HistoricoPacote atualizacao) {
        Boolean excluiu = false;
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        String sql = "DELETE FROM rastreioencomendas.historico_pacote WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, atualizacao.getId());
            ps.executeUpdate();
            conn.commit();
            ps.close();
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

    public Boolean editarAtualizacao(HistoricoPacote atualizacao) {
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
            ps.close();
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

    public List<HistoricoPacote> retornaListaDeHistorico(String codigo) {
        List<HistoricoPacote> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "SELECT hp.id as id_historico, hp.datahora_atualizacao, hp.observacao, st.id as id_status,"
                + " st.descricao, hp.localizacao FROM rastreioencomendas.historico_pacote hp "
                + "JOIN rastreioencomendas.pacote P ON p.id = hp.id_pacote "
                + "JOIN rastreioencomendas.status st ON st.id = hp.id_status "
                + "WHERE p.codigo_rastreio = ? ORDER BY hp.datahora_atualizacao";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, codigo.toUpperCase());
            rs = ps.executeQuery();

            while (rs.next()) {
                HistoricoPacote historico = new HistoricoPacoteBuilder()
                        .comDataHoraAtualizacaoFormatados(formataDataEhHoraParaString(recuperaTimestamp(rs, "datahora_atualizacao")))
                        .mapear(rs);
                lista.add(historico);
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
                idEndereco = recuperaInteiro(rs, "id");
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
                idPacote = recuperaInteiro(rs, "id");
            }

            sql = "INSERT INTO rastreioencomendas.historico_pacote(id_pacote, id_status, datahora_atualizacao) VALUES(?, ?, CURRENT_TIMESTAMP)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPacote);
            ps.setInt(2, 1);
            ps.executeUpdate();
            conn.commit();
            ps.close();
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
