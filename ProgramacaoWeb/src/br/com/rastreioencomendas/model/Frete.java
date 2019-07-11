package br.com.rastreioencomendas.model;

public class Frete {

    private Integer id;
    private String tipo;
    private Integer qtdDias;

    public Frete(Integer id, String tipo, Integer qtdDias) {
        this.id = id;
        this.tipo = tipo;
        this.qtdDias = qtdDias;
    }

    public static class FreteBuilder {
        private Integer id;
        private String tipo;
        private Integer qtdDias;

        public FreteBuilder() {

        }

        public FreteBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FreteBuilder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public FreteBuilder qtdDias(Integer qtdDias) {
            this.qtdDias = qtdDias;
            return this;
        }

        public Frete build() {
            return new Frete(id, tipo, qtdDias);
        }

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
