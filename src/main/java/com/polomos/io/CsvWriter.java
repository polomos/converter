package com.polomos.io;

import com.polomos.converter.Sentence;

public class CsvWriter extends BufferedFileWriter {

	private static final String CSV_SUFFIX = ".csv";

	public CsvWriter(final String filePath) {
		super(filePath + CSV_SUFFIX);
	}

	@Override
	protected String getFormatedSentence(Sentence toWrite) {
		return toWrite.toCsv();
	}

}
