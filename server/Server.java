package server;

import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Server {

   public static void main(String[] args) {
      
      new Server().runServer();
      
   }
   
   public void runServer() {
      ServerSocket socket = createSocket();
      
      Thread conn = new Thread (new ConnectionListener(socket));
      conn.start();
      
      Thread input = new Thread(new InputListener());
      input.start();
      
      
      // Now wait on the server until the input closes.  Then we can close everything else
      try {
         input.join();
      } catch (InterruptedException e) {
      }
      
      try {
         socket.close();
      } catch (IOException e) {
      }
      
      
      try {
         conn.join();
      } catch (InterruptedException e) {
      }
      
   }
   
   private ServerSocket createSocket() {
      try {
         return (new ServerSocket(4444));
      } catch (IOException e) {
         System.out.println("Could not bind to port 4444");
         System.exit(1);
      }
      
      return null;
   }

}