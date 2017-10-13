package com.polomos.converter;

import static org.apache.commons.io.FilenameUtils.getBaseName;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polomos.io.CsvWriter;
import com.polomos.io.XmlWriter;
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

	public FileProcessorService() {
	}

	/**
	 * Process provided file. Reads all lines. When end of sentence is detected,
	 * it is converted into xml and csv and pushed to file.
	 */
	public void process(final String filePath) {
		final String fileBaseName = getBaseName(filePath);

		// validate if provided path is correct
		if (FileValidator.isValidPath(filePath)) {
			final CsvWriter csvWriter = new CsvWriter(fileBaseName);
			final XmlWriter xmlWriter = new XmlWriter(fileBaseName);
			BufferedReader br = null;
			SentenceProcessor sp = null;

			try {
				br = Files.newBufferedReader(Paths.get(filePath), Charset.forName("UTF-8"));
				String currentLine;
				sp = new SentenceProcessor(csvWriter, xmlWriter);
				while ((currentLine = br.readLine()) != null) {
					sp.processLine(currentLine);
				}
				// must be call in case there is no end of line mark at the end
				// of file
				sp.putCurrentSentenceToFiles();
			} catch (IOException e) {
				log.error(e.getMessage());
			} finally {
				try {
					if (br != null) {
						br.close();
					}
					xmlWriter.close();
					csvWriter.close();
				} catch (IOException ex) {
					log.error(ex.getMessage());
				}
			}
		}
	}
}
