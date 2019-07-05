package br.com.rastreioencomendas.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.rastreioencomendas.dao.EmpresaDAO;
import br.com.rastreioencomendas.model.Empresa;
import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.util.PageUtil;
import br.com.rastreioencomendas.util.ViaCEP;
import br.com.rastreioencomendas.util.ViaCEPException;
import br.com.rastreioencomendas.util.ViaCepUtil;

@ViewScoped
@ManagedBean
public class EmpresaMB extends AbstractEmpresaMB{

	EmpresaDAO empresaDAO = new EmpresaDAO();
	Empresa empresaCadastrar = new Empresa();

	public EmpresaMB() {

	}

	public void abrirDialogCadEmpresa() {
		empresaCadastrar = new Empresa();
		PageUtil.abrirDialog(DIALOG_CADASTRO_EMPRESA);
		PageUtil.atualizarComponente(FORM_CADASTRO_EMPRESA);
	}

	public void cadastrarEmpresa() {

		Boolean valido = true;

		if (empresaCadastrar.getEndereco().getNumero() <= 0) {
			PageUtil.mensagemDeErro(MENSAGEM_NUMERO_ENDERECO_INVALIDO);
			valido = false;
		}
		if (empresaCadastrar.getEndereco().getBairro() == null) {
			PageUtil.mensagemDeErro(MENSAGEM_BAIRRO_VAZIO);
			valido = false;
		}
		if (empresaCadastrar.getEndereco().getEstado() == null) {
			PageUtil.mensagemDeErro(MENSAGEM_ESTADO_VAZIO);
			valido = false;
		}
		if (empresaCadastrar.getEndereco().getCidade() == null) {
			PageUtil.mensagemDeErro(MENSAGEM_CIDADE_VAZIA);
			valido = false;
		}
		if (empresaCadastrar.getEndereco().getLogradouro() == null) {
			PageUtil.mensagemDeErro(MENSAGEM_LOGRADOURO_VAZIO);
			valido = false;
		}
		if (valido) {

			if (empresaDAO.cadastrarEmpresa(empresaCadastrar)) {
				PageUtil.mensagemDeSucesso(MENSAGEM_CADASTRO_EMPRESA_SUCESSO);
				PageUtil.fecharDialog(DIALOG_CADASTRO_EMPRESA);
				PageUtil.atualizarComponente(FORM_LISTA_EMPRESAS);
			} else {
				PageUtil.mensagemDeErro(MENSAGEM_CADASTRO_EMPRESA_ERRO);
			}
		}
	}
	
	public void buscarCepDestinatario() throws ViaCEPException {
		if(!empresaCadastrar.getEndereco().getCep().equals(null)){
			Endereco endereco = ViaCepUtil.buscarEndereco(empresaCadastrar.getEndereco());
			this.empresaCadastrar.setEndereco(endereco);
		}
	}

	public List<Empresa> retornaListaEmpresa() {
		return empresaDAO.retornaListaEmpresa();
	}

	public Empresa getEmpresaCadastrar() {
		return empresaCadastrar;
	}
}
