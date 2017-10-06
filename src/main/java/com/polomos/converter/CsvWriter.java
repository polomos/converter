package com.polomos.converter;

import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvWriter {

	private static final Logger log = LoggerFactory.getLogger(CsvWriter.class);
	private FileWriter writer;

	public void writeSentence(final Sentence sentence) throws IOException {
		if (writer == null) {
			try {
				writer = new FileWriter("output.csv");
				log.debug("writer");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.write(sentence.toString());
	}

	public void close() {
		try {
			if (writer != null) {
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
