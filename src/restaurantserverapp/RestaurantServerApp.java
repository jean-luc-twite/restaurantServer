/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package restaurantserverapp;

import java.io.IOException;
import java.net.*;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class RestaurantServerApp {
    
   
    public static void main(String[] args) {
        // TODO code application logic here
       ServerSocket server =null;
        Socket socket = null;
       
    
        try{
           // create the server 
            server = new ServerSocket(9191);
            System.out.println("server :" + server);
         while(true){
           // create sockte
           socket = server.accept();
             System.out.println("connected...");
            
            new RestaurantRequestHandler(socket);
           }
       }catch(Exception ex){
           ex.printStackTrace();
        }finally{
           try{
               System.out.println("closing...");
               socket.close();
           }catch(IOException e){
               e.printStackTrace();
            }
        }
    }
    
}
    