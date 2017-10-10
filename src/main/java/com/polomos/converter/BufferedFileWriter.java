package com.polomos.converter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedFileWriter {

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

	protected void write(final Sentence toWrite) {

		try {
			bw.write(toWrite.toCsv());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
