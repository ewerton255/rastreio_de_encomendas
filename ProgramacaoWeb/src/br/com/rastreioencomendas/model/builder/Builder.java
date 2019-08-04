package br.com.rastreioencomendas.model.builder;

import java.sql.ResultSet;

public interface Builder {

    Object mapear(ResultSet rs);

    Object build();
}
