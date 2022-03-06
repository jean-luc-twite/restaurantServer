/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantserverapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author HP
 */
public class RestaurentDBManager {
     private  Connection connection;

    public RestaurentDBManager(String url,String userName,String password)throws SQLException {
        
        this.connection = DriverManager.getConnection(url,userName,password);
    }
   public ResultSet getItems(String query)throws SQLException{
       ResultSet results;
       PreparedStatement statement;
       
       statement =connection.prepareStatement(query);
       results = statement.executeQuery();
       
       return results;
   } 
   public void addItems(String quiery)throws SQLException{
       ResultSet results;
       long millis = System.currentTimeMillis();
      java.sql.Date date = new java.sql.Date(millis);
       PreparedStatement statement;
       String sql = "INSERT INTO customers_tbl VALUES(?,?,?,?,?,?,?)";
      String[] tokens=quiery.split(",");
       
       statement =connection.prepareStatement(sql);
       statement.setInt(1, Integer.parseInt(tokens[0]));
       statement.setString(2, tokens[1]);
       statement.setString(3, tokens[2]);
       statement.setString(4, tokens[3]);
       statement.setString(5, tokens[4]);
       statement.setInt(6,Integer.parseInt(tokens[5]));
       statement.setString(7, String.valueOf(date));
       
      //results = statement.executeQuery();
      statement.executeUpdate();
      
      
   }
   public ResultSet search(String data) throws SQLException{
       ResultSet results ;
       PreparedStatement statement;
       String sql ="select* from customers_tbl  where customernumber =?";
       
       statement =connection.prepareStatement(sql);
       statement.setInt(1, Integer.parseInt(data));
       
      results = statement.executeQuery();
      return results;
   }
  

}
