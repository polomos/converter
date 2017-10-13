package com.polomos.validator;

import static java.nio.file.Files.exists;
import static java.nio.file.Files.isDirectory;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class responsible for validation of path delivered to applications
 * 
 * @author JPOLOM
 *
 */
public final class FileValidator {

	private FileValidator() {
	};

	private static final Logger log = LoggerFactory.getLogger(FileValidator.class);

	/**
	 * Check if {@code filePath} is correct.
	 * 
	 * @param filePath
	 * @return {@code true} in case file is correct, {@code false} otherwise
	 */
	public static boolean isValidPath(final String filePath) {
		final Path path = Paths.get(filePath);

		if (!exists(path)) {
			log.error("File {} does not extis", filePath);
		} else if (isDirectory(path)) {
			log.error("Path {} is a directory", filePath);
		} else {
			return true;
		}
		return false;
	}

}
