package br.com.rastreioencomendas.model;

public class Pacote {
	private String codigoRastreio;
	private String descricao;
	private double peso;
	private String cpfCnpjDestinatario;
	
	public String getCodigoRastreio() {
		return codigoRastreio;
	}
	public void setCodigoRastreio(String codigoRastreio) {
		this.codigoRastreio = codigoRastreio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getCpfCnpjDestinatario() {
		return cpfCnpjDestinatario;
	}
	public void setCpfCnpjDestinatario(String cpfCnpjDestinatario) {
		this.cpfCnpjDestinatario = cpfCnpjDestinatario;
	}
	
}