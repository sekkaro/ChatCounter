// package name
package edu.handong.csee.java.chatcounter;

// import every class from java.util
import java.util.*;

/**
 * ChatMessageCounter class does actual calculations of counting messages 
 * of each names.
 * <p>
 * This class uses methods: calculateCounter() to calculate the message count and 
 * getter values of HashMap and Names list to return the gotten value to the user. 
 * @author Jun Seob Lee
 *
 */
// ChatMessageCounter class
public class ChatMessageCounter {
	private ArrayList<String> listOfNames,names; // ArrayList to store names and whole list of names
	private HashMap<String,Integer> messageCounter = new HashMap<>(); // HashMap that stores names and the message count
	private HashMap<String,Integer> sortedMessageCounter = new HashMap<>(); // sorted HashMap that stores names and the message count
	private ArrayList<Integer> counter = new ArrayList<Integer>(); // ArrayList to store message counts
	private ArrayList<String> sortedNames= new ArrayList<String>(); // ArrayList to store list of names sorted with descending order of count

	/**
	 * This is the default constructor of ChatMessageCounter class
	 * <p>
	 * This constructor is empty and the actual working is done in the methods. This 
	 * constructor was made just to use super() from the subclasses.
	 */
	// default constructor
	public ChatMessageCounter(){
		//empty
	}

	/**
	 * This is the setData() method
	 * <p>
	 * This method acts like a constructor which sets the data required in this class to
	 * count the message count of each names.
	 * @param listOfNames whole list of names that contains raw data of all multiple repeated name values
	 * @param names ArrayList that stores non-repeated list of names
	 */
	// setData method that acts like a constructor with whole list of names and names passed as parameter
	public void setData(ArrayList<String> listOfNames,ArrayList<String> names) {
		this.listOfNames = listOfNames; // sets the ArrayList listOfNames as the parameter passed
		this.names = names; // sets the ArrayList names as the parameter passed
	}

	/**
	 * This is the calculateCounter() method
	 * <p>
	 * This method calculates message count for each person/names and then stores the value
	 * in a HashMap. Also sorts this HashMap in descending order of message count.
	 */
	// CalculateCounter method
	public void calculateCounter() {
		int count=0; // initializes the vale of count as 0
		// for each loop to get all names 
		for(String name:names) {
			// for each loop to repeat the whole list of names for each names to count
			for(String listOfName:listOfNames) {
				// if the name equals to the name in the list of names
				if(name.equals(listOfName)) {
					count++; // increment count
				}
			}
			counter.add(count); // add each count of names to the ArrayList counter
			messageCounter.put(name,count); // put the name as the key and message count as value to the messageCounter HashMap
			count=0; // reset count value to keep counting for next upcoming names
		}

		Collections.sort(counter); // sort the ArrayList counter 
		Collections.reverse(counter); // sort the ArrayList counter again but in descending order
		// for each loop to get every message count values
		for(int counter : counter) { 
			// for each loop to get every name in names ArrayList
			for(String name : names) {
				// if the sortedNames ArrayList never had this name and the actual message count equals to that name,
				if(!sortedNames.contains(name)&&(messageCounter.get(name)==counter)) {
					sortedNames.add(name); // add that name to the sorted names ArrayList
					sortedMessageCounter.put(name,messageCounter.get(name)); // put that name and the message count value to the sortedMessageCounter HashMap
				}
			}
		}
	}

	/**
	 * This is the getHashMap() method
	 * <p>
	 * This is the getter method of the sorted HashMap returning the final value in 
	 * calculateCounter() method.
	 * @return sortedMessageCounter sorted HashMap that stores names and message count in descending order
	 */
	// getHashMap method
	public HashMap<String,Integer> getHashMap(){
		return sortedMessageCounter; // returns the sorted HashMap
	}

	/**
	 * This is the getNames() method
	 * <p>
	 * This is the getter method for sorted list of names
	 * @return sortedNames sorted ArrayList of names 
	 */
	// getNames method
	public ArrayList<String> getNames(){
		return sortedNames; // returns the sortedNames ArrayList
	}

}
