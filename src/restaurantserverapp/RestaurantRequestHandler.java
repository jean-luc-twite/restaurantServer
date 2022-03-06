/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantserverapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public class RestaurantRequestHandler extends Thread {
    private PrintWriter out;
    private BufferedReader in;
    private  Socket socket;
       public RestaurantRequestHandler(Socket socket ) throws IOException{
      this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
       this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       //start thread--> this method calls therun method;
     start();
   }
 @Override
 public void run(){
     
        String messagFromClient,data,response;
        String url="jdbc:mysql://localhost:3306/restaurent_db";
        String userName="root";
        String passwd = "04061998Jlt@";
        ResultSet results;
        RestaurentDBManager rdbm;
        
            try{
            //create an instance of 
              rdbm = new RestaurentDBManager (url,userName,passwd);
              data = in.readLine();
                   while(true){
                // create tokens
                String[] tokens = data.split("#");
               
               if(Integer.parseInt(tokens[1]) == 1){
                  rdbm.addItems(tokens[0]);
                   //send message to confirme that the customers was added
                   response ="added";
                   out.println(response);
                   }else if(Integer.parseInt(tokens[1]) == 2){
                       //get items from database
                       results = rdbm.getItems(tokens[0]);
                       //generate response
                       response = getResult(results);
                       //send response back 
                        out.println(response);
                   }else if(Integer.parseInt(tokens[1])== 3){
                       //get result
                       results =rdbm.search(tokens[0]);
                       //generate response
                       response =getResult(results);
                       //send response
                       out.println(response);
                   }
                         
                   }
            }catch(Exception ex){
                ex.printStackTrace();
            }
              
 }


    private String getResult(ResultSet result) throws SQLException{
        String data ="";
        int CustomersNumber;
        String name;
        String surName;
        String city;
        String state;
        Date date;
        while(result.next()){
         CustomersNumber =result.getInt("customernumber");
         name =result.getString("firstname");
         surName =result.getString("lastname");
         city =result.getString("city");
         state = result.getString("state");
         date = result.getDate("date");
         //concatenate
         data = data + "#" + CustomersNumber + "," + name + "," +surName + "," + city + "," + state + ", "+ date;
        }
         return data ;
    }

}
