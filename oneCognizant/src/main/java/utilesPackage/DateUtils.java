package utilesPackage;

import java.util.Date;

public class DateUtils {
	// Creating a TimeStamp
		public static String getTimeStamp() {
			Date date = new Date();
			return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
		}
}
