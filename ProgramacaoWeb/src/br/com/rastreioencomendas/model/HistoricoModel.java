package br.com.rastreioencomendas.model;

import java.util.Date;

public class HistoricoModel {

	private Pacote pacote;
	private String status;
	private Date dataHoraAtualizacao;
	private String dataHoraAtualizacaoFormatados;
	private String observacao;
	
	public Pacote getPacote() {
		return pacote;
	}
	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataHoraAtualizacao() {
		return dataHoraAtualizacao;
	}
	public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
		this.dataHoraAtualizacao = dataHoraAtualizacao;
	}
	public String getDataHoraAtualizacaoFormatados() {
		return dataHoraAtualizacaoFormatados;
	}
	public void setDataHoraAtualizacaoFormatados(String dataHoraAtualizacaoFormatados) {
		this.dataHoraAtualizacaoFormatados = dataHoraAtualizacaoFormatados;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
