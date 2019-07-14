package br.com.rastreioencomendas.util;

public class StringUtil {

    public static String retornaPrimeiraPalavraDeUmTexto(String texto){
        String[] vetorPalavras = texto.split(" ");
        return vetorPalavras[0];
    }

}
