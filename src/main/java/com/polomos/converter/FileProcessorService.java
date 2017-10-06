package com.polomos.converter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polomos.validator.FileValidator;

/**
 * Service responsible for whole processing process. It reads file, converts and
 * write into output
 * 
 * @author JPOLOM
 *
 */
public final class FileProcessorService {
	private static final Logger log = LoggerFactory.getLogger(FileProcessorService.class);
	private String filePath;

	public FileProcessorService(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Process provided file
	 */
	public void process() {

		if (FileValidator.isValid(filePath)) {
			final SentenceProcessor sp = new SentenceProcessor();
			final File inputFile = new File(filePath);
			LineIterator it = null;
			try {
				it = FileUtils.lineIterator(inputFile, "UTF-8");
				while (it.hasNext()) {
					String line = it.nextLine();
					sp.processLine(line);
					log.debug(line);
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			} finally {
				LineIterator.closeQuietly(it);
				sp.close();
			}
		}
	}

}
