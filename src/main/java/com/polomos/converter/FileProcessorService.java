package com.polomos.converter;

import static org.apache.commons.io.FilenameUtils.getBaseName;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
	private String filePath;

	public FileProcessorService(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Process provided file
	 */
	public void process() {
		final String fileBaseName = getBaseName(filePath);

		if (FileValidator.isValidPath(filePath)) {
			final CsvWriter csvWriter = new CsvWriter(fileBaseName);
			final XmlWriter xmlWriter = new XmlWriter(fileBaseName);
			BufferedReader br = null;
			InputStreamReader in = null;
			SentenceProcessor sp = null;

			try {
				in = new InputStreamReader(new FileInputStream(filePath), "UTF8");
				br = new BufferedReader(in);

				String currentLine;
				sp = new SentenceProcessor(csvWriter, xmlWriter);
				while ((currentLine = br.readLine()) != null) {
					sp.processLine(currentLine);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null) {
						br.close();
					}
					if (in != null) {
						in.close();
					}
					xmlWriter.close();
					csvWriter.close();
					csvWriter.replaceFile(sp.getLongestSentence());

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
