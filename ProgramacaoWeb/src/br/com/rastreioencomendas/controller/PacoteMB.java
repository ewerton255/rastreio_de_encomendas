package br.com.rastreioencomendas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.rastreioencomendas.dao.PacoteDAO;
import br.com.rastreioencomendas.model.HistoricoPacote;
import br.com.rastreioencomendas.model.Pacote;
import br.com.rastreioencomendas.model.builder.HistoricoPacoteBuilder;
import br.com.rastreioencomendas.model.builder.PacoteBuilder;
import br.com.rastreioencomendas.util.PageUtil;
import br.com.rastreioencomendas.util.VerificadorUtil;
import br.com.rastreioencomendas.util.ViaCEP;
import br.com.rastreioencomendas.util.ViaCEPException;

@SessionScoped
@ManagedBean
public class PacoteMB extends AbstractMB{

    private PacoteDAO pacoteDAO = new PacoteDAO();
    private Pacote pacoteParaCadastrar = new PacoteBuilder().build();
    private HistoricoPacote novaAtualizacao = new HistoricoPacoteBuilder().build();
    private HistoricoPacote atualizacaoSelecionada = new HistoricoPacoteBuilder().build();
    private String tipoDocumento;
    private Pacote pacoteSelecionado = new PacoteBuilder().build();
    private Pacote pacoteParaBuscar = new PacoteBuilder().build();
    private List<HistoricoPacote> listaHistoricoRastreio = new ArrayList<>();


    public PacoteMB() {

    }

    public String gerarCodigoRastreio() {
        String codigo = "";
        Random random = new Random();

        for (int i = 0; i < TAMANHO_CODIGO_DE_RASTREIO; i++) {

            if (i == 0 || i == 1 || i == TAMANHO_CODIGO_DE_RASTREIO -1 || i == TAMANHO_CODIGO_DE_RASTREIO - 2) {
                codigo += String.valueOf((char) (65 + random.nextInt(90 - 65)));
            } else {
                codigo += String.valueOf(random.nextInt((9 - 1) + 1) + 1);
            }
        }
        return codigo.toUpperCase();
    }

    public String retornaStatusEncomenda() {
        String status = "";
        if (listaHistoricoRastreio != null) {
            if (listaHistoricoRastreio.size() > 0) {
                if (listaHistoricoRastreio.size() == 1) {
                    status = RECEBIDO;
                } else if (listaHistoricoRastreio.size() > 1) {
                    if (listaHistoricoRastreio.get(listaHistoricoRastreio.size() - 1).getStatus().getId() != 5) {
                        status = TRANSITO;
                    } else if (listaHistoricoRastreio.get(listaHistoricoRastreio.size() - 1).getStatus().getId() == 5) {
                        status = ENTREGUE;
                    }
                }
            }
        }
        return status;
    }

    public void carregaDadosPageRastrear() {
        this.pacoteParaBuscar = new PacoteBuilder().build();
        this.listaHistoricoRastreio = new ArrayList<>();
    }

    public void buscarPacote() {
        this.listaHistoricoRastreio = pacoteDAO.retornaListaDeHistorico(pacoteParaBuscar.getCodigoRastreio());
        if (listaHistoricoRastreio.size() == 0) {
            PageUtil.mensagemDeErro(MENSAGEM_NENHUM_RESULTADO_ENCONTRADO);
        }
    }

    public void retornaCodigoDeRastreio() {
        String codigo = gerarCodigoRastreio();
        while (pacoteDAO.verificaSeCodigoJaExiste(codigo)) {
            codigo = gerarCodigoRastreio();
        }
        this.pacoteParaCadastrar.setCodigoRastreio(codigo);
    }

    public void abrirDialogCadastroPacote() {
        this.pacoteParaCadastrar = new PacoteBuilder().build();
        this.tipoDocumento = null;
        PageUtil.abrirDialog(DIALOG_CADASTRO_PACOTE);
        PageUtil.atualizarComponente(FORM_CADASTRO_PACOTE);
    }

    public void cadastrarPacote() {
        Boolean valido = true;

        if (pacoteParaCadastrar.getCodigoRastreio() == null) {
            PageUtil.mensagemDeErro(MENSAGEM_CODIGO_RASTREIO_VAZIO);
            valido = false;
        }
        if (pacoteParaCadastrar.getPeso() <= 0) {
            PageUtil.mensagemDeErro(MENSAGEM_PESO_INVALIDO);
            valido = false;
        }
        if (pacoteParaCadastrar.getEnderecoDestinatario().getNumero() <= 0) {
            PageUtil.mensagemDeErro(MENSAGEM_NUMERO_ENDERECO_INVALIDO);
            valido = false;
        }
        if (pacoteParaCadastrar.getEnderecoDestinatario().getBairro() == null) {
            PageUtil.mensagemDeErro(MENSAGEM_BAIRRO_VAZIO);
            valido = false;
        }
        if (pacoteParaCadastrar.getEnderecoDestinatario().getEstado() == null) {
            PageUtil.mensagemDeErro(MENSAGEM_ESTADO_VAZIO);
            valido = false;
        }
        if (pacoteParaCadastrar.getEnderecoDestinatario().getCidade() == null) {
            PageUtil.mensagemDeErro(MENSAGEM_CIDADE_VAZIA);
            valido = false;
        }
        if (pacoteParaCadastrar.getEnderecoDestinatario().getLogradouro() == null) {
            PageUtil.mensagemDeErro(MENSAGEM_LOGRADOURO_VAZIO);
            valido = false;
        }
        if (valido) {
            if (pacoteDAO.cadastrarPacote(this.pacoteParaCadastrar)) {
                PageUtil.mensagemDeSucesso(MENSAGEM_PACOTE_CADASTRADO_COM_SUCESSO);
                PageUtil.atualizarComponente(FORM_LISTA_PACOTES);
                PageUtil.fecharDialog(DIALOG_CADASTRO_PACOTE);
            } else {
                PageUtil.mensagemDeErro(MENSAGEM_ERRO_CADASTRO_PACOTE);
            }
        }

    }

    public void buscarCepDestinatario() throws ViaCEPException {
        ViaCEP viaCep = new ViaCEP();

        viaCep.buscar(this.pacoteParaCadastrar.getEnderecoDestinatario().getCep());

        this.pacoteParaCadastrar.getEnderecoDestinatario().setEstado(viaCep.getUf());
        this.pacoteParaCadastrar.getEnderecoDestinatario().setBairro(viaCep.getBairro());
        this.pacoteParaCadastrar.getEnderecoDestinatario().setLogradouro(viaCep.getLogradouro());
        this.pacoteParaCadastrar.getEnderecoDestinatario().setCidade(viaCep.getLocalidade());
    }

    public void abrirHistoricoDoPacote(Pacote pacote) {
        this.pacoteSelecionado = pacote;
        populaListaDeHistorico();
        PageUtil.atualizarComponente(FORM_HISTORICO_PACOTES);
        PageUtil.abrirDialog(DIALOG_HISTORICO_PACOTES);
    }

    public List<HistoricoPacote> populaListaDeHistorico() {
        List<HistoricoPacote> lista = new ArrayList<>();
        if (VerificadorUtil.isNaoEstaNulo(pacoteSelecionado.getCodigoRastreio())) {
            lista = pacoteDAO.retornaListaDeHistorico(this.pacoteSelecionado.getCodigoRastreio());
        }
        return lista;
    }

    public void abrirDlgExcluirAtualizacao(HistoricoPacote atualizacao) {
        this.atualizacaoSelecionada = atualizacao;
        PageUtil.abrirDialog(DIALOG_EXCLUSAO_ATUALIZACAO_);
        PageUtil.atualizarComponente(FORM_EXCLUSAO_ATUALIZACAO);
    }

    public void excluirAtualizacao() {
        if (pacoteDAO.excluirAtualizacao(atualizacaoSelecionada)) {
            PageUtil.mensagemDeSucesso(MENSAGEM_ATUALIZACAO_EXCLUIDA_COM_SUCESSO);
            PageUtil.fecharDialog(DIALOG_EXCLUSAO_ATUALIZACAO_);
            PageUtil.atualizarComponente(FORM_HISTORICO_PACOTES);
        } else {
            PageUtil.mensagemDeErro(MENSAGEM_ERRO_EXCLUIR_ATUALIZACAO);
        }
    }

    public void abrirDialogCadastroAtualizacao(Pacote pacote) {
        this.pacoteSelecionado = pacote;
        this.novaAtualizacao = new HistoricoPacoteBuilder().build();
        PageUtil.atualizarComponente(FORM_CADASTRO_ATUALIZACAO);
        PageUtil.abrirDialog(DIALOG_CADASTRO_ATUALIZACAO);
    }

    public void abrirDialogEditarAtualizacao(HistoricoPacote atualizacao) {
        this.atualizacaoSelecionada = atualizacao;
        PageUtil.abrirDialog(DIALOG_EDITAR_ATUALIZACAO);
        PageUtil.atualizarComponente(FORM_EDITAR_ATUALIZACAO);
    }

    public void editarAtualizacao() {
        if (pacoteDAO.editarAtualizacao(atualizacaoSelecionada)) {
            PageUtil.mensagemDeSucesso(MENSAGEM_ATUALIZACAO_EDITADA_COM_SUCESSO);
            PageUtil.fecharDialog(DIALOG_EDITAR_ATUALIZACAO);
            PageUtil.atualizarComponente(FORM_HISTORICO_PACOTES);
        } else {
            PageUtil.mensagemDeErro(MENSAGEM_ERRO_EDITAR_ATUALIZACAO);
        }
    }

    public Boolean renderizaLocalizacao() {
        Boolean renderiza = false;

        if (VerificadorUtil.isNaoEstaNulo(novaAtualizacao.getStatus().getId())) {
            if (novaAtualizacao.getStatus().getId() == 1 || novaAtualizacao.getStatus().getId() == 2) {
                renderiza = true;
            }
        }
        return renderiza;
    }

    public Boolean renderizaLocalizacaoEditar() {
        Boolean renderiza = false;

        if (VerificadorUtil.isNaoEstaNulo(atualizacaoSelecionada.getStatus().getId())) {
            if (atualizacaoSelecionada.getStatus().getId() == 1 || atualizacaoSelecionada.getStatus().getId() == 2) {
                renderiza = true;
            }
        }
        return renderiza;
    }

    public void cadastrarNovaAtualizacao() {
        if (pacoteDAO.cadastrarAtualizacao(pacoteSelecionado, novaAtualizacao)) {
            PageUtil.mensagemDeSucesso(MENSAGEM_SUCESSO_CADASTRO_ATUALIZACAO);
            PageUtil.fecharDialog(DIALOG_CADASTRO_ATUALIZACAO);
        } else {
            PageUtil.mensagemDeErro(MENSAGEM_ERRO_CADASTRO_ATUALIZACAO);
            PageUtil.fecharDialog(DIALOG_CADASTRO_ATUALIZACAO);
        }
    }

    public void abrirDlgStatusPacote() {
        PageUtil.abrirDialog(DIALOG_STATUS_PACOTE);
        PageUtil.atualizarComponente(FORM_STATUS_PACOTE);
    }

    public List<Pacote> retornaListaDePacotes() {
        return pacoteDAO.retornaListaDePacotes();
    }

    public Pacote getPacoteParaCadastrar() {
        return pacoteParaCadastrar;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Pacote getPacoteSelecionado() {
        return pacoteSelecionado;
    }

    public List<HistoricoPacote> getListaHistoricoRastreio() {
        return listaHistoricoRastreio;
    }

    public Pacote getPacoteParaBuscar() {
        return pacoteParaBuscar;
    }

    public HistoricoPacote getNovaAtualizacao() {
        return novaAtualizacao;
    }

    public HistoricoPacote getAtualizacaoSelecionada() {
        return atualizacaoSelecionada;
    }

}
