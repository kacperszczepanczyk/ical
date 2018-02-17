package iCal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VLesson {
	private ArrayList<Date> dates;
	private Lesson lesson;

	public VLesson(Lesson lesson) {
		this.lesson = lesson;
		dates = new ArrayList<Date>();
		transferDates();
	}

	private void transferDates() {
		for (int i = 0; i < lesson.getTerm().size(); i++) {
			dates.add(formatDate(lesson.getTerm().get(i) + ";" + lesson.getStartTime() + ":00", new SimpleDateFormat("dd-MM-yyyy;hh:mm:ss")));
		}
	}

	private Date formatDate(String date, DateFormat dateFormat) {
		try {
			return dateFormat.parse(date + ";" + lesson.getStartTime() + ":00");
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}

	private int getHour(String s) {
		return Integer.parseInt(s.substring(0, 2));

	}

	private int getMinute(String s) {
		return Integer.parseInt(s.substring(3, 5));
	}

	public int getDuration() {
		int hours = getHour(lesson.getEndTime()) - getHour(lesson.getStartTime());
		int minutes = getMinute(lesson.getEndTime()) - getMinute(lesson.getStartTime());
		return hours * 60 + minutes;
	}

	public String getDescription() {
		return lesson.getSubject() + "  | " + lesson.getClassType() + " |  " + lesson.getSubGroup() + "  "
				+ lesson.getTeacher();
	}

	public int getDatesAmount() {
		return dates.size();
	}

	public Date getDateByIndex(int index) {
		return dates.get(index);
	}
}
