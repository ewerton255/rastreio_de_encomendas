package br.com.rastreioencomendas.util;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
@ManagedBean
public class PageUtil {

	public static void redirecionarParaPaginaPrincipal() throws IOException {
		FacesContext context  = FacesContext.getCurrentInstance();
		String url = context.getExternalContext().getRequestContextPath();
		try {
			context.getExternalContext().redirect(url+"/"+"index.faces");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void redirecionarParaPage(String page) throws IOException {
		FacesContext context  = FacesContext.getCurrentInstance();
		String url = context.getExternalContext().getRequestContextPath();
		try {
			context.getExternalContext().redirect(url+"/"+page+".faces");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void abrirDialog(String dialog) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('"+dialog+"').show();");
	}
	
	public static void fecharDialog(String dialog) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('"+dialog+"').hide();");
	}
	
	public static void atualizarComponente(String componente) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(componente);
	}
	
	public static void mensagemDeSucesso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", mensagem));
    }
	
	public static void mensagemDeAlerta(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", mensagem));
	}
	
	public static void mensagemDeErro(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", mensagem));
	}
}