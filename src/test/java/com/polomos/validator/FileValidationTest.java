package com.polomos.validator;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Test class for {@link FileValidator}
 * 
 * @author JPOLOM
 *
 */
public class FileValidationTest {

	private final FileValidator underTest = new FileValidator();
	private final String basePath = "src/test/resources/";

	@Test
	public void testFilePath() {
		assertThat(underTest.isValid(basePath + "notExists.txt")).isFalse();
		assertThat(underTest.isValid(basePath + "emptyDirectory")).isFalse();
		assertThat(underTest.isValid(basePath + "emptyFile.txt")).isTrue();
	}
}