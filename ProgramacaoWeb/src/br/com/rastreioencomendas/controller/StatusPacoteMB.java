package br.com.rastreioencomendas.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.mobile.component.page.Page;

import br.com.rastreioencomendas.dao.StatusPacoteDAO;
import br.com.rastreioencomendas.model.StatusPacote;
import br.com.rastreioencomendas.util.PageUtil;

@ViewScoped
@ManagedBean
public class StatusPacoteMB extends AbstractStatusPacoteMB{

	StatusPacoteDAO statusPacoteDAO = new StatusPacoteDAO();
	StatusPacote statusParaCadastrar = new StatusPacote();
	StatusPacote statusSelecionado = new StatusPacote();
	
	public StatusPacoteMB() {
		
	}
	
	public void abrirDlgCadStatus() {
		statusParaCadastrar = new StatusPacote();
		PageUtil.abrirDialog(DIALOG_CADASTRO_STATUS);
		PageUtil.atualizarComponente(FORM_CADASTRO_STATUS);
	}
	
	public void abrirDlgEditarStatus(StatusPacote status) {
		this.statusSelecionado = status;
		PageUtil.abrirDialog(DIALOG_EDITAR_STATUS);
		PageUtil.atualizarComponente(FORM_EDITAR_STATUS);
	}
	
	public void abrirDlgExcluirStatus(StatusPacote status) {
		this.statusSelecionado = status;
		PageUtil.abrirDialog(DIALOG_EXCLUIR_STATUS);
		PageUtil.atualizarComponente(FORM_EXCLUIR_STATUS);
	}
	
	public void cadastrarStatus() {
		if(statusPacoteDAO.cadastrarStatus(statusParaCadastrar)) {
			PageUtil.mensagemDeSucesso(MENSAGEM_STATUS_CADASTRADO_COM_SUCESSO);
			PageUtil.atualizarComponente(FORM_STATUS);
			PageUtil.fecharDialog(DIALOG_CADASTRO_STATUS);
		}else {
			PageUtil.mensagemDeErro(MENSAGEM_ERRO_CADASTRAR_STATUS);
		}
	}
	
	public void editarStatus() {
		if(statusPacoteDAO.editarStatus(statusSelecionado)) {
			PageUtil.mensagemDeSucesso(MENSAGEM_STATUS_EDITADO_COM_SUCESSO);
			PageUtil.atualizarComponente(FORM_STATUS);
			PageUtil.fecharDialog(DIALOG_EDITAR_STATUS);
		}else {
			PageUtil.mensagemDeErro(MENSAGEM_ERRO_EDITAR_STATUS);
		}
	}
	
	public void excluirStatus() {
		if(statusPacoteDAO.excluirStatus(statusSelecionado)) {
			PageUtil.mensagemDeSucesso(MENSAGEM_STATUS_EXCLUIDO_SUCESSO);
			PageUtil.atualizarComponente(FORM_STATUS);
			PageUtil.fecharDialog(DIALOG_EXCLUIR_STATUS);
		}else {
			PageUtil.mensagemDeErro(MENSAGEM_STATUS_EXCLUIDO_ERRO);
		}
	}
	
	public List<StatusPacote> retornaListaDeStatus(){
		return statusPacoteDAO.retornaListaDeStatus();
	}

	public StatusPacote getStatusParaCadastrar() {
		return statusParaCadastrar;
	}

	public StatusPacote getStatusSelecionado() {
		return statusSelecionado;
	}
}
