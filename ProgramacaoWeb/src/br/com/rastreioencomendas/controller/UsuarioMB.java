package br.com.rastreioencomendas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.rastreioencomendas.model.Endereco;
import br.com.rastreioencomendas.util.*;
import org.apache.commons.validator.routines.EmailValidator;

import br.com.rastreioencomendas.dao.UsuarioDAO;
import br.com.rastreioencomendas.model.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioMB extends AbstractUsuarioMB {

	private Usuario usuarioCadastrar;
	private Usuario usuarioLogado;
	private Usuario usuarioSelecionado;
	private Usuario usuarioBuscar = new Usuario();;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<Usuario> listaDeUsuarios = new ArrayList<>();
	private String tipoDeBusca;
	
	public UsuarioMB() {
		usuarioCadastrar = new Usuario();
		usuarioSelecionado = new Usuario();
	}
	
	public void login() throws IOException {
		Usuario usuarioLogado = usuarioDAO.login(usuarioCadastrar);
		
		if(usuarioLogado != null) {
			SessionUtil.adicionaObjetoUsuarioNaSessao(usuarioLogado);
			this.usuarioCadastrar = new Usuario();
			this.usuarioLogado = usuarioLogado;
			PageUtil.redirecionarParaPaginaPrincipalAdministrador();
		}else {
			PageUtil.mensagemDeErro(MENSAGEM_USUARIO_OU_SENHA_INVALIDOS);
		}
	}

	public void abrirDlgCadUsuario(){
		usuarioCadastrar = new Usuario();
		PageUtil.abrirDialog(DIALOG_CADASTRO_USUARIO);
		PageUtil.atualizarComponente(FORM_CADASTRO_USUARIO);
	}

	public void buscarUsuarios(){
		if(tipoDeBusca.equals(null)){
			PageUtil.mensagemDeErro(MENSAGEM_SELECIONAR_TIPO_DE_BUSCA);
		}else if(usuarioBuscar.getEmail() == null && usuarioBuscar.getNome() == null){
			PageUtil.mensagemDeErro(MENSAGEM_INSIRA_BUSCA);
		}else{
			listaDeUsuarios = usuarioDAO.buscarUsuarios(tipoDeBusca, usuarioBuscar);
		}
		PageUtil.atualizarComponente(FORM_LIST_USUARIOS);
	}

	public void limparBuscaUsuario(){
		this.usuarioBuscar = new Usuario();
		this.tipoDeBusca = null;
		carregaDadosUsuario();
		PageUtil.atualizarComponente(FORM_LIST_USUARIOS);
	}

	public void carregaDadosUsuario(){
		this.listaDeUsuarios = usuarioDAO.retornaListaDeUsuarios();
		this.tipoDeBusca = null;
		this.usuarioBuscar = new Usuario();
		this.tipoDeBusca = null;
	}
	
	public String retornaNomeUsuarioLogado() {
		String primeiroNome = "";
		if(SessionUtil.verificaSeUsuarioEstaNaSessao()) {
			Usuario usuarioLogado = (Usuario) SessionUtil.recuperaObjetoDaSessao(OBJ_USUARIO);
			String[] nome = usuarioLogado.getNome().split(" ");
			primeiroNome = nome[0];
		}
		return primeiroNome.toUpperCase();
	}
	
	public void deslogar() throws IOException {
		SessionUtil.encerrarSessao();
		PageUtil.redirecionarParaPaginaPrincipal();
		this.usuarioLogado = null;
	}

	public void buscarCepUsuario(String tipo) throws ViaCEPException {
		if(tipo.equals(CADASTRO)){
			if(!usuarioCadastrar.getEndereco().getCep().equals(null)){
				Endereco endereco = ViaCepUtil.buscarEndereco(usuarioCadastrar.getEndereco());
				usuarioCadastrar.setEndereco(endereco);
			}
		}else if(tipo.equals(EDICAO)){
			if(!usuarioSelecionado.getEndereco().getCep().equals(null)){
				Endereco endereco = ViaCepUtil.buscarEndereco(usuarioSelecionado.getEndereco());
				usuarioSelecionado.setEndereco(endereco);
			}
		}
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
		PageUtil.abrirDialog(DIALOG_EDITAR_USUARIO);
	}
	
	public void selecionaUsuarioParaExcluir(Usuario usuario) {
		this.usuarioSelecionado = usuario;
		PageUtil.atualizarComponente(FORM_DELETAR_USUARIO);
		PageUtil.abrirDialog(DIALOG_DELETAR_USUARIO);
	}
	
	public void editarUsuario() {
		if(usuarioDAO.editarUsuario(this.usuarioSelecionado)) {
			PageUtil.mensagemDeSucesso(MENSAGEM_USUARIO_EDITADO_COM_SUCESSO);
			carregaDadosUsuario();
		}else {
			PageUtil.mensagemDeErro(MENSAGEM_ERRO_EDITAR_USUARIO);
		}
		PageUtil.atualizarComponente(FORM_LIST_USUARIOS);
		PageUtil.fecharDialog(DIALOG_EDITAR_USUARIO);
	}
	
	public void excluirUsuario() {
		if(usuarioDAO.excluirUsuario(this.usuarioSelecionado)) {
			PageUtil.mensagemDeSucesso(MENSAGEM_USUARIO_EXCLUIDO_COM_SUCESSO);
			carregaDadosUsuario();
		}else {
			PageUtil.mensagemDeErro(MENSAGEM_ERRO_EXCLUIR_USUARIO);
		}
		PageUtil.atualizarComponente(FORM_LIST_USUARIOS);
		PageUtil.fecharDialog(DIALOG_DELETAR_USUARIO);
	}
	
	public void cadastrarUsuario() {
		if(usuarioDAO.verificaSeUsuarioJaExiste(usuarioCadastrar)) {
			PageUtil.mensagemDeErro(MENSAGEM_EMAIL_EXISTENTE);
		}else {
			if(EmailValidator.getInstance().isValid(usuarioCadastrar.getEmail())) {
				if(usuarioDAO.cadastrarUsuario(usuarioCadastrar)) {
					PageUtil.mensagemDeSucesso(MENSAGEM_USUARIO_CADASTRADO_COM_SUCESSO);
					PageUtil.fecharDialog(DIALOG_CADASTRO_USUARIO);
					carregaDadosUsuario();
					this.usuarioCadastrar = new Usuario();
				}else {
					PageUtil.mensagemDeErro(MENSAGEM_ERRO_CADASTRO_USUARIO);
				}
			}else {
				PageUtil.mensagemDeErro(MENSAGEM_EMAIL_INVALIDO);
			}
		}
		PageUtil.atualizarComponente(FORM_LOGIN);
		PageUtil.atualizarComponente(FORM_LIST_USUARIOS);
		PageUtil.atualizarComponente(FORM_CADASTRO_USUARIO);
	}
	
	public Usuario getUsuarioCadastrar() {
		return usuarioCadastrar;
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
