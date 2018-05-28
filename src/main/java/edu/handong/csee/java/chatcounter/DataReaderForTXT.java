// package name
package edu.handong.csee.java.chatcounter;

// imports whole of java.io classes
import java.io.*;
// imports whole of java.util classes
import java.util.*;
// imports whole of java.util.regex classes
import java.util.regex.*;

/**
 * DataReaderForTXT class reads the data from txt files.
 * <p>
 * This class compares different patterns appearing in txt files and using them, reads
 * values from the files and finally uses getter method to sync whole messages data along
 * with csv file reader.
 * @author Jun Seob Lee
 *
 */
// DataReaderForTXT class
public class DataReaderForTXT {
	private ArrayList<String> names = new ArrayList<String>(); // list of names
	private ArrayList<String> wholeMessages = new ArrayList<String>(); // list of whole messages
	Pattern p1 = Pattern.compile("\\[(.+)\\]\\s\\[(.+)\\s(\\d+):(\\d+)\\]\\s(.+)"); // regular expression with the most common pattern
	Pattern p2 = Pattern.compile("-+\\s(\\d+).\\s(\\d).\\s(\\d+).\\s.+\\s-+"); // regular expression dealing with the date
	private String msg,name; // message and name as String objects
	int year,month,day,hour,min; // year,month,day,hour and min as integer objects
	boolean readName = false; // initializes the value of readName as false

	/**
	 * This is the readFiles() method
	 * <p>
	 * This method reads txt files while checking for patterns in each line of the file and 
	 * getting the data that can be used to create a whole message. Also deals with exceptional 
	 * cases appearing while reading txt files. Finally, gets the whole valid list of names.
	 * @param file txt file that is used to read
	 */
	// readFiles method
	public void readFiles(File file) {
		names.clear(); // clear the list of names when reading a new file
		// try block
		try {
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8")); // creates instance of BufferedReader class and reads it in UTF-8 form
			String line; // String to read each line 
			// while the line exists to read in the file,
			while((line=inputStream.readLine())!=null) {
				Matcher m1 = p1.matcher(line); // matcher to check that particular pattern in the line
				Matcher m2 = p2.matcher(line); // second matcher to check that particular pattern in the line
				// if the second pattern is found,
				if(m2.find()) {
					year = Integer.parseInt(m2.group(1)); // store the first group in parenthesis in the second pattern as year
					month = Integer.parseInt(m2.group(2)); // store the second group in parenthesis in the second pattern as month
					day = Integer.parseInt(m2.group(3)); // store the third group in parenthesis in the second pattern as day
				}

				// if the first pattern is found,
				if(m1.find()) {
					name = m1.group(1); // store the first group in parenthesis in the first pattern as name
					min = Integer.parseInt(m1.group(4)); // store the fourth group in parenthesis in the first pattern as minute
					msg = m1.group(5); // store the fifth group in parenthesis in the first pattern as message
					hour = Integer.parseInt(m1.group(3)); // store the third group in parenthesis in the first pattern as hour

					// if the second group in parenthesis in the first pattern contains "오후" and the hour value is not 12,
					if(m1.group(2).contains("오후")&&hour!=12) {
						hour = Integer.parseInt(m1.group(3))+12; // sets the value of hour as the third group in parenthesis in the first pattern as hour and add 12
					}
					// if the second group in parenthesis in the first pattern contain "오전" and the hour value is 12,
					if(m1.group(2).contains("오전")&&hour==12) {
						hour = 0; // sets value of hour as 0
					}

					// if the msg equals to "사진",
					if(msg.equals("사진")) {
						msg = "Photo"; // change the message to english "photo"
					}
					// if the whole message does not contain the message in that particular common form for both csv and txt file,
					if(!wholeMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg)) {
						wholeMessages.add(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg); // then add to the list of whole message in that particular form of values
						readName = true; // and since that line was never in wholeMessages, set the readName to true
					}
					// if the readName is true,
					if(readName) {
						names.add(name); // add that name value to the name list
						readName=false; // set the value of readName back to false
					}
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
