package com.polomos.converter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

public class SentenceProcessor {

	private static final Logger log = LoggerFactory.getLogger(SentenceProcessor.class);
	private static final Splitter SPLITTER = Splitter.on(CharMatcher.WHITESPACE).trimResults().omitEmptyStrings();
	private CsvWriter csvWriter;
	private Sentence sentence = new Sentence();

	public SentenceProcessor(final String fileName) {
		csvWriter = new CsvWriter(fileName);
	}

	public void processLine(final String line) throws IOException {
		final Iterable<String> splitedLine = SPLITTER.split(line);

		for (String word : splitedLine) {
			if (WordUtil.isEndOfSentence(word)) {
				sentence.addWord(word.substring(0, word.length() - 1));
				log.debug(sentence.toString());
				if (sentence.isNotEmpty()) {
					csvWriter.write(sentence);
					sentence.startNew();
				}
			} else {
				sentence.addWord(word);
			}
		}
	}

	/**
	 * Close all open files.
	 */
	public void close() {
		csvWriter.close();
	}
}
