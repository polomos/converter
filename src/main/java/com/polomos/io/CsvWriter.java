package com.polomos.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polomos.converter.Sentence;

public class CsvWriter extends BufferedFileWriter {

	public static final String CSV_SUFFIX = ".csv";

	private static final Logger log = LoggerFactory.getLogger(CsvWriter.class);
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
	}

	public void replaceFile(final int longestSentence) {
		renameCurrentFile();
		File f1 = new File(CSV_TMP_NAME);
		File f2 = new File(getFilePath());

		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(f1);
			out = new FileOutputStream(f2);
			out.write(getHeader(longestSentence));

			IOUtils.copy(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}

	}

	private void renameCurrentFile() {
		// File (or directory) with old name
		File file = new File(getFilePath());
		// File (or directory) with new name
		File tmpFile = new File(CSV_TMP_NAME);
		// Rename file (or directory)
		file.renameTo(tmpFile);
		tmpFile.deleteOnExit();
	}

	private byte[] getHeader(int longestSentence) {
		final StringBuilder sb = new StringBuilder("");
		for (int i = 1; i <= longestSentence; i++) {
			sb.append(", Word ").append(i);
		}
		sb.append("\n");
		return sb.toString().getBytes();
	}
}
