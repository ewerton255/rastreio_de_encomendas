package br.com.rastreioencomendas.util;

import javax.faces.context.FacesContext;
import br.com.rastreioencomendas.model.Usuario;

public class SessionUtil {

	public static void adicionaObjetoUsuarioNaSessao(Object objeto) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", objeto);
	}
	
	public static Boolean verificaSeUsuarioEstaNaSessao() {
		Boolean sessaoAtiva = false;
		Usuario usuarioLogado = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		if(usuarioLogado != null) {
			sessaoAtiva = true;
		}
		return sessaoAtiva;
	}
	
	public static Object recuperaObjetoDaSessao(String objeto) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(objeto);
	}
	
	public static void encerrarSessao() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
