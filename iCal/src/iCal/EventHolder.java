package iCal;

import java.io.File;
import java.io.IOException;
import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

import java.util.ArrayList;

public class EventHolder {
	private ArrayList<VEvent> events;

	public EventHolder() {
		events = new ArrayList<VEvent>();
	}

	public void addEvent(VEvent event) {
		events.add(event);
	}

	public void exportEvents(String filename) {
		ICalendar ical = new ICalendar();
		for (int i = 0; i < events.size(); i++) {
			ical.addEvent(events.get(i));
		}
		File file = new File(filename + ".ics");
		try {
			Biweekly.write(ical).go(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
