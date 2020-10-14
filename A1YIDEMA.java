//-----------------------------------------
// NAME		: ydie ma
// STUDENT NUMBER	: Gord Boyer
// COURSE		: COMP 2150
// INSTRUCTOR	: Gord Boyer
// ASSIGNMENT	: assignment #1
// QUESTION	: 
// 
// REMARKS: What is the purpose of this program?
// to stimulate a crypto currency system that contains:
// investors, cryptocurrencies and bockchain for each cryptocurrency.
//
//-----------------------------------------




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
// CLASS: A1YIDE MA
//
// Author: yide ma,7796951
//
// REMARKS: What is the purpose of this class? 
// a class for reading input file and execcute 
// command in each line.
//
//-----------------------------------------
public class A1YIDEMA {
     public static void main(String[] args) {
    	 
    	 inputFile("A1input.txt");
    	 
		 System.out.println("\nEnd of processing.");
     }
     
     
     
     
     
     
 	//------------------------------------------------------
     // inputFile
     //
     // PURPOSE:    convert command to behaviour of object
 	 //
     // PARAMETERS:
     //    string filename,
     //     
     //     
     // Returns: static 
     //------------------------------------------------------	
	  public static BufferedReader inputFile(String fileName){
			BufferedReader reader = null;
			
		    try{     
		      reader = new BufferedReader(new FileReader(fileName));
		     
			  
		      
			  String line = reader.readLine();

			  WholeSystem a =new WholeSystem();

				while (line != null) {
                    
					a.receive(line);
					line = reader.readLine();
				}
				reader.close();
		      
		      
		    }catch (IOException e){
		    	
		      System.out.println(e.getMessage());
		    }
	   return reader;
	  }
}
































    

	












