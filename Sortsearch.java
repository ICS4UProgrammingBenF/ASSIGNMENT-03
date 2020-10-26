//import necessary libraries
import java.io.*;
import java.util.Scanner;

/**
* This program sorts arrays of numbers and searches for specific values.
*
* @author  Ben Falsetto
* @version 1.0
* @since   2020-10-25
*/
public class Sortsearch {
  
  /**
   * Main method.
  */
  public static void main(String[] args) {
    try {
      //declare variables
      String output = "";
      
      //create the scanner object
      String fileName = "input.txt";
      File myObj = new File("input.txt");
      Scanner scanner = new Scanner(myObj);
      
      //getting and organizing data
      while (scanner.hasNextLine() == true) {
        String line = scanner.nextLine();
        String[] rawStrArray = line.split("\\s+");
        
        //converting the strings to ints
        int[] rawArray = new int[rawStrArray.length];
        for (int i = 0; i < rawArray.length; i++) {
          int temp = Integer.parseInt(rawStrArray[i]);
          rawArray[i] = temp;
        }
        
        //calling bubble sort
        int[] sortedArray = bubblesort(rawArray);
        
        //adding the sorted and unsorted arrays to the output file
        //output += unsortedoutput(rawArray) + "\r\n";
        String addToOut = unsortedoutput(rawStrArray);
        output += addToOut + "\r\n";
        String addToOutput = sortedoutput(sortedArray);
        output += addToOutput + "\r\n";
        
        //gets the number to be found
        int numToFind = Integer.parseInt(scanner.nextLine());
        
        //calls binary search
        int indexFound = binarysearch(sortedArray, 0, sortedArray.length - 1, numToFind);
        
        //if index is -1, tell the user that the value doesn't exist
        if (indexFound == -1) {
          output += numToFind + " not found\r\n";
        } else  {
          output += numToFind + " found at index: " + indexFound + "\r\n";
        }
      }
      //System.out.println(output);
      try {
        //writing to the text file
        String outputFileName = "output.txt";
        File outputFile = new File(outputFileName);
        FileWriter fileWriter = new FileWriter(outputFile);
        
        //creating the buffered writer
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        
        //actually writing to the text file
        bufferedWriter.write(output);
        
        //close the writer
        bufferedWriter.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Organizes array into order.
  */
  public static int[] bubblesort(int[] rawArray)  {
    //looping through the array and switching if 
    //a preceeding value is > than a following value
    for (int i = 0; i < rawArray.length; i++)  {
      for (int c = 1; c < rawArray.length - i; c++) {
        
        //if the preceeding value is greater than the current, switch
        if (rawArray[c - 1] > rawArray[c])  {
          int tempVal = rawArray[c];
          rawArray[c] = rawArray[c - 1];
          rawArray[c - 1] = tempVal;
        }
      }
    }
    //return the organized array
    return rawArray;
  }
  
  /**
   * Determines the location of the number to be found.
  */
  public static int binarysearch(int[] sortedArray, int first, int last, int numToFind) {
    //checking that last is actually greater than first
    int itemToReturn = 0;
    if (last >= first)  {
      int mid = first + (last - first) / 2;
      //if the numToFind is at mid, return mid
      if (sortedArray[mid] == numToFind)  {
        itemToReturn = mid;
      } else if (sortedArray[mid] > numToFind)  { //re-call the function with updated paremeters
        return binarysearch(sortedArray, first, mid - 1, numToFind);
      } else if (sortedArray[mid] < numToFind)  { //re-call the function with updated parameters
        return binarysearch(sortedArray, mid + 1, last, numToFind);
      }
    } else  {
      itemToReturn = -1;
    }
    return itemToReturn;
  }
  
  /**
   * Writes the unsorted array to the output string.
  */
  public static String unsortedoutput(String[] rawStrArray)  {
    //declare variables
    String output = "Unsorted Array: ";
    
    //looping through the array, adding each val to output
    for (int i = 0; i < rawStrArray.length; i++) {
      output += rawStrArray[i] + " ";
    }
    //return output
    return output;
  }
  
  /**
   * Writes the sorted array to the output string.
  */
  public static String sortedoutput(int[] sortedArray)  {
    //declare variables
    String output = "Sorted Array: ";
    
    //looping through the array, adding each val to output
    for (int i = 0; i < sortedArray.length; i++) {
      output += sortedArray[i] + " ";
    }
    //return output
    return output;
  }
}