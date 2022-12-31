import java.util.Scanner; // Needed for the scanner class
import java.util.Random; // Needed for the random class
import java.io.*;      // Throws IOException

/**This program rolls dice a number of times and 
  stores the total values rolled and displays  a histogram 
  of asterisks "*" to the screen and to a file
 */

public class RollingDiceGame
{
  public static void main(String[] args)throws IOException
  {
  int numOfDice;// To hold the number of dice to be rolled
  int numOfTimes;// To hold the number of times the dice are to be rolled
  String playAgain;// Store user's answer
      
   // Create scanner class
   Scanner keyboard = new Scanner(System.in);
   
   //Create random object
   Random randomNumbers = new Random();
   
   // do while loop
   do
 {
   System.out.print("You may roll up to 100 dice. How many dice would you like to roll?\t");
   numOfDice = keyboard.nextInt();
   
   // Validate the input
   while ((numOfDice < 1) || (numOfDice > 100)) 
   {
   System.out.print("Invalid value. Please try again.Enter value between 1 and 100.\t");
   numOfDice = keyboard.nextInt();
   }

   System.out.print("You may roll the dice up to 1000 times. How many times would you like to roll the dice?\t");
   numOfTimes = keyboard.nextInt();
   
   // Validate the input
   while ((numOfTimes < 1) || (numOfTimes > 1000)) 
   {
   System.out.print("Invalid value. Please try again.Enter a value between 1 and 1000.\t");
   numOfTimes = keyboard.nextInt();
   }
   int minrange= numOfDice;
   int maxrange = numOfDice*6;
   // Create file for each round the game is played
   // File name  changes as the number of dice and times the dice are rolled for each round changes.
   String FileName = (numOfDice + " Dice rolled " + numOfTimes+ " times");
    
   // Create array  to keep count of the number of times each possible total is rolled
   int[] diceValueCount = new int[numOfDice * 6 +1];
   
   for(int i = 0; i < numOfTimes; i++)
   {
    int total = 0;   // To store the number of times a value is rolled
     for(int d = 0; d < numOfDice; d++)
      {
        // The random generated number between 1 and 6
        int roll = randomNumbers.nextInt(6) + 1;
        total+= roll;
      }
        // Increment the value at the subscript to process all values rolled
        diceValueCount[total]++;
    }
         // Print asterisks "*" to the screen
         for(int index = numOfDice; index < diceValueCount.length; index++)
          {
             System.out.print(index + " ");
              for(int j = 0; j < diceValueCount[index]; j++)
              System.out.print("*");
              System.out.println();// Prints a blank line
       }
       // Creating method to print histogram to file
       PrintWriter outputFile = new PrintWriter(FileName);
       printHistogram(FileName, diceValueCount, numOfDice);//Calling the  Method
       
       // Consume remaining new line
        keyboard.nextLine();
        
       // Ask user whether they  want to play again
        System.out.print("Would you like to play again? Yes/No ");
        playAgain = keyboard.nextLine();
   }      
        while (playAgain.equalsIgnoreCase("Yes"));

 }
   
   //Print Histogram Method
   public static void printHistogram(String FileName,int[] diceValueCount, int numOfDice)throws IOException
    {
     // Open File
     PrintWriter outputFile = new PrintWriter(FileName);
     // Write data to file
     for(int index = numOfDice; index < diceValueCount.length; index++)
      {
        outputFile.print(index + " ");
          for(int j = 0; j < diceValueCount[index]; j++)
           outputFile.print("*");
           outputFile.println();// Prints a blank line
       }
         // Close the file
          outputFile.close();
      }   
}