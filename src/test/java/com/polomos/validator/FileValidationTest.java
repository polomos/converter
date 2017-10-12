package com.polomos.validator;

import static com.polomos.validator.FileValidator.isValidPath;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Test class for {@link FileValidator}
 * 
 * @author JPOLOM
 *
 */
public class FileValidationTest {

	private final String basePath = "src/test/resources/";

	@Test
	public void testFilePath() {
		assertThat(isValidPath(basePath + "notExists.txt")).isFalse();
		assertThat(isValidPath(basePath + "emptyDirectory")).isFalse();
		assertThat(isValidPath(basePath + "emptyFile.txt")).isTrue();
	}
}