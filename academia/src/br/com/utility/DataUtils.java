package br.com.utility;

import java.util.Calendar;
import java.util.Date;

public class DataUtils {
	
	
	public static Date toDateOnly(Date date){
		// ignora informação de horas
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date);

		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);

		return calendar.getTime();
	} 

}
