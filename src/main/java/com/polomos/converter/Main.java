package com.polomos.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class, which start whole processing
 * 
 * @author JPOLOM
 *
 */
public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	private Main() {
	};

	public static void main(String[] args) {
		log.debug("Start application");
		new FileProcessorService(args[0]).process();
		log.debug("Stop application");
	}
}
