package com.polomos.converter;

import org.junit.Test;

/**
 * Test class for {@link FileProcessorService}
 * 
 * @author JPOLOM
 *
 */
public final class FileProcessorServiceTest {

	@Test
	public void testProcess() {
		final FileProcessorService fps = new FileProcessorService("file.txt");
		fps.process();
	}
}
