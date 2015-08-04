package server;

import java.util.*;

public class InputListener implements Runnable {
   
   public void run() {
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("> ");
      
      String line = scanner.nextLine();
      while (!line.equals("quit")) {
         
         System.out.print("> ");
         line = scanner.nextLine();
      }
      
      scanner.close();
   }
}