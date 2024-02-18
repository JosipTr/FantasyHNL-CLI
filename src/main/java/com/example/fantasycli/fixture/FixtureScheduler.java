package com.example.fantasycli.fixture;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.shell.standard.ShellComponent;


import jakarta.transaction.Transactional;

@ShellComponent
@EnableScheduling
@Transactional
public class FixtureScheduler implements SchedulingConfigurer {
	private final List<Fixture> nextFixtures = new ArrayList<>();
	private final Map<Runnable, String> taskMap = new HashMap<>();
	private final Map<Runnable, String> taskMapNew = new HashMap<>();
	private int counter;
	private final FixtureRepository repository;
	private final FixtureService service;

	public FixtureScheduler(FixtureRepository repository, FixtureService service) {
		super();
		this.counter = 0;
		this.repository = repository;
		this.service = service;
	}

	@SuppressWarnings("deprecation")
	@Bean
	public TaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler();
	}

	public void getFixtures() {
		counter++;
		nextFixtures.clear();
		var dateTime = LocalDateTime.now();

		var fixtures = repository.findAll();
		fixtures.sort((o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()));

		for (var fixture : fixtures) {
			var fixtureDate = LocalDateTime.ofEpochSecond(fixture.getTimestamp(), 0, ZoneOffset.ofHours(1));

			if (dateTime.getDayOfYear() < fixtureDate.getDayOfYear())
				continue;

			if (dateTime.getDayOfYear() == fixtureDate.getDayOfYear()) {
				nextFixtures.add(fixture);
				continue;
			}
		}
		addTask();
		return;
	}

	public void updateLiveFixture(int fixtureId) throws InterruptedException {
		counter++;
//		service.getFixture(fixtureId);
	}

	public void stop(ScheduledTaskRegistrar taskRegistrar) {
		if (counter == 90) {
			taskRegistrar.destroy();
			return;
		}
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskMap.put(() -> stop(taskRegistrar), "0/30 * * * * *");
		getFixtures();
		taskRegistrar.setScheduler(taskScheduler());
		taskRegistrar.setCronTasks(taskMap);
	}

	public void addTask() {
		StringBuilder builder = new StringBuilder();
		for (var fixture : nextFixtures) {
			var fixtureDate = LocalDateTime.ofEpochSecond(fixture.getTimestamp(), 0, ZoneOffset.ofHours(1));
			var day = fixtureDate.getDayOfMonth();
			var month = fixtureDate.getMonthValue();
			var minute = fixtureDate.getMinute();
			var hour = fixtureDate.getHour();
			var s = builder.append(0).append(" ").append(minute + "/2").append(" ").append(hour).append(" ").append(day)
					.append(" ").append(month).append(" ").append("*").toString();

			var n = getHalfTime(fixtureDate);
			taskMap.put(() -> {
				try {
					updateLiveFixture(fixture.getId());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, s);
			taskMapNew.put(() -> {
				try {
					updateLiveFixture(fixture.getId());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, n);
			System.out.println(s);
			builder.setLength(0);
		}
	}

	private String getHalfTime(LocalDateTime fixtureDate) {
		StringBuilder builder = new StringBuilder();
		var day = fixtureDate.getDayOfMonth();
		var month = fixtureDate.getMonthValue();
		var minute = fixtureDate.getMinute();
		var hour = fixtureDate.getHour();

		if (minute + 45 >= 60) {
			minute -= 60;
			hour += 1;
		} else {
			minute += 45;
		}
		var n = builder.append(0).append(" ").append(minute + "/5").append(" ").append(hour).append(" ").append(day)
				.append(" ").append(month).append(" ").append("*").toString();
		return n;
	}
}
