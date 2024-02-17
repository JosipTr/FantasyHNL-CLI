package com.example.fantasycli;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

import com.example.fantasycli.fixture.Fixture;
import com.example.fantasycli.fixture.FixtureRepository;
import com.example.fantasycli.fixture.FixtureService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@ShellComponent
@EnableScheduling
public class FixtureScheduler implements SchedulingConfigurer{
	private final List<Fixture> nextFixtures = new ArrayList<>();
	private final List<String> fixtureDates = new ArrayList<>();
	private final Map<Runnable, String> taskMap = new HashMap<>();
	private final FixtureRepository repository;
	private final FixtureService service;
	private ScheduledAnnotationBeanPostProcessor postProcessor;
	
	@ShellMethod(key = "da")
	@Transactional
	public void getFixtures() {
		nextFixtures.clear();
		var dateTime = LocalDateTime.now();
		var day = dateTime.getDayOfMonth();
		var month = dateTime.getMonthValue();
		var year = dateTime.getYear();
		var fixtures = repository.findAll();
		fixtures.sort((o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()));
		
		for (var fixture : fixtures) {
			var fixtureDate = LocalDateTime.ofEpochSecond(fixture.getTimestamp(), 0, ZoneOffset.ofHours(1));

	        if (dateTime.getDayOfYear() < fixtureDate.getDayOfYear()) continue;
//	        if (day == fixtureDate.getDayOfMonth() && month == fixtureDate.getMonthValue() && year == fixtureDate.getYear()) {
//	        	
//	        }
	        if (dateTime.getDayOfYear() == fixtureDate.getDayOfYear()) {
	        	nextFixtures.add(fixture);
	        	continue;
	        }
	        
//	        if (dateTime.getDayOfYear() > fixtureDate.getDayOfYear()) break;
	        
		}
		addTask();
		return;
	}
	@Transactional
	public void updateLiveFixture(int fixtureId) {
		System.out.println("da");
		service.getFixture(fixtureId);
	}
	
	public void addTask() {
		StringBuilder builder = new StringBuilder();
		for (var fixture : nextFixtures) {
			var fixtureDate = LocalDateTime.ofEpochSecond(fixture.getTimestamp(), 0, ZoneOffset.ofHours(1));
			var day = fixtureDate.getDayOfMonth();
			var month = fixtureDate.getMonthValue();
			var year = fixtureDate.getYear();
			var minute = fixtureDate.getMinute();
			var hour = fixtureDate.getHour();
			var s =builder.append(0).append(" ").append(minute + "/2").append(" ").append(hour).append(" ").append(day).append(" ").append(month).append(" ").append("*").toString();
			taskMap.put(() -> updateLiveFixture(fixture.getId()), s);
			System.out.println(s);
			builder.setLength(0);
		}
		System.out.println(taskMap);
		taskMap.put(() -> hej(), "0/30 * * * * *");

	}
	
	public void hej() {
		System.out.println("hej hej hej");
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		getFixtures();
        taskRegistrar.setScheduler(taskScheduler());
        taskRegistrar.setCronTasks(taskMap);
		
	}
	
	  private TaskScheduler taskScheduler() {
	        return new ConcurrentTaskScheduler();
	    }
	  
}


//taskRegistrar.addCronTask(() -> getFixtures(), "0/15 * * * * *");
//taskRegistrar.addCronTask(() -> addTask(), "0/5 * * * * *");
//d.put(() -> getFixtures(), "0 0 0 * * *");
//d.put(() -> addTask(), "0/1 * * * * *");
