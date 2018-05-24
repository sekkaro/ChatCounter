package edu.handong.csee.java.chatcounter;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class DataReaderForTXT {
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<String> wholeMessages = new ArrayList<String>();
	Pattern p1 = Pattern.compile("\\[(.+)\\]\\s\\[(.+)\\s(\\d+):(\\d+)\\]\\s(.+)");
	Pattern p2 = Pattern.compile("-+\\s(\\d+).\\s(\\d).\\s(\\d+).\\s.+\\s-+");
	String msg,name;
	int year,month,day,hour,min;
	boolean readName = false;
	public void readFiles(File file) {
		names.clear();
			try {
				BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
				String line;
				while((line=inputStream.readLine())!=null) {
					Matcher m1 = p1.matcher(line);
					Matcher m2 = p2.matcher(line);
						if(m2.find()) {
							year = Integer.parseInt(m2.group(1));
							month = Integer.parseInt(m2.group(2));
							day = Integer.parseInt(m2.group(3));
						}
						 if(m1.find()) {
						 name = m1.group(1);
						 min = Integer.parseInt(m1.group(4));
						 msg = m1.group(5);
						 if(m1.group(2).equals("오후")) {
							 hour = Integer.parseInt(m1.group(3))+12;
						 }
						 if(msg.equals("사진")) {
							 msg = "Photo";
						 }
						 if(!wholeMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg)) {
								wholeMessages.add(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg);
								readName = true;
							}
						 if(readName) {
							 names.add(name);
							 readName=false;
						 	}
						 }
					 }
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public ArrayList<String> getNames(){
		return names;
	}
	
	public ArrayList<String> getMessages(){
		return wholeMessages;
	}
	
	public void addMessages(ArrayList<String> wholeMessages) {
		for(String message : wholeMessages) {
			if(!this.wholeMessages.contains(message)) {
				this.wholeMessages.add(message);
			}
		}
	}
}
