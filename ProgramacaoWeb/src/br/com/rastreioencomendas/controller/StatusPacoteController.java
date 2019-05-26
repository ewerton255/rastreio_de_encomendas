package br.com.rastreioencomendas.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.mobile.component.page.Page;

import br.com.rastreioencomendas.dao.StatusPacoteDAO;
import br.com.rastreioencomendas.model.StatusPacote;
import br.com.rastreioencomendas.util.PageUtil;

@SessionScoped
@ManagedBean
public class StatusPacoteController {

	StatusPacoteDAO statusPacoteDAO = new StatusPacoteDAO();
	StatusPacote statusParaCadastrar = new StatusPacote();
	StatusPacote statusSelecionado = new StatusPacote();
	
	public StatusPacoteController() {
		
	}
	
	public void abrirDlgCadStatus() {
		statusParaCadastrar = new StatusPacote();
		PageUtil.abrirDialog("dlgCadStatus");
		PageUtil.atualizarComponente("formCadStatus");
	}
	
	public void abrirDlgEditarStatus(StatusPacote status) {
		this.statusSelecionado = status;
		PageUtil.abrirDialog("dlgEditarStatus");
		PageUtil.atualizarComponente("formEditarStatus");
	}
	
	public void abrirDlgExcluirStatus(StatusPacote status) {
		this.statusSelecionado = status;
		PageUtil.abrirDialog("dlgExcluirStatus");
		PageUtil.atualizarComponente("formExcluirStatus");
	}
	
	public void cadastrarStatus() {
		if(statusPacoteDAO.cadastrarStatus(statusParaCadastrar)) {
			PageUtil.mensagemDeSucesso("Status cadastrado com sucesso");
			PageUtil.atualizarComponente("formStatusPacote");
			PageUtil.fecharDialog("dlgCadStatus");
		}else {
			PageUtil.mensagemDeErro("Erro ao cadastrar status");
		}
	}
	
	public void editarStatus() {
		if(statusPacoteDAO.editarStatus(statusSelecionado)) {
			PageUtil.mensagemDeSucesso("Status editado com sucesso");
			PageUtil.atualizarComponente("formStatusPacote");
			PageUtil.fecharDialog("dlgEditarStatus");
		}else {
			PageUtil.mensagemDeErro("Erro ao editar status");
		}
	}
	
	public void excluirStatus() {
		if(statusPacoteDAO.excluirStatus(statusSelecionado)) {
			PageUtil.mensagemDeSucesso("Status excluido com sucesso");
			PageUtil.atualizarComponente("formStatusPacote");
			PageUtil.fecharDialog("dlgExcluirStatus");
		}else {
			PageUtil.mensagemDeErro("Erro ao excluir status");
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
