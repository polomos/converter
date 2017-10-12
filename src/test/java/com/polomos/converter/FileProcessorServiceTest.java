package com.polomos.converter;

import static java.nio.file.Files.deleteIfExists;
import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

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
		assertThat(deleteFile(EMPTY_FILE)).isTrue();
	}

	/**
	 * Process file, which contains one line
	 */
	@Test
	public void testProcessingFile1() {
		fps.process(basePath + ONE_LINE_FILE);
		assertThat(deleteFile(ONE_LINE_FILE)).isTrue();

	}

	private boolean deleteFile(final String fileName) {
		final String baseFileName = FilenameUtils.getBaseName(fileName);
		try {
			return deleteIfExists(new File(baseFileName + ".csv").toPath())
					&& deleteIfExists(new File(baseFileName + ".xml").toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
