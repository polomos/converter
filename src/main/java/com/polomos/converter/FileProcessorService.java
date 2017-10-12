package com.polomos.converter;

import static org.apache.commons.io.FilenameUtils.getBaseName;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
