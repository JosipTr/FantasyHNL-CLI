package com.example.fantasycli;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;

import com.example.fantasycli.country.CountryRepository;
import com.example.fantasycli.fixture.Fixture;
import com.example.fantasycli.fixture.FixtureRepository;
import com.example.fantasycli.player.PlayerRepository;
import com.example.fantasycli.team.TeamRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@ShellComponent
@Transactional
@AllArgsConstructor
public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private static final String TOPIC = "users";
	private final FixtureRepository fixtureRepository;
	private final CountryRepository countryRepository;
	private final TeamRepository teamRepository;
	private final PlayerRepository playerRepository;

//    @Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@ShellMethod(key = "send")
	public void sendMessage(String fixtureId) {

		logger.info(String.format("#### -> Producing message -> %s", fixtureId));
		this.kafkaTemplate.send(TOPIC, fixtureId);

	}
}
