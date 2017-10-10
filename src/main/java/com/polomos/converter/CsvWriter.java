package com.polomos.converter;

public class CsvWriter extends BufferedFileWriter {

	private static final String CSV_SUFFIX = ".csv";

	public CsvWriter(final String filePath) {
		super(filePath + CSV_SUFFIX);
	}

}
