package edu.handong.csee.java.chatcounter;

import java.io.*;
import java.util.ArrayList;

public class DataReader {
	
	public static void main(String[] args) {
		
		DataReader myReader = new DataReader();
		
		myReader.getData(null);

	}
	
	public ArrayList<String> getData(String strDir){
		File myDir = getDirectory(null);
		
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
		return message;
	}

}
