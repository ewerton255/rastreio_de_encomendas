package br.com.rastreioencomendas.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.rastreioencomendas.dao.StatusPacoteDAO;
import br.com.rastreioencomendas.model.StatusPacote;

@ViewScoped
@ManagedBean
public class StatusPacoteController {

	StatusPacoteDAO statusPacoteDAO = new StatusPacoteDAO();
	
	public StatusPacoteController() {
		
	}
	
	public List<StatusPacote> retornaListaDeStatus(){
		return statusPacoteDAO.retornaListaDeStatus();
	}
	
}
