package com.polomos.converter;

import static org.apache.commons.io.FilenameUtils.getBaseName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
			BufferedReader br = null;
			FileReader fr = null;
			SentenceProcessor sp = null;

			try {
				fr = new FileReader(filePath);
				br = new BufferedReader(fr);

				String currentLine;
				sp = new SentenceProcessor(getBaseName(filePath));
				while ((currentLine = br.readLine()) != null) {
					sp.processLine(currentLine);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (sp != null) {
						sp.close();
					}
					if (br != null) {
						br.close();
					}
					if (fr != null) {
						fr.close();
					}

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
