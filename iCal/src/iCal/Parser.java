package iCal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Parser {
	private TimeTable timeTableRef;
	private String pageAddress;
	
	
	public Parser(String pageAddress , TimeTable timeTableRef){
		this.timeTableRef = timeTableRef;
		this.pageAddress = pageAddress;
	}
	
	public void parsePageSource () throws IOException{
		String actualDayOfWeekName = null;
		
		Document doc = Jsoup.parse(downloadPageSource(pageAddress) , "http://plan.uz.zgora.pl");
		
		Elements tableElements = doc.getElementsByTag("table");
		Element secondTableElement = tableElements.get(1);
		
		Elements trElements = secondTableElement.getElementsByTag("tr");
		
		trElements.remove(0);
		
		for (int i = 0 ; i < trElements.size() ; i++){
			Element trElement = trElements.get(i);
			
			if (isDayOfWeekName(trElement.text())){
				actualDayOfWeekName = trElement.text();
				continue;
			}
			
			Lesson lesson = new Lesson ();
			Elements tdElements = trElement.getElementsByTag("td");
			
			lesson.setSubGroup(tdElements.get(0).text());
			lesson.setStartTime(tdElements.get(1).text());
			lesson.setEndTime(tdElements.get(2).text());
			lesson.setSubject(tdElements.get(3).text());
			lesson.setClassType(tdElements.get(4).text());
			lesson.setTeacher(tdElements.get(5).text());
			lesson.setClassRoom(tdElements.get(6).text());
			lesson.getDueDatesList().addAll(getDueDates(tdElements.get(7) , actualDayOfWeekName));
			lesson.setInfoAboutDueDates(getInfoAboutDueDates(tdElements.get(7).text()));
			lesson.setDayOfWeek(actualDayOfWeekName);
			timeTableRef.addLessonToList(lesson);
			System.out.println(lesson);
		}
		
	}
	
	private String downloadPageSource (String pageAddress) throws IOException{
		URL pageAddr = new URL(pageAddress);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(pageAddr.openStream() , "UTF-8"));
		
		StringBuilder pageSrc = new StringBuilder();
		String tmp = null;
		
		while ((tmp = in.readLine()) != null)
			pageSrc.append(tmp);
		
		in.close();
		
		return pageSrc.toString();		
	}
	
	private ArrayList <String> getDayOfWeekNames (){
		ArrayList <String> result = new ArrayList<>();
		
		result.add("Poniedzia³ek");
		result.add("Wtorek");
		result.add("Œroda");
		result.add("Czwartek");
		result.add("Pi¹tek");
		result.add("Sobota");
		result.add("Niedziela");
		
		return result;
	}
	
	private boolean isDayOfWeekName (String dayOfWeekName){
		ArrayList <String> dayOfWeekNames = getDayOfWeekNames();
		
		if (dayOfWeekNames.contains(dayOfWeekName))
			return true;
		return false;
	}
	
	
	private String getInfoAboutDueDates(String string){
		final String SEPARATOR = "/";
		int indexOfSeparator;
		String result = null;
		
		indexOfSeparator = string.indexOf(SEPARATOR);
		
		result = string.subSequence(indexOfSeparator, string.length() - 1).toString();
		
		return result;
	}
	
	private ArrayList <String> getDueDates(Element tdElementContainsDueDates , String lessonDayOfWeekName) throws UnsupportedEncodingException, IOException{
		final String DATE_PATTERN = "[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]";
		ArrayList <String> result = null;
		Element element = tdElementContainsDueDates;
		
		Element aElement = element.getElementsByTag("a").first();
		String link = aElement.attr("abs:href");
		String text = aElement.text();
		
		result = getDatesFromString(text, DATE_PATTERN);
		
		if (result == null){
			result = getDatesFromSubpage (link , text , lessonDayOfWeekName);
		}
		
		return result;
	}
	
	//WERSJA NR 2 - NIE MODYFIKOWAÆ
	
	private ArrayList <String> getDatesFromSubpage (String subpageLink , String classType , String lessonDayOfWeekName) throws UnsupportedEncodingException, IOException{
		final String tableRegex = "\\s-\\s.*((?i)(<table.*</table>))";
		String pageContent = null;
		
		StringBuilder patternToMatchAsStringB = new StringBuilder();
	
		patternToMatchAsStringB.append(classType);
		patternToMatchAsStringB.append(tableRegex);
		
		try {
			pageContent = downloadPageSource(subpageLink);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Pattern patternToMatch = Pattern.compile(patternToMatchAsStringB.toString());
		
		Matcher matcher = patternToMatch.matcher(pageContent);
		
		String matchedText = null;
		
		try{
			matcher.find();
			matchedText = matcher.group();
		} catch (IllegalStateException ex){
			ex.printStackTrace();
		}
		
		Document doc = Jsoup.parse(matchedText);
		
		Element dueDatesTable = doc.getElementsByTag("table").first();
		
		Elements trElements = dueDatesTable.getElementsByTag("tr");
		
		ArrayList <String> dueDatesList = new ArrayList<>();
		for (int i = 0 ; i < trElements.size() ; i++){
			Elements tdElements = trElements.get(i).getElementsByTag("td");
			
			if (tdElements.size() == 0) {continue;}
				
				
			String dayOfWeekNameByTimetable = tdElements.get(2).text();
			String date = tdElements.get(1).text();
			
			if (dayOfWeekNameByTimetable.equals(lessonDayOfWeekName)){
				dueDatesList.add(date);
			}
		}	
		return dueDatesList;
	}
	
	
	private ArrayList<String> getDatesFromString (String string , String pattern){
		ArrayList <String> result = new ArrayList<>();
		
		Pattern patternObj = Pattern.compile(pattern);
		Matcher matcher = patternObj.matcher(string);
		
		while(true){
			if (matcher.find()){
				String match = matcher.group();
				result.add(match);
			} else {
				break;
			}
		}
		if (result.size() == 0)
			return null;
		else
			return result;
	}
	
	public String getPageAddress() {
		return pageAddress;
	}

	public void setPageAddress(String pageAddress) {
		this.pageAddress = pageAddress;
	}
}
