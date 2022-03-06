/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantserverapp;

import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;

/**
 *
 * @author HP
 */
public class NewClass extends Thread{
       private PrintWriter out;
    private BufferedReader in;
    private  Socket socket;
    
      public NewClass(Socket socket ) throws IOException{
      this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
       this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       //start thread--> this method calls therun method;
     start();
   }
      public void run(){
     
        String messagFromClient,data,response;
        String url="jdbc:mysql://localhost:3306/restaurent_db";
        String userName="root";
        String passwd = "04061998Jlt@";
        ResultSet result;
        RestaurentDBManager rdbm;
        try{
            rdbm = new RestaurentDBManager (url,userName,passwd);
            data = in.readLine();
                   while(true){
                // create tokens
               // String[] tokens = data.split("#");
               //
            //   if(Integer.parseInt(tokens[1]) == 1){
                  rdbm.addItems(data);
                
                   }
            
}catch(Exception e){
    e.printStackTrace();
}
      }
}