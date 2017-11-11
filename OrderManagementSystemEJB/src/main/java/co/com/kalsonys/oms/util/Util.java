package co.com.kalsonys.oms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String DateFormater(Date date) {

		// String dateFormated = Util.GetMouthFormated(date.getMonth()) + "/"+
		// Util.GetDayFornmated(date.getDay()) + "/" +
		// Util.GetYearFormated(date.getYear());

		return String.valueOf(new SimpleDateFormat("MM/dd/yy").format(date));

	}

	public static String GetDayFornmated(Integer day) {

		if (day < 10) {
			return "0" + String.valueOf(day);
		} else {
			return String.valueOf(day);
		}
	}

	public static String GetMouthFormated(Integer mouth) {

		Integer mouthReal = mouth + 1;

		return Util.GetDayFornmated(mouthReal);
	}

	public static String GetYearFormated(Integer year) {
		String strYear = String.valueOf(year);

		String yy = strYear.substring(strYear.length() - 2);

		return yy;
	}
}
