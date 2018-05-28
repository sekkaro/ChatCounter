// package name
package edu.handong.csee.java.chatcounter;

// imports whole java.io classes
import java.io.*;
// imports whole java.util classes
import java.util.*;

/**
 * DataReader class is the main class having the main method it reads the file 
 * and sends the data to the DataWriter class for writing files.
 * <p>
 * This class uses main method to make instances of all the classes and controls and looks
 * what parameters to pass. It uses getData() method to get all the directory names and list 
 * of file names and finally reads the file and gets the list of repeated names.
 * @author Jun Seob Lee
 *
 */
// DataReader class
public class DataReader {

	// main method
	public static void main(String[] args) {

		DataReader myReader = new DataReader(); // creates the instance of DataReader class

		ArrayList<String> listOfNames = myReader.getData(args[0]); // stores the list of names gotten from getData method

		MessageFilter myFilter = new MessageFilter(listOfNames); // creates instance of MessageFilter class while passing raw data of list of names

		myFilter.calculateNames(); // calls calculateNames method of MessageFilter class
		HashMap<String,Integer> messageCounter = myFilter.getHashMap(); // stores the sorted HashMap from getHashMap method
		listOfNames = myFilter.getNames(); // stores sorted list of names by calling getNames method

		DataWriter myWriter = new DataWriter(messageCounter,listOfNames); // creates instance of the DataWriter class while passing sorted HashMap and sorted list of names
		File outputFile = new File(args[1]); // creates an output File from the argument given by the user
		myWriter.writeFile(outputFile); // passes the output file object to the writeFile method
	}

	/**
	 * This is the getData() method
	 * <p>
	 * This method calls the private method to get directory name and list of files names.
	 * Finally, this method also reads from all the files and gets the whole list of names.
	 * @param strDir String value of the name of the directory
	 * @return listOfNames list of names
	 */
	// getData method
	public ArrayList<String> getData(String strDir){
		File myDir = getDirectory(strDir); // calls the private method to get the directory name

		File[] file = getListOfFilesFromDirectory(myDir); // gets list of files by passing the directory name

		ArrayList<String> listOfNames = readFiles(file); // stores the whole raw list of names by calling readFiles method 

		return listOfNames; // returns the list of names
	}

	/**
	 * This is the getDirectory() method
	 * <p>
	 * This is the private getter method to get the Directory as a file object
	 * @param strDir directory name stored as String object
	 * @return myDirectory directory name stored as File object
	 */
	// getDirectory method
	private File getDirectory(String strDir){
		File myDirectory = new File(strDir); // creates File using the directory name
		return myDirectory; // returns the directory file
	}

	/**
	 * This is the getListOfFilesFromDirectory() method
	 * <p>
	 * This is the private getter method to get the list of files from the directory
	 * @param dataDir directory name stored as String object
	 * @return list of Files by calling the method listfiles() from the directory file
	 */
	// getListOfFilesFromDirectory method
	private File[] getListOfFilesFromDirectory(File dataDir){
		return dataDir.listFiles(); // returns the list of files contained in that directory
	}

	/**
	 * This is the readFiles() method
	 * <p>
	 * This private method reads all csv and txt files sending them to each csv file reader class 
	 * and txt reader class. Finally, it gets the list of names that can be used to calculate 
	 * message count later.
	 * @param files Array of files that contains all the files from a directory
	 * @return listOfNames whole list of names
	 */
	// readFiles method
	private ArrayList<String> readFiles(File[] files){
		ArrayList<String> listOfNames = new ArrayList<String>(); // ArrayList listofNames to store list of names

		DataReaderForCSV csvReader = new DataReaderForCSV(); // creates an instance of DataReaderForCSV class
		DataReaderForTXT txtReader = new DataReaderForTXT(); // creates an instance of DataReaderForTXT class

		// for each loop to go through every file in the directory
		for(File file : files) {
			// if that file contains csv in its name
			if(file.toString().contains("csv")) {
				csvReader.addMessages(txtReader.getMessages()); // passes the whole message from the txt file reader and add it to the message on csv
				csvReader.readFiles(file); // reads the file passing that file 
				listOfNames.addAll(csvReader.getNames()); // adds all the list of names gotten from the csv reader
			}
			// else if that file contains txt
			else if (file.toString().contains("txt")){
				txtReader.addMessages(csvReader.getMessages()); // passes the whole message from csv file reader and add it to the message on txt
				txtReader.readFiles(file); // reads the file passing that file
				listOfNames.addAll(txtReader.getNames()); // adds all the list of names gotten from the txt reader
			}
		}
		return listOfNames; // returns the whole list of names gotten from all files
	}

}
