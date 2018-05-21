package edu.handong.csee.java.chatcounter;

import java.io.*;
import java.util.*;

public class DataReaderForCSV {
	ArrayList<String> messages = new ArrayList<String>();
	ArrayList<String> wholeMessages = new ArrayList<String>();
	boolean readName = false;
	public void readFiles(File file) {
			messages.clear();
			try {
				BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
				String line;
				while((line=inputStream.readLine())!=null) {
					if(line.contains("2018")) {
						if(!wholeMessages.contains(line)) {
							wholeMessages.add(line);
							readName = true;
						}
						if(readName) {
							messages.add(line.split(",")[1].replace("\"", "").replace("[","").replace("]",""));
						}
					}
					readName=false;
				}
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	
	public ArrayList<String> getMessages(){
		return messages;
	}
}
