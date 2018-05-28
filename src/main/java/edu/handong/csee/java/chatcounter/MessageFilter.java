// package name
package edu.handong.csee.java.chatcounter;

// import whole of java.util classes
import java.util.*;

/**
 * MessageFilter class is a subclass of ChatMessageCounter.
 * <p>
 * This class like the name says, filters the list of names and reduces it and passes it back
 * to the user so that they can use it to calculate the message count. This class basically
 * adds and calculates data to the list of names and filters it and uses methods of its superclass
 * to get the HashMap value.
 * @author Jun Seob Lee
 *
 */
// MessageFilter class which is a subclass of ChatMessageCounter class
public class MessageFilter extends ChatMessageCounter{
	private ArrayList<String> listOfNames = new ArrayList<String>(); // whole raw list of names
	private ArrayList<String> names = new ArrayList<String>(); // list of proper names of no repeated value

	/**
	 * This is the default constructor of MessageFilter class
	 * <p>
	 * This constructor is empty.
	 */
	// default constructor
	public MessageFilter() {
		// empty
	}

	/**
	 * This constructor of MessageFilter class has parameters
	 * <p>
	 * This constructor calls and initializes its superclass constructor and sets the value of 
	 * list of names as gotten from the parameter.
	 * @param listOfNames whole list of names
	 */
	// constructor with parameters
	public MessageFilter(ArrayList<String> listOfNames) {
		super(); // calls the default constructor of its superclass
		this.listOfNames = listOfNames; // sets the value of the whole list of names to the parameter passed
	}

	/**
	 * This is the calculateNames() method
	 * <p>
	 * This method calls a private method getNames and reduces the value of the whole list of names into non-conflicting
	 * list of names. Also calls its superclass's setData method to set the data and calculateCounter() method to calculate the message counter.
	 */
	// calculateNames method
	public void calculateNames() {
		names = getNames(listOfNames); // calls getNames() private method and stores the return value to the proper list of names

		super.setData(listOfNames, names); // calls the setData() method from its superclass
		super.calculateCounter(); // calculate the message counter from its superclass
	}

	/**
	 * This is the getNames method()
	 * <p>
	 * This private method makes the new list of names reduced and deduced from the whole
	 * list of names.
	 * @param listOfNames whole list of names
	 * @return new list of names
	 */
	// getNames method
	private ArrayList<String> getNames(ArrayList<String> listOfNames) {
		ArrayList<String> names = new ArrayList<String>(); // new list of names
		// for each statement to see ever names on whole list of names
		for(String name : listOfNames) { 
			// if the new list of names does not contain that name then,
			if(!names.contains(name)) {
				names.add(name); // add that name to the new list of names
			}
		}
		return names; // return the newly created list of names
	}

	/**
	 * This is the getHashMap() method
	 * <p>
	 * This getter method is overridden from its superclass.
	 * @return the sorted HashMap that contains names and its message count 
	 */
	// overridden method
	@Override
	public HashMap<String,Integer> getHashMap(){
		return super.getHashMap(); // calls superclass getHashMap method and returns that HashMap value
	}

	/**
	 * This is the getNames() method
	 * <p>
	 * This getter method is overridden from its superclass.
	 * @return the sorted list of names
	 */
	// overridden method
	@Override
	public ArrayList<String> getNames(){
		return super.getNames(); // calls superclass getNames method and returns that list of names value
	}
}
