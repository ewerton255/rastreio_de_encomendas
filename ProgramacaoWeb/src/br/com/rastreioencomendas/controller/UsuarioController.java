package br.com.rastreioencomendas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.validator.routines.EmailValidator;

import br.com.rastreioencomendas.dao.UsuarioDAO;
import br.com.rastreioencomendas.model.Usuario;
import br.com.rastreioencomendas.util.PageUtil;
import br.com.rastreioencomendas.util.SessionUtil;

@SessionScoped
@ManagedBean(name = "usuarioController")
public class UsuarioController {

	private Usuario user;
	private Usuario usuarioLogado;
	private Usuario usuarioSelecionado;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public UsuarioController() {
		user = new Usuario();
		usuarioSelecionado = new Usuario();
	}
	
	public void login() throws IOException {
		Usuario usuarioLogado = usuarioDAO.login(user);
		
		if(usuarioLogado != null) {
			SessionUtil.adicionaObjetoUsuarioNaSessao(usuarioLogado);
			this.user = new Usuario();
			this.usuarioLogado = usuarioLogado;
			PageUtil.redirecionarParaPaginaPrincipal();
		}else {
			PageUtil.mensagemDeErro("Usu�rio ou senha inv�lido!");
		}
	}
	
	public List<Usuario> retornaListaDeUsuarios(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = usuarioDAO.retornaListaDeUsuarios();
		return usuarios;
	}
	
	public String retornaNomeUsuarioLogado() {
		String nome = "";
		if(SessionUtil.verificaSeUsuarioEstaNaSessao()) {
			Usuario usuarioLogado = (Usuario) SessionUtil.recuperaObjetoDaSessao("usuarioLogado");
			nome = usuarioLogado.getNome();
		}
		return nome;
	}
	
	public void deslogar() throws IOException {
		SessionUtil.encerrarSessao();
		PageUtil.redirecionarParaPaginaPrincipal();
		this.usuarioLogado = null;
	}
	
	public Boolean verificaSeExisteSessaoAtiva() {
		Boolean existe = false;
		
		if(SessionUtil.verificaSeUsuarioEstaNaSessao()) {
			existe = true;
		}
		
		return existe;
	}
	
	public void selecionaUsuarioParaEditar(Usuario usuario) {
		this.usuarioSelecionado = usuario;
		PageUtil.abrirDialog("dlgEditUsuario");
	}
	
	public void selecionaUsuarioParaExcluir(Usuario usuario) {
		this.usuarioSelecionado = usuario;
		PageUtil.atualizarComponente("formDelUsuario");
		PageUtil.abrirDialog("dlgDelUsuario");
	}
	
	public void editarUsuario() {
		if(usuarioDAO.editarUsuario(this.usuarioSelecionado)) {
			PageUtil.mensagemDeSucesso("Usu�rio editado com sucesso!");
		}else {
			PageUtil.mensagemDeErro("Erro ao editar usu�rio!");
		}
		PageUtil.atualizarComponente("formListUsuarios");
		PageUtil.fecharDialog("dlgEditUsuario");
	}
	
	public void excluirUsuario() {
		if(usuarioDAO.excluirUsuario(this.usuarioSelecionado)) {
			PageUtil.mensagemDeSucesso("Usu�rio excluido com sucesso!");
		}else {
			PageUtil.mensagemDeErro("Erro ao excluir usu�rio!");
		}
		PageUtil.atualizarComponente("formListUsuarios");
		PageUtil.fecharDialog("dlgDelUsuario");
	}
	
	public void cadastrarUsuario() {
		if(usuarioDAO.verificaSeUsuarioJaExiste(user)) {
			PageUtil.mensagemDeErro("Email j� existente");
		}else {
			if(EmailValidator.getInstance().isValid(user.getEmail())) {
				if(usuarioDAO.cadastrarUsuario(user)) {
					PageUtil.mensagemDeSucesso("Usu�rio cadastrado com sucesso!");
					PageUtil.fecharDialog("dlgCadUsuario");
					this.user = new Usuario();
				}else {
					PageUtil.mensagemDeErro("Erro ao cadastrar usu�rio!");
				}
			}else {
				PageUtil.mensagemDeErro("E-mail inv�lido");
			}
		}
		PageUtil.atualizarComponente("formLogin");
		PageUtil.atualizarComponente("formListUsuarios");
		PageUtil.atualizarComponente("formCadUsuario");
	}
	
	public Usuario getUser() {
		return user;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
}
