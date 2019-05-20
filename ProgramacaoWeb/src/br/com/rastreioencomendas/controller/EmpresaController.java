package br.com.rastreioencomendas.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.rastreioencomendas.dao.EmpresaDAO;
import br.com.rastreioencomendas.model.Empresa;
import br.com.rastreioencomendas.util.PageUtil;
import br.com.rastreioencomendas.util.ViaCEP;
import br.com.rastreioencomendas.util.ViaCEPException;

@ViewScoped
@ManagedBean
public class EmpresaController {

	EmpresaDAO empresaDAO = new EmpresaDAO();
	Empresa empresaCadastrar = new Empresa();

	public EmpresaController() {

	}

	public void abrirDialogCadEmpresa() {
		empresaCadastrar = new Empresa();
		PageUtil.abrirDialog("dlgCadEmpresa");
		PageUtil.atualizarComponente("formCadEmpresa");
	}

	public void cadastrarEmpresa() {

		Boolean valido = true;

		if (empresaCadastrar.getEndereco().getNumero() <= 0) {
			PageUtil.mensagemDeErro("Námero endereáo inválido");
			valido = false;
		}
		if (empresaCadastrar.getEndereco().getBairro() == null) {
			PageUtil.mensagemDeErro("Informe o Bairro");
			valido = false;
		}
		if (empresaCadastrar.getEndereco().getEstado() == null) {
			PageUtil.mensagemDeErro("Informe o Estado");
			valido = false;
		}
		if (empresaCadastrar.getEndereco().getCidade() == null) {
			PageUtil.mensagemDeErro("Informe a Cidade");
			valido = false;
		}
		if (empresaCadastrar.getEndereco().getLogradouro() == null) {
			PageUtil.mensagemDeErro("Informe o logradouro");
			valido = false;
		}
		if (valido) {

			if (empresaDAO.cadastrarEmpresa(empresaCadastrar)) {
				PageUtil.mensagemDeSucesso("Empresa cadastrada com sucesso");
				PageUtil.fecharDialog("dlgCadEmpresa");
				PageUtil.atualizarComponente("formListaEmpresas");
			} else {
				PageUtil.mensagemDeErro("Erro ao cadastrar empresa");
			}
		}
	}
	
	public void buscarCepDestinatario() throws ViaCEPException {
		ViaCEP viaCep = new ViaCEP();
		viaCep.buscar(this.empresaCadastrar.getEndereco().getCep());
		this.empresaCadastrar.getEndereco().setEstado(viaCep.getUf());
		this.empresaCadastrar.getEndereco().setBairro(viaCep.getBairro());
		this.empresaCadastrar.getEndereco().setLogradouro(viaCep.getLogradouro());
		this.empresaCadastrar.getEndereco().setCidade(viaCep.getLocalidade());
	}

	public List<Empresa> retornaListaEmpresa() {
		return empresaDAO.retornaListaEmpresa();
	}

	public Empresa getEmpresaCadastrar() {
		return empresaCadastrar;
	}
}
