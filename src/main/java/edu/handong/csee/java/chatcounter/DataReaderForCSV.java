// package name
package edu.handong.csee.java.chatcounter;

// imports whole of java.io classes
import java.io.*;
// imports whole of java.util classes
import java.util.*;
// imports whole of java.util.regex class
import java.util.regex.*;

/**
 * DataReaderForCSV class reads the data from csv files.
 * <p>
 * This class compares different patterns appearing in csv files and using them, reads
 * values from the files and finally uses getter method to sync whole messages data along
 * with txt file reader.
 * @author Jun Seob Lee
 *
 */
// DataReaderForCSV class
public class DataReaderForCSV {
	private ArrayList<String> names = new ArrayList<String>(); // list of names
	private ArrayList<String> wholeMessages = new ArrayList<String>(); // list of whole message
	Pattern p1 = Pattern.compile("(\\d+)-(\\d+)-(\\d+)\\s(\\d+):(\\d+):(\\d+),\"(.+)\",\"(.+)\""); // regular expression pattern with the most common pattern
	Pattern p2 = Pattern.compile("(\\d+)-(\\d+)-(\\d+)\\s(\\d+):(\\d+):(\\d+),\"(.+)\",\"(.+)"); // regular expression pattern where ending double quote is missing
	Pattern p3 = Pattern.compile("(.+)\""); // regular expression pattern where there is only non-digit values ending with double quote
	private String msg,name; // String object to store the text/message and name
	private int year,month,day,hour,min,seconds; // integer object to store year,month,day,hour,min and seconds while reading the files
	private boolean readName = false; // initializes the readName as false

	/**
	 * This is the readFiles() method
	 * <p>
	 * This method reads csv files while checking for patterns in each line of the file and 
	 * getting the data that can be used to create a whole message. Also deals with exceptional 
	 * cases appearing while reading csv files. Finally, gets the whole valid list of names.
	 * @param file csv file that is used to read
	 */
	// readFiles method
	public void readFiles(File file) {
		names.clear(); // clear the names list when reading new/next file
		// try block
		try {
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8")); // creates instance of BufferedReader class and reads it in UTF-8 form
			String line; // String to read each line 
			// while the line exists to read in the file,
			while((line=inputStream.readLine())!=null) {
				Matcher m1 = p1.matcher(line); // matcher to check that particular pattern in the line
				Matcher m2 = p2.matcher(line); // second matcher to check that particular pattern in the line
				Matcher m3 = p3.matcher(line); // third matcher to check that particular pattern in the line
				// if line contains the double quote sign,
				if(line.contains("\"")) {
					// dealing with the exception case which looked like a bug while reading each line
					if(line.contains("I think I got the answer")||(line.contains("confused")&&name.equals("조정훈"))) {
						msg=""; // empty the message/text part
						continue; // skip to read the next line
					}
					// if the first pattern matches,
					if(m1.matches()) {
						year = Integer.parseInt(m1.group(1)); // store the first group in parenthesis in the first pattern as year
						month = Integer.parseInt(m1.group(2)); // store the second group in parenthesis in the first pattern as month
						day = Integer.parseInt(m1.group(3)); // store the third group in parenthesis in the first pattern as day
						hour = Integer.parseInt(m1.group(4)); // store the fourth group in parenthesis in the first pattern as hour
						min = Integer.parseInt(m1.group(5)); // store the fifth group in parenthesis in the first pattern as minute
						seconds = Integer.parseInt(m1.group(6)); // store the sixth group in parenthesis in the first pattern as seconds
						name = m1.group(7); // store the seventh group in parenthesis in the first pattern as name
						msg = m1.group(8).replace("\"\"","\""); // store the eighth group in parenthesis in the first pattern as message
						// if the whole message does not contain the message in that particular common form for both csv and txt file,
						if(!wholeMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg)) {
							wholeMessages.add(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg); // then add to the list of whole message in that particular form of values
							readName = true; // and since that line was never in wholeMessages, set the readName to true
						}

					}
					// else if the second pattern is found,
					else if(m2.find()) {
						year = Integer.parseInt(m2.group(1)); // store the first group in parenthesis in the second pattern as year
						month = Integer.parseInt(m2.group(2)); // store the second group in parenthesis in the second pattern as month
						day = Integer.parseInt(m2.group(3)); // store the third group in parenthesis in the second pattern as day
						hour = Integer.parseInt(m2.group(4)); // store the fourth group in parenthesis in the second pattern as hour
						min = Integer.parseInt(m2.group(5)); // store the fifth group in parenthesis in the second pattern as minute
						seconds = Integer.parseInt(m2.group(6)); // store sixth first group in parenthesis in the second pattern as seconds
						name = m2.group(7); // store the seventh group in parenthesis in the second pattern as name
						msg = line.split(",")[2].replace("\"\"", "\""); // store the eighth group in parenthesis in the second pattern as messages
					}
					// else if the third pattern is found,
					else if(m3.find()) {
						msg = msg + m3.group(1).replace("\"\"", "\""); // store the previous message value added with first group in parenthesis in the third pattern as message
						// if the whole message does not contain the message in that particular common form for both csv and txt file,
						if(!wholeMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg)) {
							wholeMessages.add(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg); // then add to the list of whole message in that particular form of values
							readName = true; // and since that line was never in wholeMessages, set the readName to true
						}
					}

					// else none of the pattern was found,
					else{
						// if the whole message does not contain the message in that particular common form for both csv and txt file,
						if(!wholeMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg)) {
							wholeMessages.add(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg); // then add to the list of whole message in that particular form of values
							readName = true; // and since that line was never in wholeMessages, set the readName to true
						} 
					}
				}

				// if the readName is true,
				if(readName) {
					names.add(name); // add that name value to the name list
					readName=false; // set the value of readName back to false
				}
			}
			inputStream.close(); // close the inputStream
		} 
		// catch block for i/o exception
		catch (IOException e) {
			e.printStackTrace(); // trace the taken stack back and print the error
		} 
	}

	/**
	 * This is the getNames() method
	 * <p>
	 * This is the getter method to get the list of names.
	 * @return names which is the list of names
	 */
	// getNames method
	public ArrayList<String> getNames(){
		return names; // return list of names
	}

	/**
	 * This is the getMessages() method
	 * <p>
	 * This is the getter method to get the list of Whole messages.
	 * @return wholeMessages that stores whole Messages without conflicts or repeated information
	 */
	// getMessages method
	public ArrayList<String> getMessages(){
		return wholeMessages; // return whole list of messages
	}

	/**
	 * This is the addMessages() method
	 * <p>
	 * This method checks the whole list of messages and only adds to the list if the messages never contains them.
	 * @param wholeMessages list of whole messages
	 */
	// addMessages method
	public void addMessages(ArrayList<String> wholeMessages) {
		// for each loop to check every message from parameter passed value
		for(String message : wholeMessages) {
			// if the wholeMessages of this class does not not contain that message,
			if(!this.wholeMessages.contains(message)) {
				this.wholeMessages.add(message); // add that message to the whole message so that it does not read again
			}
		}
	}
}
