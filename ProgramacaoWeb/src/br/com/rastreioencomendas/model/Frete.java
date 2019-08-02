package br.com.rastreioencomendas.model;

public class Frete {

    private Integer id;
    private String tipo;
    private Integer qtdDias;

    public Frete(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }


}
