package com.polomos.io;

import static java.lang.System.lineSeparator;
import static java.nio.file.Files.newBufferedWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polomos.converter.Sentence;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * Class which provide base method which handle writing {@link Sentence} to
 * file.
 * 
 * @author jpolom
 *
 */
public abstract class BufferedFileWriter {

	private static final Logger log = LoggerFactory.getLogger(BufferedFileWriter.class);

	@Getter
	private String filePath;
	@Getter(value = AccessLevel.PROTECTED)
	private int longestSentence = 0;
	private BufferedWriter bw = null;

	public BufferedFileWriter(final String filePath) {
		this.filePath = filePath;

		try {
			bw = newBufferedWriter(Paths.get(filePath));
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Write {@code sentence} into file.
	 * 
	 * @param sentence
	 *            which will be stored in file
	 */
	public void writeSentence(final Sentence sentence) {
		longestSentence = Math.max(longestSentence, sentence.getLenght());
		write(getFormatedSentence(sentence) + lineSeparator());
	}

	/**
	 * Write {@code toWrite} into file
	 * 
	 * @param toWrite
	 */
	protected void write(final String toWrite) {
		try {
			bw.write(toWrite);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * Handle end of file and close stream.
	 */
	public void close() {
		handleEndOfFile();
		closeWriter();
	}

	/**
	 * Close writer stream
	 */
	protected void closeWriter() {
		try {
			if (bw != null) {
				bw.close();
			}

		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
	}

	/**
	 * Format sentence according to requirement in particular writer.
	 * 
	 * @param sentence
	 *            to write
	 * @return formated sentence
	 */
	protected abstract String getFormatedSentence(final Sentence sentence);

	/**
	 * Add additional actions, which should be executed at the end writing to
	 * file.
	 */
	protected abstract void handleEndOfFile();

}
