// package name
package edu.handong.csee.java.chatcounter;

// import whole org.apache.commons.cli classes
import org.apache.commons.cli.*;
// imports whole java.io classes
import java.io.*;
// imports whole java.util classes
import java.util.*;
// imports whole of java.util.concurrent class
import java.util.concurrent.*;

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
	private static String inputdir; // input directory file that contains the value
	private static String outputfile; // output directory file in String object

	/**
	 * This is the main method. 
	 * <p>
	 * The main method creates instances of messageFilter class and DataWriter class which 
	 * manages HashMap and list of names to read from the files and then to write it to the file.
	 * @param args argument string passed from the user
	 */
	// main method
	public static void main(String[] args) {

		Options options = createOptions(); // creates instance of Options class
		
		int numOfCoresInMyCPU = Runtime.getRuntime().availableProcessors(); // gets number of cores in the CPU using runtime methods
		ExecutorService executor = Executors.newFixedThreadPool(numOfCoresInMyCPU); // creates a thread pool using ExecutorService
		
		// if the what the parseOptions method has passed is true do the following actions,
		if(parseOptions(options, args)){
			DataReader myReader = new DataReader(); // creates the instance of DataReader class
			ArrayList<String> listOfNames = myReader.getData(inputdir); // stores the list of names gotten from getData method

			MessageFilter myFilter = new MessageFilter(listOfNames); // creates instance of MessageFilter class while passing raw data of list of names

			myFilter.calculateNames(); // calls calculateNames method of MessageFilter class
			HashMap<String,Integer> messageCounter = myFilter.getHashMap(); // stores the sorted HashMap from getHashMap method
			listOfNames = myFilter.getNames(); // stores sorted list of names by calling getNames method

			DataWriter myWriter = new DataWriter(messageCounter,listOfNames); // creates instance of the DataWriter class while passing sorted HashMap and sorted list of names
			File outputFile = new File(outputfile); // creates an output File from the argument given by the user
			myWriter.writeFile(outputFile); // passes the output file object to the writeFile method
		}
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
	 * @return list of Files by calling the method listFiles() from the directory file
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

	/**
	 * This is the createOptions() method.
	 * <p>
	 * This method creates the Options instance and adds in "i" and "o" options to 
	 * read and write the files.
	 * @return the Options instance
	 */
	// createOptions method
	public static Options createOptions() {
		Options options = new Options(); // creates instance of Options class 
		options.addOption(Option.builder("i").longOpt("inputdir") // calls addOption predefined method for -i
				.desc("Set a directory path that contains input files") // sets description values
				.hasArg() // checks if it has arguments
				.argName("Directory path") // defines argument name
				.required() // the option is required
				.build()); // builds the option

		options.addOption(Option.builder("o").longOpt("outputfile") // calls addOption predefined method for -o
				.desc("Set a directory path that contains output files") // sets description values
				.hasArg() // checks if it has arguments
				.argName("File path") // defines argument name
				.required() // the option is required
				.build()); // builds the option
		return options; // returns the all the options created
	}

	/**
	 * This is the parseOptions() method
	 * <p>
	 * This method checks for exceptions while getting values from the options created from the 
	 * command line arguments.
	 * @param options instance of the Options class
	 * @param args String array/argument of main method
	 * @return true or false depending on if getting values from options has no exceptions
	 */
	// parseOptions method
	public static boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser(); // creates instance of the CommandLineParser class initializing as default one

		// try block 
		try {

			CommandLine cmd = parser.parse(options, args); // creates CommandLine instance while passing Options instance and arg String from main method

			inputdir = cmd.getOptionValue("i"); // stores the option value gotten from cmd
			outputfile = cmd.getOptionValue("o"); // stores the option value gotten from cmd

		} 
		// catch block
		catch (Exception e) {
			printHelp(options); // passes Options instance to printHelp statement
			return false; // return false
		}

		return true; // returns true
	}

	/**
	 * This is the printHelp() method
	 * <p>
	 * This method prints out reference statements if creating the options or getting the values
	 * from the options fail.
	 * @param options instance of the Options class
	 */
	// printHelp method
	public static void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter(); // Creates instance of the HelpFormatter class
		String header = "CLI test program"; // sets the value of header
		String footer ="\nPlease report issues at https://github.com/sekkaro/ChatCounter"; // sets the value of footer
		formatter.printHelp("CLIExample", header, options, footer, true); // calls predefined printHelp method by passing header and footer strings as well as Options instance
	}
}
