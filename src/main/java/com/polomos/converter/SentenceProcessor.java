package com.polomos.converter;

import java.io.IOException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

public class SentenceProcessor {

	private static final Set<String> END_OF_SENTENCE_DELIMETER = Sets.newHashSet(".", "!");

	private static final Logger log = LoggerFactory.getLogger(SentenceProcessor.class);
	private CsvWriter csvWriter = new CsvWriter();
	private Sentence sentence = new Sentence();

	public void processLine(final String line) throws IOException {
		final Iterable<String> splitedLine = Splitter.on(" ").trimResults().omitEmptyStrings().split(line);
		for (String word : splitedLine) {
			log.debug("Processing {}", word);
			if (END_OF_SENTENCE_DELIMETER.contains(word) || word.c) {
				if (!sentence.isNotEmpty()) {
					csvWriter.writeSentence(sentence);
					sentence.clear();
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
