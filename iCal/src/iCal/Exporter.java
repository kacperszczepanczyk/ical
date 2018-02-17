package iCal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

public class Exporter {
	private ICalendar iCalendar;
	private ArrayList<VEvent> events;
	private ArrayList<VLesson> vlessons;

	public Exporter(ArrayList<VLesson> vlessons) {
		iCalendar = new ICalendar();
		events = new ArrayList<VEvent>();
		this.vlessons = vlessons;
		createEvents();
		fillICalendar();
	}

	private void createEvents() {
		for (int i = 0; i < vlessons.size(); i++) {
			for (int j = 0; j < vlessons.get(i).getDatesAmount(); j++) {
				VEvent vEvent = new VEventBuilder()
				.setDate(vlessons.get(i).getDateByIndex(j))
				.setDuration(vlessons.get(i).getDuration())
				.setSummary(vlessons.get(i).getDescription())
				.setDescription("opis")
				.build();
				events.add(vEvent);
			}
		}
	}

	private void fillICalendar(){
		for (int i = 0; i < events.size(); i++) {
			iCalendar.addEvent(events.get(i));
		}
	}
	
	public void exportEvents(String fileName) {
		File file = new File(fileName + ".ics");
		try {
			Biweekly.write(iCalendar).go(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
