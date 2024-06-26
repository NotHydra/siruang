package providers;


import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Utility {
	public static final String[] availableTime = new String[] {
			"00:00",
			"00:30",
			"01:00",
			"01:30",
			"02:00",
			"02:30",
			"03:00",
			"03:30",
			"04:00",
			"04:30",
			"05:00",
			"05:30",
			"06:00",
			"06:30",
			"07:00",
			"07:30",
			"08:00",
			"08:30",
			"09:00",
			"09:30",
			"10:00",
			"10:30",
			"11:00",
			"11:30",
			"12:00",
			"12:30",
			"13:00",
			"13:30",
			"14:00",
			"14:30",
			"15:00",
			"15:30",
			"16:00",
			"16:30",
			"17:00",
			"17:30",
			"18:00",
			"18:30",
			"19:00",
			"19:30",
			"20:00",
			"20:30",
			"21:00",
			"21:30",
			"22:00",
			"22:30",
			"23:00",
			"23:30"
	};

	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
	private static DateTimeFormatter dateTimeToFullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static DateTimeFormatter dateTimeToTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	public static String formatDateTime(LocalDateTime dateTime) {
		return dateTime.format(dateTimeFormatter);
	}

	public static String formatDateTimeToFull(LocalDateTime dateTime) {
		return dateTime.format(dateTimeToFullFormatter);
	}

	public static String formatDateTimeToTime(LocalDateTime dateTime) {
		return dateTime.format(dateTimeToTimeFormatter);
	}

	public static String currentTimestamp() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}

	public static String capitalize(String text) {
		String[] words = text.split(" ");
		StringBuilder result = new StringBuilder();

		for (String word : words) {
			result.append(word.substring(0, 1).toUpperCase() + word.substring(1)).append(" ");
		}

		return result.toString().trim();
	}
}
