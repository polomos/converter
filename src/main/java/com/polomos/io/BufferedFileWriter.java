package com.polomos.io;

import static java.lang.System.lineSeparator;

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.commons.io.output.FileWriterWithEncoding;

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

	@Getter
	private String filePath;
	@Getter(value = AccessLevel.PROTECTED)
	private int longestSentence = 0;
	private BufferedWriter bw = null;
	private FileWriterWithEncoding fw = null;

	public BufferedFileWriter(final String filePath) {
		this.filePath = filePath;
		try {
			fw = new FileWriterWithEncoding(filePath, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw = new BufferedWriter(fw);
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
			e.printStackTrace();
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
			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();

		} catch (IOException ex) {
			ex.printStackTrace();
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
