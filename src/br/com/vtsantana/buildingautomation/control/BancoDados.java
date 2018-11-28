/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vtsantana.buildingautomation.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Jackson Nascimento
 */
public class BancoDados {
     private Connection connection;
     ControlEvento evento = new ControlEvento();
    
    /**
     *
     * @param numeroSala
     * @param inicio
     * @param fim
     * @throws SQLException
     */
    public void adicionarInfo(String numeroSala, Date inicio, Date fim) throws SQLException{
     this.connection = ControlConexBancoDados.getConexao();
     String sql = "INSERT INTO sala(numeroSala, horaLigar, horaDesligar) VALUES(?, ?, ?)"; 
        try {  
           PreparedStatement stmt = connection.prepareStatement(sql);
          stmt.setString(1, numeroSala);  
          stmt.setDate(2, (java.sql.Date) inicio); //AAAA-MM-DD hh:mm:ss
          stmt.setDate (3, (java.sql.Date) fim); //AAAA-MM-DD hh:mm:ss
          
         stmt.execute();  
                stmt.close();  
            } catch (SQLException u) {  
                throw new RuntimeException(u);  
        }       
 }   
       
  public ControlEvento buscarInfo(String numeroSala) throws SQLException{
      this.connection = ControlConexBancoDados.getConexao();
  String sql = "SELECT * FROM sala WHERE numeroSala = ?";
      PreparedStatement pstm = connection.prepareStatement(sql);
      pstm.setString(1, numeroSala);
      ResultSet rs = pstm.executeQuery();
      evento = null;
      
      if(rs.next()){
      
      evento.setNumeroSala(rs.getString("numeroSala"));
      evento.setInicio(rs.getDate("horaLigar"));
      evento.setFim(rs.getDate("horaDesligar"));
      
      }
      rs.close();
      pstm.close();
  
  
  
   return evento;
  }
}

 

