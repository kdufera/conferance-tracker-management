package test;
import static org.junit.Assert.*;

import org.junit.Test;

import schedule.EventScheduler;

public class EventSchedulerTest {

	@Test
	public void testProcessEvent() {
		String testInputOne = "Proper Unit Tests for Anyone ​30min";
		EventScheduler currentSchedule = new EventScheduler();
		currentSchedule.processEvent(testInputOne);
		
	    assertEquals("9:00AM  Proper Unit Tests for Anyone 30min", currentSchedule.getEventListContainer().get(0));
		String testInputTwo = "Why Python? ​45min";
	    currentSchedule.processEvent(testInputTwo);
	    assertEquals("9:30AM  Why Python? 45min", currentSchedule.getEventListContainer().get(1));
	    String testInputThree = "TDD for Embedded Systems ​30min";
	    currentSchedule.processEvent(testInputThree);
	    assertEquals("10:15AM  TDD for Embedded Systems 30min",currentSchedule.getEventListContainer().get(2));
	}

	@Test
	public void testProcessDuration() {
		EventScheduler currentSchedule = new EventScheduler();
	    assertEquals(30, currentSchedule.processDuration(" 30min"));
	    assertEquals(40, currentSchedule.processDuration(" 40min"));
	    assertEquals(50, currentSchedule.processDuration(" 50min"));
	}

	@Test
	public void testProcessEventName() {
		String[] testOne = {"Proper", "Unit","Tests","for","Anyone","30min" };
		EventScheduler currentSchedule = new EventScheduler();
	    assertEquals(" Proper Unit Tests for Anyone", currentSchedule.processEventName(testOne));
	    String[] testTwo = {"Why", "Python?","​45min" };
	    assertEquals(" Why Python?", currentSchedule.processEventName(testTwo));
	    String[] testThree = {"TDD", "for", "Embedded", "Systems", "​45min" };
	    assertEquals(" TDD for Embedded Systems", currentSchedule.processEventName(testThree));
	}

	@Test
	public void testScheduleEvent() {
		String testOneName = " Proper Unit Tests for Anyone";
		EventScheduler currentSchedule = new EventScheduler();
		currentSchedule.scheduleEvent(testOneName, 30);
	    assertEquals("9:00AM  Proper Unit Tests for Anyone 30min",currentSchedule.getEventListContainer().get(0));
	    String testTwoName = " Why Python?";
		currentSchedule.scheduleEvent(testTwoName, 30);
	    assertEquals("9:30AM  Why Python? 30min",currentSchedule.getEventListContainer().get(1));
	    String testThreeName = " TDD for Embedded Systems";
		currentSchedule.scheduleEvent(testThreeName, 30);
	    assertEquals("10:00AM  TDD for Embedded Systems 30min",currentSchedule.getEventListContainer().get(2));
	}

	@Test
	public void testProcessMorningEvents() {
		String testInputOneName = " Proper Unit Tests for Anyone";
		EventScheduler currentSchedule = new EventScheduler();
		currentSchedule.processMorningEvents(30, testInputOneName, 0);
	    assertEquals("9:00AM  Proper Unit Tests for Anyone 30min",currentSchedule.getEventListContainer().get(0));
	}

	@Test
	public void testProcessMidDayEvents() {
		EventScheduler currentSchedule = new EventScheduler();
        currentSchedule.processMidDayEvents(30);
	    assertEquals("12:00PM Lunch",currentSchedule.getEventListContainer().get(0));
	}

	@Test
	public void testProcessAfternoonEvents() {
		String testInputOneName = " Proper Unit Tests for Anyone";
		EventScheduler currentSchedule = new EventScheduler();
		currentSchedule.processAfternoonEvents(30, testInputOneName, 0);
	    assertEquals("1:00PM  Proper Unit Tests for Anyone 30min",currentSchedule.getEventListContainer().get(0));
	}

	@Test
	public void testProcessNonCriticalEvents() {
		String testOneName = " From Java 8 to Java 12";
		EventScheduler currentSchedule = new EventScheduler();
		currentSchedule.afternoonDuration = 220;
		currentSchedule.setCurrMinutes(30);
		currentSchedule.getLightningEvents().push(testOneName);
		currentSchedule.processNonCriticalEvents(10, testOneName);
	    assertEquals("4:30PM From Java 8 to Java 12 lightning" ,currentSchedule.getEventListContainer().get(0));
	    currentSchedule.afternoonDuration = 240;
	    currentSchedule.setCurrMinutes(30);
	    currentSchedule.getLightningEvents().push(testOneName);
		currentSchedule.processNonCriticalEvents(10, testOneName);
	    assertEquals("5:00PM Networking Event" ,currentSchedule.getEventListContainer().get(1));
	}


	@Test
	public void testDisplaySchedule() {
		EventScheduler currentSchedule = new EventScheduler();
		currentSchedule.getEventListContainer().add("test one");
		currentSchedule.getEventListContainer().add("test two");
		currentSchedule.getEventListContainer().add("test three");
	    assertEquals("test one" ,currentSchedule.getEventListContainer().get(0));
	    assertEquals("test two" ,currentSchedule.getEventListContainer().get(1));
	    assertEquals("test three" ,currentSchedule.getEventListContainer().get(2));
	}

}
