package iCal;

import java.util.Date;

import biweekly.component.VEvent;
import biweekly.util.Duration;

public class VEventBuilder {
	private VEvent vEvent;
	
	
	public VEventBuilder(){
		vEvent = new VEvent();
	}
	
	public VEventBuilder setSummary(String summary){
		vEvent.setSummary(summary);
		return this;
	}
	
	public VEventBuilder setDescription(String description){
		vEvent.setDescription(description);
		return this;
	}
	
	public VEventBuilder setDate(Date date){
		vEvent.setDateStart(date);
		return this;
	}
	
	public VEventBuilder setDuration(int minutes){
		vEvent.setDuration(new Duration.Builder().minutes(minutes).build());
		return this;
	}
	
	public VEvent build(){
		return vEvent;
	}
}
