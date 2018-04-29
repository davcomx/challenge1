package com.dl.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class Challenge1ServiceImpl implements Challenge1Service {

	private static final Logger LOGGER = LoggerFactory.getLogger(Challenge1ServiceImpl.class);
	
	private static final String CLASSPATH_FILE = "classpath:static/city.txt";
	private static final String RESPONSE_NO  = "no";
	private static final String RESPONSE_YES = "yes";
	
	private Map<String, Set<String>> cityMap = new HashMap<>();

	@PostConstruct
	private void init() {
		if (cityMap.isEmpty()) {
			File file = null;

			try {
				file = ResourceUtils.getFile(CLASSPATH_FILE);
			} catch (FileNotFoundException e) {
				LOGGER.error(e.getMessage());
			}

			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNext()) {
					String[] cities = scanner.nextLine().split(", ");
					addCity(cities[0], cities[1]);
					addCity(cities[1], cities[0]);
				}
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}			
		}		
	}
	
	@Override
	public String connected(String origin, String destination) {
		return isConnected(origin, destination) ? RESPONSE_YES : RESPONSE_NO;
	}
	
	@Override
	public boolean isConnected(String origin, String destination) {
		if (origin == null || destination == null) return false;		
		if (!cityMap.containsKey(origin) || !cityMap.containsKey(destination)) return false;
		
		boolean isFound = false;		
		Set<String> visitedCities = new HashSet<>();
		Queue<String> cityQueue = new LinkedList<>();
		cityQueue.add(origin);
		   
		while (!cityQueue.isEmpty()) {
			String currentCity = cityQueue.poll();
		         
		    if (cityMap.get(currentCity).contains(destination)) {
		    	isFound = true;
		    } else {
		    	visitedCities.add(currentCity);
		        
		    	for (String neigbourCity : cityMap.get(currentCity)) {
		    		if (!visitedCities.contains(neigbourCity)) {
		                cityQueue.add(neigbourCity);
		            }
		    	}
		    }
		}
		
		return isFound;
	}

	private void addCity(String city1, String city2) {
		if (cityMap.containsKey(city1)) {
			cityMap.get(city1).add(city2);
		} else {
			HashSet<String> cities = new HashSet<>();
			cities.add(city2);
			cityMap.put(city1, cities);
		}
	}

}
