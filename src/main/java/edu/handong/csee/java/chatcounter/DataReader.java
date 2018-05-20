package edu.handong.csee.java.chatcounter;

import java.io.*;
import java.util.ArrayList;

public class DataReader {
	
	public static void main(String[] args) {
		
		DataReader myReader = new DataReader();
		
		ArrayList<String> messages = myReader.getData(args[0]);
		
		MessageFilter myFilter = new MessageFilter(messages);
		
		myFilter.convertToString();
	}
	
	public ArrayList<String> getData(String strDir){
		File myDir = getDirectory(strDir);
		
		File[] file = getListOfFilesFromDirectory(myDir);
		
		ArrayList<String> messages = readFiles(file);
		
		return messages;
	}
	
	private File getDirectory(String strDir){
		File myDirectory = new File(strDir);
		return myDirectory;
	}
	
	private File[] getListOfFilesFromDirectory(File dataDir){
		return dataDir.listFiles();
	}
	
	private ArrayList<String> readFiles(File[] files){
		ArrayList<String> message = new ArrayList<String>();
		
		DataReaderForCSV csvReader = new DataReaderForCSV();
		DataReaderForTXT txtReader = new DataReaderForTXT();
		
		
		for(File file : files) {
			if(file.toString().contains("csv")) {
				csvReader.readFiles(file);
				message.add(csvReader.getMessages());
			}
			else if (file.toString().contains("txt")){
				txtReader.readFiles(file);
				message.add(txtReader.getMessages());
			}
		}
		
		return message;
	}

}
