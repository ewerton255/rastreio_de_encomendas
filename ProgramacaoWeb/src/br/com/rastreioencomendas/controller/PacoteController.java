package br.com.rastreioencomendas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.rastreioencomendas.dao.PacoteDAO;
import br.com.rastreioencomendas.model.HistoricoModel;
import br.com.rastreioencomendas.model.Pacote;
import br.com.rastreioencomendas.util.PageUtil;
import br.com.rastreioencomendas.util.ViaCEP;
import br.com.rastreioencomendas.util.ViaCEPException;

@SessionScoped
@ManagedBean
public class PacoteController extends AbstractPacoteController {

    private PacoteDAO pacoteDAO = new PacoteDAO();
    private Pacote pacoteParaCadastrar;
    private HistoricoModel novaAtualizacao = new HistoricoModel();
    private HistoricoModel atualizacaoSelecionada = new HistoricoModel();
    private String tipoDocumento;
    private Pacote pacoteSelecionado;
    private Pacote pacoteParaBuscar;
    private List<HistoricoModel> listaHistoricoRastreio;

    public PacoteController() {
        this.pacoteSelecionado = new Pacote();
        this.pacoteParaCadastrar = new Pacote();
        this.listaHistoricoRastreio = new ArrayList<>();
        this.pacoteParaBuscar = new Pacote();
    }

    public String gerarCodigoRastreio() {
        String codigo = "";
        Random random = new Random();

        for (int i = 0; i < 13; i++) {

            if (i == 0 || i == 1 || i == 11 || i == 12) {
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
        this.pacoteParaBuscar = new Pacote();
        this.listaHistoricoRastreio = new ArrayList<>();
    }

    public void buscarPacote() {
        this.listaHistoricoRastreio = pacoteDAO.retornalIstaParaRastreio(pacoteParaBuscar.getCodigoRastreio());
        if (listaHistoricoRastreio.size() == 0) {
            PageUtil.mensagemDeErro("Nenhum resultado encontrado");
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
        this.pacoteParaCadastrar = new Pacote();
        this.tipoDocumento = null;
        PageUtil.abrirDialog("dlgCadPacote");
        PageUtil.atualizarComponente("formCadPacote");
    }

    public void cadastrarPacote() {
        Boolean valido = true;

        if (pacoteParaCadastrar.getCodigoRastreio() == null) {
            PageUtil.mensagemDeErro("Gere um c�digo de rastreio");
            valido = false;
        }
        if (pacoteParaCadastrar.getPeso() <= 0) {
            PageUtil.mensagemDeErro("Peso inv�lido");
            valido = false;
        }
        if (pacoteParaCadastrar.getEnderecoDestinatario().getNumero() <= 0) {
            PageUtil.mensagemDeErro("N�mero endere�o inv�lido");
            valido = false;
        }
        if (pacoteParaCadastrar.getEnderecoDestinatario().getBairro() == null) {
            PageUtil.mensagemDeErro("Informe o Bairro");
            valido = false;
        }
        if (pacoteParaCadastrar.getEnderecoDestinatario().getEstado() == null) {
            PageUtil.mensagemDeErro("Informe o Estado");
            valido = false;
        }
        if (pacoteParaCadastrar.getEnderecoDestinatario().getCidade() == null) {
            PageUtil.mensagemDeErro("Informe a Cidade");
            valido = false;
        }
        if (pacoteParaCadastrar.getEnderecoDestinatario().getLogradouro() == null) {
            PageUtil.mensagemDeErro("Informe o logradouro");
            valido = false;
        }
        if (valido) {
            if (pacoteDAO.cadastrarPacote(this.pacoteParaCadastrar)) {
                PageUtil.mensagemDeSucesso("Pacote cadastrado com sucesso!");
                PageUtil.atualizarComponente("formListaPacotes");
                PageUtil.fecharDialog("dlgCadPacote");
            } else {
                PageUtil.mensagemDeErro("Erro ao cadastrar pacote!");
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
        retornaListaDeHistorico();
        PageUtil.atualizarComponente("formHistoricoPacote");
        PageUtil.abrirDialog("dlgHistoricoPacote");
    }

    public List<HistoricoModel> retornaListaDeHistorico() {
        List<HistoricoModel> lista = new ArrayList<>();
        if (this.pacoteSelecionado.getCodigoRastreio() != null) {
            lista = pacoteDAO.retornaListaDeHistorico(this.pacoteSelecionado.getId());
        }
        return lista;
    }

    public void abrirDlgExcluirAtualizacao(HistoricoModel atualizacao) {
        this.atualizacaoSelecionada = atualizacao;
        PageUtil.abrirDialog("dlgExcluirAtualizacao");
        PageUtil.atualizarComponente("formExcluirAtualizacao");
    }

    public void excluirAtualizacao() {
        if (pacoteDAO.excluirAtualizacao(atualizacaoSelecionada)) {
            PageUtil.mensagemDeSucesso("Atualiza��o exclu�da com sucesso");
            PageUtil.fecharDialog("dlgExcluirAtualizacao");
            PageUtil.atualizarComponente("formHistoricoPacote");
        } else {
            PageUtil.mensagemDeErro("Erro ao excluir atualiza��o");
        }
    }

    public void abrirDialogCadastroAtualizacao(Pacote pacote) {
        this.pacoteSelecionado = pacote;
        this.novaAtualizacao = new HistoricoModel();
        PageUtil.atualizarComponente("formCadAtualizacao");
        PageUtil.abrirDialog("dlgCadAtualizacao");
    }

    public void abrirDialogEditarAtualizacao(HistoricoModel atualizacao) {
        this.atualizacaoSelecionada = atualizacao;
        PageUtil.abrirDialog("dlgEditarAtualizacao");
        PageUtil.atualizarComponente("formEditarAtualizacao");
    }

    public void editarAtualizacao() {
        if (pacoteDAO.editarAtualizacao(atualizacaoSelecionada)) {
            PageUtil.mensagemDeSucesso("Atualiza��o editada com sucesso");
            PageUtil.fecharDialog("dlgEditarAtualizacao");
            PageUtil.atualizarComponente("formHistoricoPacote");
        } else {
            PageUtil.mensagemDeErro("Erro ao editar atualiza��o");
        }
    }

    public Boolean renderizaLocalizacao() {
        Boolean renderiza = false;

        if (novaAtualizacao.getStatus().getId() != null) {
            if (novaAtualizacao.getStatus().getId() == 1 || novaAtualizacao.getStatus().getId() == 2) {
                renderiza = true;
            }
        }

        return renderiza;
    }

    public Boolean renderizaLocalizacaoEditar() {
        Boolean renderiza = false;

        if (atualizacaoSelecionada.getStatus().getId() != null) {
            if (atualizacaoSelecionada.getStatus().getId() == 1 || atualizacaoSelecionada.getStatus().getId() == 2) {
                renderiza = true;
            }
        }

        return renderiza;
    }

    public void cadastrarNovaAtualizacao() {
        if (pacoteDAO.cadastrarAtualizacao(pacoteSelecionado, novaAtualizacao)) {
            PageUtil.mensagemDeSucesso("Atualizaca��o cadastrada com sucesso");
            PageUtil.fecharDialog("dlgCadAtualizacao");
        } else {
            PageUtil.mensagemDeErro("Erro ao cadastrar atualiza��o");
            PageUtil.fecharDialog("dlgCadAtualizacao");
        }
    }

    public void abrirDlgStatusPacote() {
        PageUtil.abrirDialog("dlgStatusPacote");
        PageUtil.atualizarComponente("formStatusPacote");
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

    public List<HistoricoModel> getListaHistoricoRastreio() {
        return listaHistoricoRastreio;
    }

    public Pacote getPacoteParaBuscar() {
        return pacoteParaBuscar;
    }

    public HistoricoModel getNovaAtualizacao() {
        return novaAtualizacao;
    }

    public HistoricoModel getAtualizacaoSelecionada() {
        return atualizacaoSelecionada;
    }
}
