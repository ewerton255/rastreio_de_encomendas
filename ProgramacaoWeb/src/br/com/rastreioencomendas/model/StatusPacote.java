package br.com.rastreioencomendas.model;

public class StatusPacote {

    private Integer id;
    private String descricao;

    public StatusPacote(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static class StatusPacoteBuilder {

        private Integer id;
        private String descricao;

        public StatusPacoteBuilder() {

        }

        public StatusPacoteBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public StatusPacoteBuilder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public StatusPacote build() {
            return new StatusPacote(id, descricao);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
