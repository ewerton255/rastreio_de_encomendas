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
import br.com.rastreioencomendas.model.builder.StatementBuilder;

import static br.com.rastreioencomendas.util.DBUtil.*;
import static br.com.rastreioencomendas.util.DateUtil.*;

public class PacoteDAO {

    private StatementBuilder statementBuilder = new StatementBuilder();
    private static final int ID_STATUS_INICIAL = 1;

    public PacoteDAO() {

    }

    public List<Pacote> retornaListaDePacotes() {
        List<Pacote> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;
        String sql = "SELECT p.id as id_pacote, p.codigo_rastreio, p.descricao, p.peso, p.cpf_cnpj_destinatario, "
                + "p.data_postado, f.tipo as tipo_frete, (p.data_postado::date + f.qtd_dias) as previsao_entrega "
                + "FROM rastreioencomendas.pacote p JOIN rastreioencomendas.frete f ON p.id_frete = f.id";
        try {
            rs = statementBuilder
                    .preparar(conn, sql)
                    .executarQuery();
            while (rs.next()) {
                Pacote pacote = new PacoteBuilder().mapear(rs);
                lista.add(pacote);
            }
            statementBuilder.fecharStatament();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return lista;
    }

    public Boolean verificaSeCodigoJaExiste(String codigo) {
        Boolean existe = false;
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;
        String sql = "SELECT id as id_pacote FROM rastreioencomendas.pacote WHERE codigo_rastreio = ?";
        try {
            rs = statementBuilder
                    .preparar(conn, sql)
                    .comParametro(codigo.toUpperCase())
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

    public Boolean cadastrarAtualizacao(Pacote pacote, HistoricoPacote atualizacao) {
        Boolean cadastrou = false;
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO rastreioencomendas.historico_pacote(id_pacote, datahora_atualizacao, id_status, observacao, localizacao) "
                + "VALUES(?, CURRENT_TIMESTAMP, ?, ?, ?)";
        try {
            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(pacote.getId())
                    .comParametro(atualizacao.getStatus().getId())
                    .comParametro(atualizacao.getObservacao())
                    .comParametro(atualizacao.getLocalizacao())
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

    public Boolean excluirAtualizacao(HistoricoPacote atualizacao) {
        Boolean excluiu = false;
        Connection conn = ConnectionFactory.getConnection();
        String sql = "DELETE FROM rastreioencomendas.historico_pacote WHERE id = ?";
        try {
            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(atualizacao.getId())
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

    public Boolean editarAtualizacao(HistoricoPacote atualizacao) {
        Boolean editou = false;
        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE rastreioencomendas.historico_pacote SET id_status = ?, observacao = ?, localizacao = ? WHERE id = ?";
        try {
            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(atualizacao.getStatus().getId())
                    .comParametro(atualizacao.getObservacao())
                    .comParametro(atualizacao.getLocalizacao())
                    .comParametro(atualizacao.getId())
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

    public List<HistoricoPacote> retornaListaDeHistorico(String codigo) {
        List<HistoricoPacote> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;

        String sql = "SELECT hp.id as id_historico, hp.datahora_atualizacao, hp.observacao, st.id as id_status,"
                + " st.descricao, hp.localizacao FROM rastreioencomendas.historico_pacote hp "
                + "JOIN rastreioencomendas.pacote P ON p.id = hp.id_pacote "
                + "JOIN rastreioencomendas.status st ON st.id = hp.id_status "
                + "WHERE p.codigo_rastreio = ? ORDER BY hp.datahora_atualizacao";
        try {
            rs = statementBuilder
                    .preparar(conn, sql)
                    .comParametro(codigo.toUpperCase())
                    .executarQuery();

            while (rs.next()) {
                HistoricoPacote historico = new HistoricoPacoteBuilder()
                        .comDataHoraAtualizacaoFormatados(formataDataEhHoraParaString(recuperaTimestamp(rs, "datahora_atualizacao")))
                        .mapear(rs);
                lista.add(historico);
            }
            statementBuilder.fecharStatament();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexaoComBD(conn);
        }
        return lista;
    }

    public Boolean cadastrarPacote(Pacote pacote) {
        Boolean cadastrou = false;
        Connection conn = ConnectionFactory.getConnection();
        ResultSet rs;
        Integer idEndereco = null;
        Integer idPacote = null;
        String sql;

        try {
            sql = "INSERT INTO rastreioencomendas.endereco(cep, logradouro, cidade, bairro, numero, estado, complemento) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING id";
            rs = statementBuilder
                    .preparar(conn, sql)
                    .comParametro(pacote.getEnderecoDestinatario().getCep())
                    .comParametro(pacote.getEnderecoDestinatario().getLogradouro())
                    .comParametro(pacote.getEnderecoDestinatario().getCidade())
                    .comParametro(pacote.getEnderecoDestinatario().getBairro())
                    .comParametro(pacote.getEnderecoDestinatario().getNumero())
                    .comParametro(pacote.getEnderecoDestinatario().getEstado())
                    .comParametro(pacote.getEnderecoDestinatario().getComplemento())
                    .executarQuery();
            if (rs.next()) {
                idEndereco = recuperaInteiro(rs, "id");
            }

            sql = "INSERT INTO rastreioencomendas.pacote(codigo_rastreio, descricao, peso, cpf_cnpj_destinatario, " +
                    "id_frete, data_postado, id_endereco_destino) VALUES(?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?) RETURNING id";
            rs = statementBuilder
                    .preparar(conn, sql)
                    .comParametro(pacote.getCodigoRastreio().toUpperCase())
                    .comParametro(pacote.getDescricao())
                    .comParametro(pacote.getPeso())
                    .comParametro(pacote.getCpfCnpjDestinatario())
                    .comParametro(pacote.getTipoFrete().getId())
                    .comParametro(idEndereco)
                    .executarQuery();
            if (rs.next()) {
                idPacote = recuperaInteiro(rs, "id");
            }

            sql = "INSERT INTO rastreioencomendas.historico_pacote(id_pacote, id_status, datahora_atualizacao) VALUES(?, ?, CURRENT_TIMESTAMP)";
            statementBuilder
                    .preparar(conn, sql)
                    .comParametro(idPacote)
                    .comParametro(ID_STATUS_INICIAL)
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
