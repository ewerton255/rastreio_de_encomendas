package br.com.rastreioencomendas.model.builder;

import java.sql.*;

public class StatementBuilder {

    private int index;
    private PreparedStatement ps;

    public StatementBuilder(){

    }

    public StatementBuilder preparar(Connection con, String sql) throws SQLException {
        ps = con.prepareStatement(sql);
        return this;
    }

    public StatementBuilder comParametro(Object parametro) throws SQLException{
        this.index++;
        ps.setObject(index, parametro);
        return this;
    }

    public PreparedStatement construir(){
        return ps;
    }

    public Boolean executar() throws SQLException {
        resetarIndex();
        return ps.execute();
    }

    public ResultSet executarQuery() throws SQLException{
        resetarIndex();
        return ps.executeQuery();
    }

    public Integer executarUpdate() throws SQLException{
        resetarIndex();
        return ps.executeUpdate();
    }

    public void fecharStatament() throws SQLException{
        ps.close();
    }

    public void resetarIndex(){
        this.index = 0;
    }
}
