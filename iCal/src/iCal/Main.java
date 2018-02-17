package iCal;

import java.io.IOException;

public class Main { 

	public static void main(String[] args) {		
		TimeTable timeTable = new TimeTable();
		Parser parser = new Parser("http://plan.uz.zgora.pl/grupy_plan.php?pId_Obiekt=16677" , timeTable);
		try {
			parser.parsePageSource();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		VLessons vLessons = new VLessons(timeTable.getLessonList());
		Exporter exporter = new Exporter(vLessons.getList());
		exporter.exportEvents("planx12");
	}
}