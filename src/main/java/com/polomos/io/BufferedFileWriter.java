package com.polomos.io;

import static java.lang.System.lineSeparator;

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.commons.io.output.FileWriterWithEncoding;

import com.polomos.converter.Sentence;

import lombok.Getter;

public abstract class BufferedFileWriter {

	@Getter
	private String filePath;
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

	public void writeSentence(final Sentence toWrite) {
		write(getFormatedSentence(toWrite) + lineSeparator());
	}

	protected void write(final String toWrite) {
		try {
			bw.write(toWrite);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract String getFormatedSentence(Sentence toWrite);

	protected abstract void handleEndOfFile();

	public void close() {
		handleEndOfFile();
		try {
			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
