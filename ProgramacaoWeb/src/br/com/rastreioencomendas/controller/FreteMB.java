package br.com.rastreioencomendas.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.rastreioencomendas.dao.FreteDAO;
import br.com.rastreioencomendas.model.Frete;

@ViewScoped
@ManagedBean
public class FreteMB {

	private List<Frete> listaDeFretes;
	private FreteDAO freteDAO = new FreteDAO();
	
	
	public FreteMB() {
		this.listaDeFretes = freteDAO.retornaListaDeFretes();
	}

	public List<Frete> getListaDeFretes() {
		return listaDeFretes;
	}
}
