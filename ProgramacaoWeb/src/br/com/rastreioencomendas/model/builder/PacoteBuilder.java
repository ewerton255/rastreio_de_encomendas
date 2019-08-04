package br.com.rastreioencomendas.model.builder;

import br.com.rastreioencomendas.model.Empresa;
import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.model.Frete;
import br.com.rastreioencomendas.model.Pacote;

import java.sql.ResultSet;
import java.util.Date;

import static br.com.rastreioencomendas.util.DBUtil.*;

public class PacoteBuilder implements Builder {

    private Pacote pacote;

    public PacoteBuilder() {
        pacote = new Pacote();
    }

    public PacoteBuilder comId(Integer id) {
        pacote.setId(id);
        return this;
    }

    public PacoteBuilder comCodigoRastreio(String codigoRastreio) {
        pacote.setCodigoRastreio(codigoRastreio);
        return this;
    }

    public PacoteBuilder comDescricao(String descricao) {
        pacote.setDescricao(descricao);
        return this;
    }

    public PacoteBuilder comPeso(Double peso) {
        pacote.setPeso(peso);
        return this;
    }

    public PacoteBuilder comCpfCnpjDestinatario(String cpfCnpjDestinatario) {
        pacote.setCpfCnpjDestinatario(cpfCnpjDestinatario);
        return this;
    }

    public PacoteBuilder comDataPostado(Date dataPostado) {
        pacote.setDataPostado(dataPostado);
        return this;
    }

    public PacoteBuilder comDataAtualizacao(Date dataAtualizacao) {
        pacote.setDataAtualizacao(dataAtualizacao);
        return this;
    }

    public PacoteBuilder comPrevisaoEntrega(Date previsaoEntrega) {
        pacote.setPrevisaoEntrega(previsaoEntrega);
        return this;
    }

    public PacoteBuilder comTipoFrete(Frete tipoFrete) {
        pacote.setTipoFrete(tipoFrete);
        return this;
    }

    public PacoteBuilder comEnderecoDestinatario(Endereco enderecoDestinatario) {
        pacote.setEnderecoDestinatario(enderecoDestinatario);
        return this;
    }

    public PacoteBuilder comEmpresaRemetente(Empresa empresaRemetente) {
        pacote.setEmpresaRemetente(empresaRemetente);
        return this;
    }

    public Pacote build() {
        return this.pacote;
    }

    public Pacote mapear(ResultSet rs) {
        return this
                .comId(recuperaInteiro(rs, "id_pacote"))
                .comCodigoRastreio(recuperaString(rs, "codigo_rastreio"))
                .comDescricao(recuperaString(rs, "descricao"))
                .comPeso(recuperaDouble(rs, "peso"))
                .comCpfCnpjDestinatario(recuperaString(rs, "cpf_cnpj_destinatario"))
                .comDataPostado(recuperaDate(rs, "data_postado"))
                .comDataAtualizacao(recuperaDate(rs, "data_atualizacao"))
                .comPrevisaoEntrega(recuperaDate(rs, "previsao_entrega"))
                .comTipoFrete(new FreteBuilder().mapear(rs))
                .comEnderecoDestinatario(new EnderecoBuilder().mapear(rs))
                .comEmpresaRemetente(new EmpresaBuilder().mapear(rs))
                .build();
    }

}
