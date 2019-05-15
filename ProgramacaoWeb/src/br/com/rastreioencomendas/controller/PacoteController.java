package br.com.rastreioencomendas.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.swing.text.MaskFormatter;

import org.primefaces.mobile.component.page.Page;

import br.com.rastreioencomendas.dao.PacoteDAO;
import br.com.rastreioencomendas.model.HistoricoModel;
import br.com.rastreioencomendas.model.Pacote;
import br.com.rastreioencomendas.util.PageUtil;
import br.com.rastreioencomendas.util.ViaCEP;
import br.com.rastreioencomendas.util.ViaCEPException;

@ViewScoped
@ManagedBean
public class PacoteController {
	
	private PacoteDAO pacoteDAO = new PacoteDAO();
	private Pacote pacoteParaCadastrar;
	private String tipoCpfCnpj;
	private Pacote pacoteSelecionado;
	private Pacote pacoteParaBuscar;
	private List<HistoricoModel> listaHistoricoRastreio;
	
	public PacoteController() {
		this.pacoteParaCadastrar = new Pacote();
		this.listaHistoricoRastreio = new ArrayList<>();
		this.pacoteParaBuscar = new Pacote();
	}
	
	public String gerarCodigoRastreio() {
		String codigo = "";
		Random random = new Random();
		
		for(int i = 0; i < 13 ; i ++) {
			
			if(i == 0) {
				codigo+= String.valueOf((char)(65 + random.nextInt(90 - 65)));
			}else if(i == 1) {
				codigo+= String.valueOf((char)(65 + random.nextInt(90 - 65)));
			}else if(i == 2) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 3) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 4) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 5) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 6) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 7) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 8) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 9) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 10) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 11) {
				codigo+= String.valueOf((char)(65 + random.nextInt(90 - 65)));
			}else if(i == 12) {
				codigo+= String.valueOf((char)(65 + random.nextInt(90 - 65)));
			}
		}
		return codigo.toUpperCase();
	}
	
	public void buscarPacote() {
		this.listaHistoricoRastreio = pacoteDAO.retornalIstaParaRastreio(pacoteParaBuscar.getCodigoRastreio());
		if(listaHistoricoRastreio.size() == 0) {
			PageUtil.mensagemDeErro("Nenhum resultado encontrado");
		}
	}
	
	public void retornaCodigoDeRastreio() {
		String codigo = gerarCodigoRastreio();
		while(pacoteDAO.verificaSeCodigoJaExiste(codigo)) {
			codigo = gerarCodigoRastreio();
		}
		this.pacoteParaCadastrar.setCodigoRastreio(codigo);
	}
	
	public void abrirDialogCadastroPacote() {
		this.pacoteParaCadastrar = new Pacote();
		this.tipoCpfCnpj = null;
		PageUtil.abrirDialog("dlgCadPacote");
		PageUtil.atualizarComponente("formCadPacote");
	}
	
	public void cadastrarPacote() {
		if(pacoteDAO.cadastrarPacote(this.pacoteParaCadastrar)) {
			PageUtil.mensagemDeSucesso("Pacote cadastrado com sucesso!");
			PageUtil.atualizarComponente("formListaPacotes");
			PageUtil.fecharDialog("dlgCadPacote");
		}else {
			PageUtil.mensagemDeErro("Erro ao cadastrar pacote!");
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
	
	public List<HistoricoModel> retornaListaDeHistorico(){
		List<HistoricoModel> lista = new ArrayList<>();
		if(this.pacoteSelecionado != null) {
			lista = pacoteDAO.retornaListaDeHistorico(this.pacoteSelecionado.getId());
		}
		return lista;
	}
	
	public List<Pacote> retornaListaDePacotes(){
		return pacoteDAO.retornaListaDePacotes();
	}

	public Pacote getPacoteParaCadastrar() {
		return pacoteParaCadastrar;
	}

	public String getTipoCpfCnpj() {
		return tipoCpfCnpj;
	}

	public void setTipoCpfCnpj(String tipoCpfCnpj) {
		this.tipoCpfCnpj = tipoCpfCnpj;
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
}
