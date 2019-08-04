package br.com.rastreioencomendas.controller;

public abstract class AbstractMB {

    protected static final Integer TAMANHO_CODIGO_DE_RASTREIO = 13;
    protected static final String RECEBIDO = "RECEBIDO";
    protected static final String TRANSITO = "TRANSITO";
    protected static final String ENTREGUE = "ENTREGUE";
    protected static final String MENSAGEM_NENHUM_RESULTADO_ENCONTRADO = "Nenhum resultado encontrado";
    protected static final String DIALOG_CADASTRO_PACOTE = "dlgCadPacote";
    protected static final String FORM_CADASTRO_PACOTE = "formCadPacote";
    protected static final String MENSAGEM_CODIGO_RASTREIO_VAZIO = "Gere um código de rastreio";
    protected static final String MENSAGEM_PESO_INVALIDO = "Peso inválido";
    protected static final String MENSAGEM_NUMERO_ENDERECO_INVALIDO = "Número endereço inválido";
    protected static final String MENSAGEM_BAIRRO_VAZIO = "Informe o Bairro";
    protected static final String MENSAGEM_ESTADO_VAZIO = "Informe o Estado";
    protected static final String MENSAGEM_CIDADE_VAZIA = "Informe a Cidade";
    protected static final String MENSAGEM_LOGRADOURO_VAZIO = "Informe o logradouro";
    protected static final String MENSAGEM_PACOTE_CADASTRADO_COM_SUCESSO = "Pacote cadastrado com sucesso!";
    protected static final String FORM_LISTA_PACOTES = "formListaPacotes";
    protected static final String MENSAGEM_ERRO_CADASTRO_PACOTE = "Erro ao cadastrar pacote!";
    protected static final String FORM_HISTORICO_PACOTES = "formHistoricoPacote";
    protected static final String DIALOG_HISTORICO_PACOTES = "dlgHistoricoPacote";
    protected static final String DIALOG_EXCLUSAO_ATUALIZACAO_= "dlgExcluirAtualizacao";
    protected static final String FORM_EXCLUSAO_ATUALIZACAO = "formExcluirAtualizacao";
    protected static final String MENSAGEM_ATUALIZACAO_EXCLUIDA_COM_SUCESSO = "Atualização excluída com sucesso";
    protected static final String MENSAGEM_ERRO_EXCLUIR_ATUALIZACAO = "Erro ao excluir atualização";
    protected static final String FORM_CADASTRO_ATUALIZACAO = "formCadAtualizacao";
    protected static final String DIALOG_CADASTRO_ATUALIZACAO = "dlgCadAtualizacao";
    protected static final String DIALOG_EDITAR_ATUALIZACAO = "dlgEditarAtualizacao";
    protected static final String FORM_EDITAR_ATUALIZACAO = "formEditarAtualizacao";
    protected static final String MENSAGEM_ATUALIZACAO_EDITADA_COM_SUCESSO = "Atualização editada com sucesso";
    protected static final String MENSAGEM_ERRO_EDITAR_ATUALIZACAO = "Erro ao editar atualização";
    protected static final String MENSAGEM_SUCESSO_CADASTRO_ATUALIZACAO = "Atualizacação cadastrada com sucesso";
    protected static final String MENSAGEM_ERRO_CADASTRO_ATUALIZACAO = "Erro ao cadastrar atualização";
    protected static final String DIALOG_STATUS_PACOTE = "dlgStatusPacote";
    protected static final String FORM_STATUS_PACOTE = "formStatusPacote";
    protected static final String DIALOG_CADASTRO_EMPRESA = "dlgCadEmpresa";
    protected static final String FORM_CADASTRO_EMPRESA = "formCadEmpresa";
    protected static final String MENSAGEM_CADASTRO_EMPRESA_SUCESSO = "Empresa cadastrada com sucesso";
    protected static final String FORM_LISTA_EMPRESAS = "formListaEmpresas";
    protected static final String MENSAGEM_CADASTRO_EMPRESA_ERRO = "Erro ao cadastrar empresa";
    protected static final String DIALOG_CADASTRO_STATUS = "dlgCadStatus";
    protected static final String DIALOG_EDITAR_STATUS = "dlgEditarStatus";
    protected static final String DIALOG_EXCLUIR_STATUS = "dlgExcluirStatus";
    protected static final String FORM_CADASTRO_STATUS = "formCadStatus";
    protected static final String FORM_EDITAR_STATUS = "formEditarStatus";
    protected static final String FORM_EXCLUIR_STATUS = "formExcluirStatus";
    protected static final String FORM_STATUS = "formStatusPacote";
    protected static final String MENSAGEM_STATUS_CADASTRADO_COM_SUCESSO = "Status cadastrado com sucesso";
    protected static final String MENSAGEM_ERRO_CADASTRAR_STATUS = "Erro ao cadastrar status";
    protected static final String MENSAGEM_STATUS_EDITADO_COM_SUCESSO = "Status editado com sucesso";
    protected static final String MENSAGEM_ERRO_EDITAR_STATUS = "Erro ao editar status";
    protected static final String MENSAGEM_STATUS_EXCLUIDO_SUCESSO = "Status excluido com sucesso";
    protected static final String MENSAGEM_STATUS_EXCLUIDO_ERRO = "Erro ao excluir status";
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
    public final String CADASTRO = "CADASTRO";
    public final String EDICAO = "EDIÇÃO";

    public String getCADASTRO() {
        return CADASTRO;
    }

    public String getEDICAO() {
        return EDICAO;
    }
}
