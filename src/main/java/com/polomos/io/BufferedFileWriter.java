package com.polomos.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.polomos.converter.Sentence;

public abstract class BufferedFileWriter {

	BufferedWriter bw = null;
	FileWriter fw = null;

	public BufferedFileWriter(final String filePath) {

		try {
			fw = new FileWriter(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw = new BufferedWriter(fw);
	}

	public void write(final Sentence toWrite) {
		try {
			bw.write(getFormatedSentence(toWrite));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract String getFormatedSentence(Sentence toWrite);

	public void close() {
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
