/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.Socket;
import java.io.*;
/**
 * @author jobmwesigwa
 * This client gets a jar file from the server
 * THe file is run using commandline and sends the results back to the server
 * 
 */
public class Client {
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        byte[] buffer = new byte[5000];
        String line = "";
        
        //The first socket will be used receive the file and the second one will be used to
        //Send back the results
        Socket socket = new Socket("localhost", 9090);
        Socket newSocket = new Socket("localhost", 9090);
        InputStream is = socket.getInputStream();
        
        //File to which the jarfile content will be delievered
        File test = new File("recievedMobilecode.jar");
        test.createNewFile();
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(test));
        
        //Getting the jar file from the server and copying it to the clients jar file
        System.out.println("\n\nReceiving the jar file");
        int byteread = is.read(buffer, 0, buffer.length);
        int current = byteread;
        
        while ((byteread = is.read(buffer, 0, buffer.length)) != -1) 
          out.write(buffer, 0, byteread);
        
        out.write(buffer, 0, current);
        out.flush();
        
        //runnung the jar file using cmd
        System.out.println("\n\nRunnung the mobile codem to process file");
        String command = "java -jar recievedMobilecode.jar";
        try
        {   
            /**
             * Create the process and we run the command in commandline 
             * Then read the cmd outputusing the buffer reader and send the content back to the server
             * */
            Process p;
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            
            //Getting output from the cmd and appending it to the string
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder output = new StringBuilder();
            
            while ((line = reader.readLine())!= null) 
              output.append("\n"+line);
            
            //Sending results to the server
            PrintWriter printout = new PrintWriter(newSocket.getOutputStream());
            printout.println(output); 
            System.out.println("\n\nResults recieved and Sent to the Server"); 
            //closing resources
            printout.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
