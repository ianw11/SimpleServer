package server;

import java.net.*;
import java.io.*;

public class ConnectionListener implements Runnable {

   ServerSocket socket = null;
      
   public ConnectionListener(ServerSocket sock) {
      socket = sock;
   }
   
   public void run() {
      
      while (!socket.isClosed()) {
      
         Socket newClient = null;
         try {
            newClient = socket.accept();
            new Thread(new Client(newClient)).start();
         } catch (IOException e) {
            if (!socket.isClosed())
               System.out.println("Accept failed");
         }
      }
      
   }
}