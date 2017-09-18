import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class WarmUpProjectFinal {
    
	public static void main(String[] args) throws Exception{
    	
    	File inputFile = new File("C://Users//Burraque Khattak//Desktop//ENCtext.txt");		// create File object inputFile and indicate location of file	
        Scanner scan = new Scanner(inputFile); //												// create Scanner class object and scan the inputFile
    
        while(scan.hasNextLine()){															// while there are lines in the file with text
            
            String line = scan.nextLine(); 
            String firstLetter = line.substring(0, 1);										// extract first letter in string
            String firstDigit = line.substring(1,2);										// extract first digit used for columns
            String secondDigit = line.substring(2,3);										// extract second digit used for columns
            String restLine = line.substring(3,line.length());
            
            int digit1 = Integer.parseInt(firstDigit);										// parse string to give digit1 value of columns
            int digit2 = Integer.parseInt(secondDigit);										// do the same for digit 2

            if (firstLetter.equals("E")){													// check if first letter is an E and send to Encrypt function
            	encryptFunction(restLine, digit1);											// send to enc func twice
                encryptFunction(restLine, digit2);
            }
            else{																			// if not then send to decrypt
            	decryptFunction(restLine, digit1); 
            	decryptFunction(restLine, digit2);											// send to decrypt func twice
            }    
         }
    }
	
	public static void decryptFunction(String restLine, int columns) throws FileNotFoundException {
    	
		double line_length = restLine.length();												// length of the string is stored in var line_length	
        char charArray[][];																	// create char array 
        int rows = (int) Math.ceil(line_length / columns);									// rows are equal to number of letters in string divided by number of columns rounded up to next integer
                
        charArray = new char[rows][columns];												// initialize the array 
                     
        int counter_row = 0;																// created counter for rows and columns
        int counter_column = 0;
		
        for(int i=0; i<restLine.length(); i++){  											// loop through the characters in the string line

            if (counter_row <= rows-1){														// if the counter is less than the number of columns
                charArray[counter_row][counter_column] = restLine.charAt(i);				// current position in the array will be char[i]
                counter_row++;																// increment position of column
                
                if (counter_row > rows-1){													// if the counter is is greater than the number of columns 
                    counter_row = 0;														// set the column counter back to 0 
                    counter_column++;														// and increment the row             
                    }            
                }
            }
        
        for(int i=0; i<rows; i++){															// loop through rows 				
        	for(int j=0; j<columns; j++){ 													// loop thorough columns
                if (charArray[i][j] == 'X'){												// if current position in charArray is empty insert an X
                    // System.out.println("added");             
                	charArray[i][j] = 0;
                    }
            	}
        	}  
        
        System.out.println(Arrays.deepToString(charArray));									// print out the char array itself
        String newDecLine = DecToString(charArray);											// store the new encrypted line in newEncLine
        System.out.println(newDecLine);
        
        try{
            PrintWriter writer = new PrintWriter("C://Users//Burraque Khattak//Desktop//decrypt.txt", "UTF-8");
            writer.write(newDecLine);
            writer.close();
        } catch (IOException e) {
           // do something
        }
        
        }
	
	public static void encryptFunction(String restLine, int columns) throws Exception{								
        
		double line_length = restLine.length();												// length of the string is stored in var line_length	
        char charArray[][];																	// create char array 
        int rows = (int) Math.ceil(line_length / columns);									// rows are equal to number of letters in string divided by number of columns rounded up to next integer
        charArray = new char[rows][columns];												// initialize the array 
                    
        int counter_row = 0;																// created counter for rows and columns
        int counter_column = 0;

        for(int i=0; i<restLine.length(); i++){  											// loop through the characters in the string line

            if (counter_column <= columns-1){												// if the counter is less than the number of columns
                charArray[counter_row][counter_column] = restLine.charAt(i);				// current position in the array will be char[i]
                counter_column++;															// increment position of column
                // System.out.println(counter_column);

                if (counter_column > columns-1){											// if the counter is is greater than the number of columns 
                    counter_column = 0;														// set the column counter back to 0 
                    counter_row++;															// and increment the row
                // System.out.println(counter_row);             
                    }            
                }
            }
        
        for(int i=0; i<rows; i++){															// loop through rows 				
        	for(int j=0; j<columns; j++){ 													// loop thorough columns
                if (charArray[i][j] == 0){													// if current position in charArray is empty insert an X
                    // System.out.println("added");             
                	charArray[i][j] = 'X';
                    }
            	}
        	}  
        
       System.out.println(Arrays.deepToString(charArray));									// print out the char array itself
        String newEncLine = EncToString(charArray);											// store the new encrypted line in newEncLine
        System.out.println(newEncLine);														// print out newEncLine
        
        try{
            PrintWriter writer = new PrintWriter("C://Users//Burraque Khattak//Desktop//encrypt.txt", "UTF-8");
            writer.write(newEncLine);
            writer.close();
        } catch (IOException e) {
           // do something
        }
        }
 	
	public static String EncToString(char charArray[][]){									// function called by encrypt function used to return string value for whatever is in the char array
        
        char stringArray[][] = charArray;
        String encAgain = "";
        int rows = stringArray.length;
        int cols = stringArray[0].length;
     
        for(int i=0; i<cols; i++){
            for(int j=0; j<rows; j++){														// this loop will print down the columns 
            	encAgain = encAgain+stringArray[j][i];
            }
        }
        return encAgain;
    }
	
	public static String DecToString(char charArray[][]){									// function called by decrypt function used to return string value for whatever is in the char array
        
        char stringArray[][] = charArray;
        String decAgain = "";
        int rows = stringArray.length;
        int cols = stringArray[0].length;
        String decAgain1 = "";

        for(int i=0; i<rows; i++){															// this loop will go down the columns rows first
            for(int j=0; j<cols; j++){
            	decAgain = decAgain+stringArray[i][j];
                decAgain1 = decAgain.replaceAll("[^A-Za-z]+", "");							// this will remove white spaces in the decryption line
            }
        }
        return decAgain1;
    }
}
  