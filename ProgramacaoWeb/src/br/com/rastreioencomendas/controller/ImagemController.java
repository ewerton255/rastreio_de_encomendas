package br.com.rastreioencomendas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
public class ImagemController {

	private List<String> imagens;
	
	@PostConstruct
	public void init() {
		imagens = new ArrayList<>();
		for(int i = 1; i <= 6; i ++) {
			imagens.add("imagem"+i+".jpeg");
		}
	}

	public List<String> getImagens() {
		return imagens;
	}
}
