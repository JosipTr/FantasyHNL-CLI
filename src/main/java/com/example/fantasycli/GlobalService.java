package com.example.fantasycli;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GlobalService {
	private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
	
	@Autowired
	private ApiRepository apiRepository;

	public Gson getGson() {
		return gson;
	}

	public ApiRepository getApiRepository() {
		return apiRepository;
	}

}
