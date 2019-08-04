package br.com.rastreioencomendas.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formataDataEhHoraParaString(Date dataEhHora){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(dataEhHora);
    }

}
