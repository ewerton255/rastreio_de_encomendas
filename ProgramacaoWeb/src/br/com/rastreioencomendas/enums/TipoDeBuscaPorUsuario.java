package br.com.rastreioencomendas.enums;

public enum TipoDeBuscaPorUsuario {

    POR_NOME("Nome"),
    POR_EMAIL("Email");

    private String sigla;

    TipoDeBuscaPorUsuario(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }
}
