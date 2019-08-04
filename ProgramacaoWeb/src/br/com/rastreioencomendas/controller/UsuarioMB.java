package br.com.rastreioencomendas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.rastreioencomendas.enums.TipoDeBuscaPorUsuario;
import br.com.rastreioencomendas.model.builder.UsuarioBuilder;
import br.com.rastreioencomendas.util.*;
import org.apache.commons.validator.routines.EmailValidator;

import br.com.rastreioencomendas.dao.UsuarioDAO;
import br.com.rastreioencomendas.model.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioMB extends AbstractMB{

    private Usuario usuarioCadastrar = new UsuarioBuilder().build();
    private Usuario usuarioLogado;
    private Usuario usuarioSelecionado = new UsuarioBuilder().build();
    private Usuario usuarioBuscar = new UsuarioBuilder().build();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private List<Usuario> listaDeUsuarios = new ArrayList<>();
    private TipoDeBuscaPorUsuario tipoDeBusca;

    public UsuarioMB() {

    }

    public void login() throws IOException {
        Usuario usuarioLogado = usuarioDAO.login(usuarioCadastrar);

        if (usuarioLogado != null) {
            SessionUtil.adicionaObjetoUsuarioNaSessao(usuarioLogado);
            this.usuarioCadastrar = new UsuarioBuilder().build();
            this.usuarioLogado = usuarioLogado;
            PageUtil.redirecionarParaPaginaPrincipalAdministrador();
        } else {
            PageUtil.mensagemDeErro(MENSAGEM_USUARIO_OU_SENHA_INVALIDOS);
        }
    }

    public void abrirDlgCadUsuario() {
        usuarioCadastrar = new UsuarioBuilder().build();
        PageUtil.abrirDialog(DIALOG_CADASTRO_USUARIO);
        PageUtil.atualizarComponente(FORM_CADASTRO_USUARIO);
    }

    public void buscarUsuarios() {
        if (tipoDeBusca.equals(null)) {
            PageUtil.mensagemDeErro(MENSAGEM_SELECIONAR_TIPO_DE_BUSCA);
        }
        else if (usuarioBuscar.isNomeEhEmailNulos()) {
            PageUtil.mensagemDeErro(MENSAGEM_INSIRA_BUSCA);
        }
        else {
            listaDeUsuarios = usuarioDAO.buscarUsuarios(tipoDeBusca, usuarioBuscar);
        }
        PageUtil.atualizarComponente(FORM_LIST_USUARIOS);
    }

    public void limparBuscaUsuario() {
        this.usuarioBuscar = new UsuarioBuilder().build();
        this.tipoDeBusca = null;
        carregaDadosUsuarios();
        PageUtil.atualizarComponente(FORM_LIST_USUARIOS);
    }

    public void carregaDadosUsuarios() {
        this.listaDeUsuarios = usuarioDAO.retornaListaDeUsuarios();
        this.usuarioBuscar = new UsuarioBuilder().build();
        this.tipoDeBusca = null;
    }

    public String retornaPrimeiroNomeUsuarioLogado() {
        String primeiroNome = "";
        if (SessionUtil.verificaSeUsuarioEstaNaSessao()) {
            Usuario usuarioLogado = (Usuario) SessionUtil.recuperaObjetoDaSessao(OBJ_USUARIO);
            primeiroNome = StringUtil.retornaPrimeiraPalavraDeUmTexto(usuarioLogado.getNome());
        }
        return primeiroNome.toUpperCase();
    }

    public void deslogarEhRedirecionarParaHome() throws IOException {
        SessionUtil.encerrarSessao();
        PageUtil.redirecionarParaPaginaPrincipal();
        this.usuarioLogado = null;
    }

    public void buscarCepUsuario(String tipo) throws ViaCEPException {

        if(VerificadorUtil.isNaoEstaNulo(usuarioCadastrar.getEndereco().getCep())
                || VerificadorUtil.isNaoEstaNulo(usuarioSelecionado.getEndereco().getCep())){

            if(tipo.equals(CADASTRO)){
                usuarioCadastrar.setEndereco(ViaCepUtil.buscarEndereco(usuarioCadastrar.getEndereco()));
            }
            else if(tipo.equals(EDICAO)){
                usuarioSelecionado.setEndereco(ViaCepUtil.buscarEndereco(usuarioSelecionado.getEndereco()));
            }
        }
    }

    public List<TipoDeBuscaPorUsuario> listaTipoDeBuscaPorUsuario(){
        return Arrays.asList(TipoDeBuscaPorUsuario.values());
    }

    public Boolean isSessaoAtiva() {
        return SessionUtil.verificaSeUsuarioEstaNaSessao();
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
        if (usuarioDAO.editarUsuario(usuarioSelecionado)) {
            PageUtil.mensagemDeSucesso(MENSAGEM_USUARIO_EDITADO_COM_SUCESSO);
            carregaDadosUsuarios();
        } else {
            PageUtil.mensagemDeErro(MENSAGEM_ERRO_EDITAR_USUARIO);
        }
        PageUtil.atualizarComponente(FORM_LIST_USUARIOS);
        PageUtil.fecharDialog(DIALOG_EDITAR_USUARIO);
    }

    public void excluirUsuario() {
        if (usuarioDAO.excluirUsuario(usuarioSelecionado)) {
            PageUtil.mensagemDeSucesso(MENSAGEM_USUARIO_EXCLUIDO_COM_SUCESSO);
            carregaDadosUsuarios();
        } else {
            PageUtil.mensagemDeErro(MENSAGEM_ERRO_EXCLUIR_USUARIO);
        }
        PageUtil.atualizarComponente(FORM_LIST_USUARIOS);
        PageUtil.fecharDialog(DIALOG_DELETAR_USUARIO);
    }

    public void cadastrarUsuario() {
        if (usuarioDAO.verificaSeUsuarioJaExiste(usuarioCadastrar)) {
            PageUtil.mensagemDeErro(MENSAGEM_EMAIL_EXISTENTE);
        } else {
            if (EmailValidator.getInstance().isValid(usuarioCadastrar.getEmail())) {
                if (usuarioDAO.cadastrarUsuario(usuarioCadastrar)) {
                    PageUtil.mensagemDeSucesso(MENSAGEM_USUARIO_CADASTRADO_COM_SUCESSO);
                    PageUtil.fecharDialog(DIALOG_CADASTRO_USUARIO);
                    carregaDadosUsuarios();
                    this.usuarioCadastrar = new UsuarioBuilder().build();
                } else {
                    PageUtil.mensagemDeErro(MENSAGEM_ERRO_CADASTRO_USUARIO);
                }
            } else {
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

    public Usuario getUsuarioBuscar() {
        return usuarioBuscar;
    }

    public TipoDeBuscaPorUsuario getTipoDeBusca() {
        return tipoDeBusca;
    }

    public void setTipoDeBusca(TipoDeBuscaPorUsuario tipoDeBusca) {
        this.tipoDeBusca = tipoDeBusca;
    }
}
