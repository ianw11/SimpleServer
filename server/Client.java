package server;

import java.io.*;
import java.net.*;

public class Client implements Runnable {
   private final Socket client;
   
   private final BufferedReader in;
   private final OutputStream out;
   
   public Client(Socket cli) throws IOException {
      client = cli;
      
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = client.getOutputStream();
   }
   
   public void run() {
      try {
         
         String request = readRequest();
         System.out.println(request);
         
         
         
         
         StringBuilder response = new StringBuilder();
         response.append("HTTP/1.1 200 OK");
         response.append("\n");
         response.append("Content-Type: text/html");
         response.append("\n");
         response.append("\n");
         response.append("<html>");
         response.append("\n");
         response.append("<body>");
         response.append("\n");
         response.append("<h1>Hello World</h1>");
         response.append("\n");
         response.append("</body>");
         response.append("\n");
         response.append("</html>");
         response.append("\n");
         
         
         out.write(response.toString().getBytes());
      
      } catch (IOException e) {
         System.err.println("Error in client socket");
      } finally {
         try {
            close();
         } catch (IOException e) {
            System.err.println("Error closing client connection");
         }
      }
   }
   
   private String readRequest() throws IOException {
      StringBuilder sb = new StringBuilder();
         
      String line = in.readLine();
      while (line != null && !line.equals("") ) {
         sb.append(line);
         sb.append("\n");
         line = in.readLine();
      }
      
      return sb.toString();
   }
   
   private void close() throws IOException {
      out.close();
      in.close();
   }
}