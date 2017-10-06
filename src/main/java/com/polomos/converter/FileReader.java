package com.polomos.converter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReader {
	private static final Logger log = LoggerFactory.getLogger(FileReader.class);
	private LineIterator it = null;

	public FileReader(final File inputFile) {
		try {
			it = FileUtils.lineIterator(inputFile, "UTF-8");
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Reads next line from file and returns it. In case there is nothing to
	 * read from file, then empty string is returned.
	 * 
	 * @return next line from file of empty string in case there is nothing to
	 *         read.
	 */
	public String readLine() {
		if (it.hasNext()) {
			final String line = it.nextLine();
			log.debug(line);
			return line;
		}
		return "";
	}
}
