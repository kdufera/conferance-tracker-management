import java.util.ArrayList;
import java.util.Stack;

public class EventScheduler {

	int morningStartTime = 9;
	int afternoonStartTime = 1;
	int moringDuration =  0;
	int afternoonDuration = 0;
	int currentHour = 0;
	int currMinutes = 0;

	Stack<String>  lightningEvents = new Stack<String>();
	ArrayList<String> eventListContainer  = new ArrayList<String>();

	/**
	 * Method used to process events
	 * @param data
	 */
	public void processEvent(String data) {
		try {
			String[] arrOfStr = data.split(" ");
			String eventName = this.processEventName(arrOfStr);
			int eventDuration = this.processDuration(arrOfStr[arrOfStr.length - 1]);
			this.scheduleEvent(eventName, eventDuration);
		} catch (Exception e) {
			System.out.print(e);			
		}
	}

	/**
	 * Method use to event duration 
	 * @param minutes
	 * @return
	 */
	public int processDuration(String minutes) {
		int duration = 0;
		if(minutes.length() <= 7) {
			try {
				char[] ch = minutes.substring(0, 3).toCharArray();
				String currentDuration = String.valueOf(ch[1]) + String.valueOf(ch[2]) ;
				duration = Integer.parseInt(currentDuration);
			} catch (NumberFormatException e) {
				System.out.print(e);			
			}
		} else {
			duration = 0;
		}
		return duration;

	}

	/**
	 * Method used to process event name
	 * @param data
	 * @return
	 */

	public String processEventName(String[] data) {
		String eventName = "";
		try {
			for(int i = 0; i < data.length - 1 ; i++) {
				eventName = eventName + " " +  String.valueOf(String.valueOf(data[i]));
			}
		} catch (Exception e) {
			System.out.print(e);			
		}
		return eventName;
	}

	/**
	 * Method used to schedule actual events
	 * @param eventName
	 * @param duration
	 */

	public void scheduleEvent(String eventName, Integer duration) {

		try {
			if(duration == 0) {
				this.lightningEvents.push(eventName);
			}
			if(eventListContainer.isEmpty() && duration!= 0) {
				eventListContainer .add("9:00AM" + " " + eventName + " " + 
						String.valueOf(duration) + "min");
				this.moringDuration = this.moringDuration +  duration;
				this.currMinutes = moringDuration % 60;
			} else {
				if(this.moringDuration + duration < 180 && duration!= 0) {
					int hours = this.moringDuration / 60; 
					this.processMorningEvents(duration, eventName, hours);
				} else if (this.moringDuration + duration > 180 && this.moringDuration + duration <= 240 && 
						duration!= -1) { 
					this.processMidDayEvents(duration);
				} else if (this.moringDuration >= 240 && this.afternoonDuration + duration <= 240 && duration!= 0) {
					this.processAfternoonEvents(duration, eventName, this.afternoonDuration / 60);
				}
				else if (this.afternoonDuration >= 180  && this.afternoonDuration + 
						duration > 240 && this.afternoonDuration <= 240) {
					this.processNonCriticalEvents(duration, eventName);
				} 
			}
		} catch (Exception e) {
			System.out.print(e);			
		}
	}

	/**
	 * Method used to process morning events
	 * @param duration
	 * @param eventName
	 * @param hours
	 */
	public void processMorningEvents(Integer duration , String eventName, int hours) {
		try {
			String tempTime = "AM";
			if(this.currMinutes == 0) {
				tempTime = "0" + tempTime;
			}
			this.currentHour = this.morningStartTime +  hours ;
			eventListContainer.add(String.valueOf( this.currentHour) + ":" +
					String.valueOf(this.currMinutes) + tempTime + " " + 
					eventName + " " + String.valueOf(duration) + "min");
			this.moringDuration = this.moringDuration +  duration;
			this.currMinutes = moringDuration % 60;
		} catch (Exception e) {
			System.out.print(e);			
		}
	}


	/**
	 * Method used to process and schedule mid day events
	 * @param duration
	 * @param eventName
	 */
	public void processMidDayEvents(Integer duration) {
		try {
			eventListContainer.add("12:00PM Lunch");
			this.currentHour = 1;
			String tempTime = "PM";
			int afternoonhours = this.afternoonDuration / 60; 
			this.currMinutes = this.afternoonDuration % 60;
			if(this.currMinutes == 0) {
				tempTime = "0" + tempTime;
			}
			this.moringDuration = moringDuration +  60;
			this.moringDuration = this.moringDuration +  duration;
		} catch (Exception e) {
			System.out.print(e);			
		}
	}

	/**
	 * Method used to process and schedule afternoon events 
	 * @param duration
	 * @param eventName
	 * @param hours
	 */
	public void processAfternoonEvents(Integer duration , String eventName, int hours) {
		try {
			String tempTime = "PM";
			if(this.currMinutes == 0) {
				tempTime = "0" + tempTime;
			}
			this.currentHour =  hours  + this.afternoonStartTime;
			eventListContainer .add(String.valueOf(this.currentHour) + ":" + 
					String.valueOf(this.currMinutes)+ tempTime + " " + eventName + " " +
					String.valueOf(duration) + "min");
			this.afternoonDuration = this.afternoonDuration +  duration;
			this.currMinutes = this.afternoonDuration % 60;
		} catch (Exception e) {
			System.out.print(e);			
		}
	}

	/**
	 * Method used to process lightning and networking events
	 * @param duration
	 * @param eventName
	 */
	public void processNonCriticalEvents(Integer duration , String eventName) {

		try {
			String tempTime = "PM";
			if(this.currMinutes == 0) {
				tempTime = "0" + tempTime;
			}
			this.currentHour =  this.afternoonDuration/60  + this.afternoonStartTime;
			if(!lightningEvents.isEmpty() && this.afternoonDuration >= 180 && this.afternoonDuration < 240) {
				this.afternoonDuration = afternoonDuration +  duration;
				eventListContainer.add(String.valueOf(this.currentHour) + ":" + 
						String.valueOf(this.currMinutes) + "PM" + lightningEvents.pop()
						+ " lightning");
				eventListContainer.add("5:00PM Networking Event");
			} else if(this.afternoonDuration >= 240 || this.afternoonDuration <= 241) { 
				this.afternoonDuration = afternoonDuration +  duration;
				eventListContainer.add(String.valueOf(this.currentHour) + ":" + 
						String.valueOf(this.currMinutes) + "PM" + " "+ " Networking Event");
			} 
		} catch (Exception e) {
			System.out.print(e);			
		}
	}

	/**
	 * Method used to display scheduled events 
	 */
	public void displaySchedule() {
		try {
			System.out.println(" ");
			for(int i=0; i < eventListContainer .size(); i++) {
				System.out.println(eventListContainer .get(i));
			}
			System.out.println(" ");
		} catch (Exception e) {
			System.out.print(e);			
		}
	}
}


