/*
 * A1 07-Sept-11
 * Ansha Yu | ay226 | 2392034
 */

import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;


public class SetDate {

	public static void main(String[] args) {
		if (args.length<3) {
			System.out.println("Please input all arguments");
			return;
		} else if (args.length>3) {
			System.out.println("Warning: more arguments input than necessary");
		}
		try {
			setDate(args[0], args[1], args[2]);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void setDate(String date, String time, String fileName) throws IllegalArgumentException {
		String sDate = date;
		String sTime = time;
		String sFileName = fileName;
		
		SimpleDateFormat sdfdate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		sdfdate.setLenient(false);
		Date finalDate;
		
		try {
			finalDate= sdfdate.parse(sDate+" "+sTime);
		} catch (Exception e) {
			throw new IllegalArgumentException("Incorrect time and/or date format");
		}
		
		try {
			if ((finalDate.getTime()>sdfdate.parse("12/31/30827 18:59:59").getTime())||(finalDate.getTime()<sdfdate.parse("12/31/1969 19:00:00").getTime())) {
			throw new IllegalArgumentException("Time out of range");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
		File nFile = new File(sFileName);
		if (!nFile.exists()) throw new IllegalArgumentException("File does not exist");
		nFile.setLastModified(finalDate.getTime());
	}

}
