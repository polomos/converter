package com.polomos.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class, which starts whole processing
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
		if (args.length > 0) {
			new FileProcessorService().process(args[0]);
		} else {
			log.error("Please provided input file as argument");
		}
		log.debug("Stop application");
	}
}
