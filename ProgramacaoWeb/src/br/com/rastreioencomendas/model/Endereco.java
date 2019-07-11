package br.com.rastreioencomendas.model;

public class Endereco {

	private Integer id;
	private String cep;
	private String logradouro;
	private String cidade;
	private String bairro;
	private Integer numero;
	private String estado;
	private String complemento;

	public Endereco(Integer id, String cep, String logradouro, String cidade, String bairro, Integer numero, String estado, String complemento) {
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.bairro = bairro;
		this.numero = numero;
		this.estado = estado;
		this.complemento = complemento;
	}

	public static class EnderecoBuilder{
		private Integer id;
		private String cep;
		private String logradouro;
		private String cidade;
		private String bairro;
		private Integer numero;
		private String estado;
		private String complemento;

		public EnderecoBuilder(){

		}

		public EnderecoBuilder id(Integer id){
			this.id = id;
			return this;
		}
		public EnderecoBuilder cep(String cep){
			this.cep = cep;
			return this;
		}
		public EnderecoBuilder logradouro(String logradouro){
			this.logradouro = logradouro;
			return this;
		}
		public EnderecoBuilder cidade(String cidade){
			this.cidade = cidade;
			return this;
		}
		public EnderecoBuilder bairro(String bairro){
			this.bairro = bairro;
			return this;
		}
		public EnderecoBuilder numero(Integer numero){
			this.numero = numero;
			return this;
		}
		public EnderecoBuilder estado(String estado){
			this.estado = estado;
			return this;
		}
		public EnderecoBuilder complemento(String complemento){
			this.complemento = complemento;
			return this;
		}
		public Endereco build(){
			return new Endereco(id, cep, logradouro, cidade, bairro, numero, estado, complemento);
		}
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
