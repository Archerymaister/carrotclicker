package CarrotClicker.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	public String strTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String uhrzeit = sdf.format(new Date());
		return uhrzeit;
	}
}
