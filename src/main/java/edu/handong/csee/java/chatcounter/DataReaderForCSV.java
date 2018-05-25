package edu.handong.csee.java.chatcounter;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class DataReaderForCSV {
	ArrayList<String> names = new ArrayList<String>();
	ArrayList<String> wholeMessages = new ArrayList<String>();
	ArrayList<String> time = new ArrayList<String>();
	Pattern p1 = Pattern.compile("(\\d+)-(\\d+)-(\\d+)\\s(\\d+):(\\d+):(\\d+),\"(.+)\",\"(.+)\"");
	Pattern p2 = Pattern.compile("(\\d+)-(\\d+)-(\\d+)\\s(\\d+):(\\d+):(\\d+),\"(.+)\",\"(.+)");
	Pattern p3 = Pattern.compile("(.+)\"");
	Pattern p0 = Pattern.compile("(.+)");
	String msg,name;
	int year,month,day,hour,min,seconds;
	boolean readName = false;
	public void readFiles(File file) {
		names.clear();
		try {
			BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
			String line;
			while((line=inputStream.readLine())!=null) {
				Matcher m1 = p1.matcher(line);
				Matcher m2 = p2.matcher(line);
				Matcher m3 = p3.matcher(line);
				Matcher m0 = p0.matcher(line);
				if(line.contains("\"")) {
				if(m1.matches()) {
					year = Integer.parseInt(m1.group(1));
					month = Integer.parseInt(m1.group(2));
					day = Integer.parseInt(m1.group(3));
					hour = Integer.parseInt(m1.group(4));
					min = Integer.parseInt(m1.group(5));
					seconds = Integer.parseInt(m1.group(6));
					name = m1.group(7);
					msg = m1.group(8).replace("\"\"","\"");
					if(!wholeMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg)) {
						wholeMessages.add(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg);
						readName = true;
					}

					/*if(msg.equals("Photo")){
							if(!time.contains(year+" "+month+" "+day+" "+name+" "+hour+" "+min+" "+seconds)) {
								time.add(year+" "+month+" "+day+" "+name+" "+hour+" "+min+" "+seconds);
								readName = true;
							}
						}*/
				}
				else if(m2.find()) {
						year = Integer.parseInt(m2.group(1));
						month = Integer.parseInt(m2.group(2));
						day = Integer.parseInt(m2.group(3));
						hour = Integer.parseInt(m2.group(4));
						min = Integer.parseInt(m2.group(5));
						seconds = Integer.parseInt(m2.group(6));
						name = m2.group(7);
						msg = line.split(",")[2].replace("\"\"", "\"");
					}
					/*else if(m0.find()){
						System.out.println(msg);
						msg = msg + m0.group(1).replace("\"\"", "\"");
					}*/
				else if(m3.find()) {
						msg = msg + m3.group(1).replace("\"\"", "\"");
						if(!wholeMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg)) {
							wholeMessages.add(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg);
							readName = true;
						}
					}
				
				else{
					if(!wholeMessages.contains(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg)) {
						wholeMessages.add(year+"-"+month+"-"+day+" "+hour+":"+min+" "+name+" "+msg);
						readName = true;
					} 
				}
				}

				if(readName) {
					names.add(name);
					readName=false;
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

	public ArrayList<String> getTime(){
		return time;
	}

	public void addMessages(ArrayList<String> wholeMessages) {
		for(String message : wholeMessages) {
			if(!this.wholeMessages.contains(message)) {
				this.wholeMessages.add(message);
			}
		}
	}
}
