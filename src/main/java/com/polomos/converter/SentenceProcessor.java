package com.polomos.converter;

import static com.polomos.io.WordUtil.isEndOfSentence;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.polomos.io.CsvWriter;
import com.polomos.io.XmlWriter;

public class SentenceProcessor {

	private static final Logger log = LoggerFactory.getLogger(SentenceProcessor.class);
	private static final Splitter SPLITTER = Splitter.on(CharMatcher.WHITESPACE).trimResults().omitEmptyStrings();
	private CsvWriter csvWriter;
	private XmlWriter xmlWriter;
	private Sentence sentence = new Sentence();

	public SentenceProcessor(final String fileName) {
		csvWriter = new CsvWriter(fileName);
		xmlWriter = new XmlWriter(fileName);
	}

	public void processLine(final String line) throws IOException {
		final Iterable<String> splitedLine = SPLITTER.split(line);

		for (String word : splitedLine) {
			if (isEndOfSentence(word)) {
				sentence.addWord(word.substring(0, word.length() - 1));
				if (sentence.isNotEmpty()) {
					putSentenceToFiles();
					sentence.startNew();
				}
			} else {
				sentence.addWord(word);
			}
		}
	}

	private void putSentenceToFiles() {
		sentence.sortSentence();
		log.debug("Sorted {}", sentence);
		csvWriter.writeSentence(sentence);
		xmlWriter.writeSentence(sentence);
	}

	/**
	 * Close all open files.
	 */
	public void close() {
		csvWriter.close();
		xmlWriter.close();
	}
}
