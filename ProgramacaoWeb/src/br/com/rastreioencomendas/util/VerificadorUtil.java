package br.com.rastreioencomendas.util;

import java.util.List;

public class VerificadorUtil {

    public static Boolean isEstaNulo(Object objeto){
        return objeto.equals(null);
    }

    public static Boolean isNaoEstaNulo(Object objeto){
        Boolean isNaoEstaNuloOuVazio = Boolean.valueOf(objeto != null && !objeto.toString().isEmpty());
        return !(objeto instanceof List)?isNaoEstaNuloOuVazio.booleanValue():isNaoEstaNuloOuVazio.booleanValue() && !((List)objeto).isEmpty();
    }
}
