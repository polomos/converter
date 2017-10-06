package com.polomos.validator;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class responsible for validation of path delivered to applications
 * 
 * @author JPOLOM
 *
 */
public final class FileValidator {

	private static final Logger log = LoggerFactory.getLogger(FileValidator.class);

	public static boolean isValid(final String filePath) {
		File f = new File(filePath);
		if (!f.exists()) {
			log.error("File {} does not extis", filePath);
		} else if (f.isDirectory()) {
			log.error("Path {} is a directory", filePath);
		} else {
			return true;
		}
		return false;
	}

}
