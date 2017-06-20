/*
 * @author Job Mwesigwa
 * @author Deborah Attuah
 * @author Cynthia Gouanfo
 * This mobile code reads in a file with items bought by the user
 * And it gets all the procies to get the total amount spent and writes a file to the user 
 * with the organised price list called onlybumbers.txt
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Prices
{
  public static void main (String [] args)
  {
    
    try (BufferedReader myString = new BufferedReader(new InputStreamReader(new FileInputStream("Sales.dat"))))
    {
      String currentLine=null;
      int counter = 1;
      ArrayList myNumbers= new ArrayList();
      double temp= 0;
      double sum = 0;
      
      //creating the file to write to using the printWriter object
      PrintWriter outFile = new PrintWriter(new File("onlynumbers.txt"));
      outFile.write("These are the prices ");
      outFile.println();
      outFile.println();
      
      while ((currentLine = myString.readLine())!=null)
      {
        if (counter%2==0)
        {
          //converting text to values
          temp=Double.parseDouble(currentLine);
          
          //writing into the file
          outFile.write(currentLine.toCharArray());
          outFile.println();
          
          //getting the sum and storing the the prices
          sum=sum+temp;
          myNumbers.add(temp);//Storing data in an arrayList
        }
        counter=counter+1;
      }
      outFile.println();
      outFile.println();
      outFile.write("The sum is: ");
      outFile.write(Double.toString(sum));
      
      myString.close();
      outFile.close();
      
      System.out.println("These are the prices \n\n" + myNumbers.subList(0,(myNumbers.size()) ));
      System.out.println("\nSum: "+sum);
    }
    
    //catching any excemtions
    catch (Exception e){
      System.out.println("Error -Other Exception "+e.toString());
    }
    
    System.out.println("finished");
  }
}