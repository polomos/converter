package com.polomos.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	

	public static void main(String[] args) {
		log.debug("Start application");
		
		final FileProcessorService fps = new FileProcessorService(args[0]);
		fps.process();
		
		log.debug("Stop application");
	}
}
