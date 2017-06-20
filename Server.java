/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

/**
 * @author jobmwesigwa
 * The server Listens for a client to reach it
 * Once reached, a jar file that orgainies a shoping list of the client is sent to the 
 * client than then waits to recieve the results.
 * once results are produced, the server displays them.
 */
public class Server {
    
    public static void main(String[] args) throws IOException{
        /**
        * The mobile code, a jar file that gets results from a shopping list 
        * at the clients end
        * */
        File myFile = new File("myJarfile/Mobilecode.jar");
      
        //byte array witht he size of the jar file
        byte[] buffer = new byte[(int) myFile.length()];
    
        /*
         * creating the server socket and 
         * listening for any connections 
         * */
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("\n\nWaiting for connection ...");
        Socket socket = serverSocket.accept();
        System.out.println("\n\nThis connection has been recieved  : " + socket);
        
        //Reading the file in and sending it to the client
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(myFile));
        in.read(buffer,0,buffer.length);
        OutputStream out = socket.getOutputStream();
        System.out.println("\n\nSending files...");
        out.write(buffer,0, buffer.length);
        out.close();
        
        //Receiving the feedback from the client with the results of the sum of prices and the prices listed
        System.out.println("\n\nMobile code sent, waiting for results");
        socket = serverSocket.accept();
        
        System.out.println("\n\nBelow is the feedback from your shopping list");
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = "";
        StringBuilder output = new StringBuilder();
        while ((line = br.readLine())!= null)
           output.append("\n"+line);
        
        //Results 
        System.out.println(output); 
   
    }

}
