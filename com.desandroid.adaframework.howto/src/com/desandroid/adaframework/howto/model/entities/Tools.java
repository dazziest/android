package com.desandroid.adaframework.howto.model.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
	public static String calculateTimeDiference(Date pInitialDate, Date pFinishDate) {
		Long initialTime = pInitialDate.getTime();
		Long finishTime =pFinishDate.getTime();
		
		long totalTime = finishTime - initialTime;
		
		DateFormat df = new SimpleDateFormat("HH:mm:ss.S");
		String returnedValue = df.format(new Date(totalTime));
		
		return returnedValue;
	}
}
