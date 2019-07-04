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
@ManagedBean
public class UsuarioController extends AbstractUsuarioController{

	private Usuario user;
	private Usuario usuarioLogado;
	private Usuario usuarioSelecionado;
	private Usuario usuarioBuscar = new Usuario();;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<Usuario> listaDeUsuarios = new ArrayList<>();
	private String tipoDeBusca;
	
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
			PageUtil.mensagemDeErro("Usuário ou senha inválido!");
		}
	}

	public void buscarUsuarios(){
		if(tipoDeBusca.equals(null)){
			PageUtil.mensagemDeErro("Selecione o tipo de busca");
		}else if(usuarioBuscar.getEmail() == null && usuarioBuscar.getNome() == null){
			PageUtil.mensagemDeErro("Insira a busca");
		}else{
			listaDeUsuarios = usuarioDAO.buscarUsuarios(tipoDeBusca, usuarioBuscar);
		}
		PageUtil.atualizarComponente("formListUsuarios");
	}

	public void limparBuscaUsuario(){
		this.usuarioBuscar = new Usuario();
		this.tipoDeBusca = null;
		carregaDadosUsuario();
		PageUtil.atualizarComponente("formListUsuarios");
	}

	public void carregaDadosUsuario(){
		this.listaDeUsuarios = usuarioDAO.retornaListaDeUsuarios();
		this.tipoDeBusca = null;
		usuarioBuscar = new Usuario();
		tipoDeBusca = null;
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
			PageUtil.mensagemDeSucesso("Usuário editado com sucesso!");
			carregaDadosUsuario();
		}else {
			PageUtil.mensagemDeErro("Erro ao editar usuário!");
		}
		PageUtil.atualizarComponente("formListUsuarios");
		PageUtil.fecharDialog("dlgEditUsuario");
	}
	
	public void excluirUsuario() {
		if(usuarioDAO.excluirUsuario(this.usuarioSelecionado)) {
			PageUtil.mensagemDeSucesso("Usuário excluido com sucesso!");
			carregaDadosUsuario();
		}else {
			PageUtil.mensagemDeErro("Erro ao excluir usuário!");
		}
		PageUtil.atualizarComponente("formListUsuarios");
		PageUtil.fecharDialog("dlgDelUsuario");
	}
	
	public void cadastrarUsuario() {
		if(usuarioDAO.verificaSeUsuarioJaExiste(user)) {
			PageUtil.mensagemDeErro("Email já existente");
		}else {
			if(EmailValidator.getInstance().isValid(user.getEmail())) {
				if(usuarioDAO.cadastrarUsuario(user)) {
					PageUtil.mensagemDeSucesso("Usuário cadastrado com sucesso!");
					PageUtil.fecharDialog("dlgCadUsuario");
					carregaDadosUsuario();
					this.user = new Usuario();
				}else {
					PageUtil.mensagemDeErro("Erro ao cadastrar usuário!");
				}
			}else {
				PageUtil.mensagemDeErro("E-mail inválido");
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

	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public String getTipoDeBusca() {
		return tipoDeBusca;
	}

	public void setTipoDeBusca(String tipoDeBusca) {
		this.tipoDeBusca = tipoDeBusca;
	}

	public Usuario getUsuarioBuscar() {
		return usuarioBuscar;
	}
}
