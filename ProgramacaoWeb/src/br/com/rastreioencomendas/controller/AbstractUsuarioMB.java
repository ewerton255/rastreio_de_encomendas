package br.com.rastreioencomendas.controller;

public class AbstractUsuarioMB {
    public static final String BUSCA_POR_NOME = "N";
    public static final String BUSCA_POR_EMAIL = "E";
    public static final String SIMBOLO_PORCETAGEM = "%";
    public final String CADASTRO = "CADASTRO";
    public final String EDICAO = "EDIÇÃO";
    protected static final String MENSAGEM_USUARIO_OU_SENHA_INVALIDOS = "Usuário ou senha inválido!";
    protected static final String DIALOG_CADASTRO_USUARIO = "dlgCadUsuario";
    protected static final String FORM_CADASTRO_USUARIO = "formCadUsuario";
    protected static final String MENSAGEM_SELECIONAR_TIPO_DE_BUSCA = "Selecione o tipo de busca";
    protected static final String MENSAGEM_INSIRA_BUSCA = "Insira a busca";
    protected static final String FORM_LIST_USUARIOS = "formListUsuarios";
    protected static final String OBJ_USUARIO = "usuarioLogado";
    protected static final String DIALOG_EDITAR_USUARIO = "dlgEditUsuario";
    protected static final String FORM_DELETAR_USUARIO = "formDelUsuario";
    protected static final String DIALOG_DELETAR_USUARIO = "dlgDelUsuario";
    protected static final String MENSAGEM_USUARIO_EDITADO_COM_SUCESSO = "Usuário editado com sucesso";
    protected static final String MENSAGEM_ERRO_EDITAR_USUARIO = "Erro ao editar usuário";
    protected static final String MENSAGEM_USUARIO_EXCLUIDO_COM_SUCESSO = "Usuário excluido com sucesso!";
    protected static final String MENSAGEM_ERRO_EXCLUIR_USUARIO = "Erro ao excluir usuário!";
    protected static final String MENSAGEM_EMAIL_EXISTENTE = "Email já existente";
    protected static final String MENSAGEM_USUARIO_CADASTRADO_COM_SUCESSO = "Usuário cadastrado com sucesso!";
    protected static final String MENSAGEM_ERRO_CADASTRO_USUARIO = "Erro ao cadastrar usuário";
    protected static final String MENSAGEM_EMAIL_INVALIDO = "E-mail inválido";
    protected static final String FORM_LOGIN = "formLogin";

    public String getCADASTRO() {
        return CADASTRO;
    }

    public String getEDICAO() {
        return EDICAO;
    }
}
