// package name
package edu.handong.csee.java.chatcounter;

// import whole of java.util classes
import java.util.*;
// import whole of java.io classes
import java.io.*;

/**
 * DataWriter class
 * <p>
 * This class writes the data gotten from the main method of DataReader class. It uses the 
 * values of message count values and list of names and writes it to a csv file.
 * @author Jun Seob Lee
 *
 */
// DataWriter class
public class DataWriter {
	private HashMap<String,Integer> messageCounter = new HashMap<>(); // HashMap that stores name and message count for each of them
	private ArrayList<String> names = new ArrayList<>(); // ArrayList names that stores list of names 

	/**
	 * This is the default constructor of the DataWriter class.
	 * <p>
	 * It is empty.
	 */
	// default constructor
	public DataWriter(){
		// empty
	}

	/**
	 * This is the constructor of DataWriter class with parameters
	 * @param messageCounter HashMap containing name as key and its particular message count
	 * @param names list of names
	 */
	// constructor with parameters
	public DataWriter(HashMap<String,Integer> messageCounter,ArrayList<String> names) {
		this.messageCounter = messageCounter; // sets the messageCounter as the parameter passed
		this.names = names; // sets the list of names as the parameter passed
	}

	/**
	 * This is the writeFile() method.
	 * <p>
	 * This method writes the data of names and its accordingly matched message count to 
	 * the csv files. 
	 * @param file output csv file
	 */
	// writeFile method
	public void writeFile(File file) {
		// try block
		try {
			BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8")); // creates instance of BufferedWriter and writes it in UTF-8
			outputStream.write("kakao_id,count\n"); // calls write method of BufferedWriter to write to the particular file
			// for each loop to go through every name value in name list
			for(String name:names) {
				outputStream.write(name+","+messageCounter.get(name)+"\n"); // writes a particular name along with the message count it contains on the HashMap
			}
			outputStream.close(); // closes the outputStream
		} 
		// catch block for i/o exception
		catch(IOException e){
			e.printStackTrace(); // trace the taken stack back and print the error
		}
	}
}
