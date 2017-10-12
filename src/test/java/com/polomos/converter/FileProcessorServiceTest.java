package com.polomos.converter;

import static com.polomos.io.CsvWriter.CSV_SUFFIX;
import static com.polomos.io.XmlWriter.XML_SUFFIX;
import static java.nio.file.Files.deleteIfExists;
import static org.apache.commons.io.FilenameUtils.getBaseName;
import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * Test class for {@link FileProcessorService}
 * 
 * @author JPOLOM
 *
 */
public final class FileProcessorServiceTest {

	private static final String NOT_EXISTING_FILE = "file.txt";
	private static final String ONE_LINE_FILE = "one_line_file.txt";
	private static final String EMPTY_FILE = "emptyFile.txt";
	private final String basePath = "src/test/resources/";
	private final FileProcessorService fps = new FileProcessorService();

	/**
	 * Simple test, which checks if {@link FileProcessorService} process
	 * correctly not existing file
	 */
	@Test
	public void testProcess() {
		fps.process(NOT_EXISTING_FILE);
		assertThat(deleteFile(EMPTY_FILE)).isFalse();
	}

	/**
	 * Test if processing of empty file works
	 */
	@Test
	public void testProcessEmptyFile() {
		fps.process(basePath + EMPTY_FILE);
		assertThat(deleteFile(getBaseName(EMPTY_FILE))).isTrue();
	}

	/**
	 * Process file, which contains one line
	 */
	@Test
	public void testProcessingFile1() {
		fps.process(basePath + ONE_LINE_FILE);
		final String baseFileName = getBaseName(ONE_LINE_FILE);

		final List<String> csvLines = readFile(baseFileName + CSV_SUFFIX);
		assertThat(csvLines).hasSize(2);
		assertThat(csvLines.get(0)).isEqualTo(", Word 1, Word 2, Word 3");
		assertThat(csvLines.get(1)).isEqualTo("Sentence 1, Ala, kota, ma");

		final List<String> xmlLines = readFile(baseFileName + XML_SUFFIX);
		assertThat(xmlLines).hasSize(4);
		assertThat(xmlLines.get(0)).isEqualTo("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		assertThat(xmlLines.get(1)).isEqualTo("<text>");
		assertThat(xmlLines.get(2)).isEqualTo("<sentence><word>Ala</word><word>kota</word><word>ma</word></sentence>");
		assertThat(xmlLines.get(3)).isEqualTo("</text>");

		assertThat(deleteFile(baseFileName)).isTrue();

	}

	private boolean deleteFile(final String fileName) {
		try {
			return deleteIfExists(new File(fileName + CSV_SUFFIX).toPath())
					&& deleteIfExists(new File(fileName + XML_SUFFIX).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private List<String> readFile(final String filePath) {
		List<String> lines = Lists.newArrayList();
		final File file = new File(filePath);
		try {
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
