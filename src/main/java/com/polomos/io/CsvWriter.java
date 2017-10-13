package com.polomos.io;

import static java.nio.file.Files.delete;
import static java.nio.file.Files.move;
import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;
import static java.nio.file.Paths.get;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polomos.converter.Sentence;

/**
 * Concrete implementation of file writer, which writes {@link Sentence} in xml
 * format.
 * 
 * @author jpolom
 *
 */
public class CsvWriter extends SentenceWriter {

	private static final Logger log = LoggerFactory.getLogger(CsvWriter.class);
	public static final String CSV_SUFFIX = ".csv";
	private static final String CSV_TMP_NAME = "temp.csv";

	public CsvWriter(final String filePath) {
		super(filePath + CSV_SUFFIX);
	}

	@Override
	protected String getFormatedSentence(Sentence toWrite) {
		return toWrite.toCsv();
	}

	@Override
	protected void handleEndOfFile() {
		closeWriter();
		replaceFileHeader(getLongestSentence());
	}

	/**
	 * Because on the beginning it is not clear how many columns will be in the
	 * longest sentence, it is calculated on the fly. At the end of processing
	 * length of longest sentence is know and header can be created.
	 * 
	 * @param numberOfColumns
	 *            number of columns
	 */
	private void replaceFileHeader(final int numberOfColumns) {
		renameCurrentFile();

		BufferedReader in = null;
		BufferedWriter out = null;
		try {
			in = newBufferedReader(Paths.get(CSV_TMP_NAME));
			out = newBufferedWriter(get(getFilePath()));
			out.write(getHeader(numberOfColumns));
			IOUtils.copy(in, out);
			delete(Paths.get(CSV_TMP_NAME));
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				log.error(ex.getMessage());
			}
		}

	}

	private void renameCurrentFile() {
		try {
			move(Paths.get(getFilePath()), Paths.get(CSV_TMP_NAME));
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Create header line
	 */
	private String getHeader(int longestSentence) {
		final StringBuilder sb = new StringBuilder("");
		for (int i = 1; i <= longestSentence; i++) {
			sb.append(", Word ").append(i);
		}
		sb.append("\n");
		return sb.toString();
	}
}
