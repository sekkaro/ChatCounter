package edu.handong.csee.java.chatcounter;

import java.io.*;
import java.util.*;

public class DataReader {
	
	public static void main(String[] args) {
		
		DataReader myReader = new DataReader();
		
		ArrayList<String> listOfNames = myReader.getData(args[0]);
		
		MessageFilter myFilter = new MessageFilter(listOfNames);
		
		myFilter.calculateNames();
		HashMap<String,Integer> messageCounter = myFilter.getHashMap();
		listOfNames = myFilter.getNames();
		
		DataWriter myWriter = new DataWriter(messageCounter,listOfNames);
		File outputFile = new File(args[1]);
		myWriter.writeFile(outputFile);
	}
	
	public ArrayList<String> getData(String strDir){
		File myDir = getDirectory(strDir);
		
		File[] file = getListOfFilesFromDirectory(myDir);
		
		ArrayList<String> listOfNames = readFiles(file);
		
		return listOfNames;
	}
	
	private File getDirectory(String strDir){
		File myDirectory = new File(strDir);
		return myDirectory;
	}
	
	private File[] getListOfFilesFromDirectory(File dataDir){
		return dataDir.listFiles();
	}
	
	private ArrayList<String> readFiles(File[] files){
		ArrayList<String> listOfNames = new ArrayList<String>();
		
		DataReaderForCSV csvReader = new DataReaderForCSV();
		DataReaderForTXT txtReader = new DataReaderForTXT();
		
		
		for(File file : files) {
			if(file.toString().contains("csv")) {
				csvReader.addMessages(txtReader.getMessages());
				csvReader.readFiles(file);
				listOfNames.addAll(csvReader.getNames());
			}
			else if (file.toString().contains("txt")){
				txtReader.addMessages(csvReader.getMessages());
				txtReader.readFiles(file);
				listOfNames.addAll(txtReader.getNames());
			}
		}
		return listOfNames;
	}

}
