package br.com.rastreioencomendas.util;

import javax.faces.context.FacesContext;
import br.com.rastreioencomendas.model.Usuario;

public class SessionUtil {

	private static final String OBJ_USUARIO = "usuarioLogado";

	public static void adicionaObjetoUsuarioNaSessao(Object objeto) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(OBJ_USUARIO, objeto);
	}
	
	public static Boolean verificaSeUsuarioEstaNaSessao() {
		Boolean sessaoAtiva = false;
		Usuario usuarioLogado = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(OBJ_USUARIO);
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
