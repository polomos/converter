package com.polomos.converter;

import static com.polomos.converter.WordUtil.isEndOfSentence;
import static com.polomos.converter.WordUtil.splitLine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polomos.io.BufferedFileWriter;

/**
 * Class process provided line. Split it into words, remove special characters.
 * When end of sentence is detected, then it sorts it and store in csv and xml
 * file.
 * 
 * @author JPOLOM
 *
 */
public class SentenceProcessor {

	private static final Logger log = LoggerFactory.getLogger(SentenceProcessor.class);

	private Sentence sentence = new Sentence();
	private BufferedFileWriter[] fileWriters;

	/**
	 * Create sentence processor, which handle separate sentences.
	 * 
	 * @param fileWriters
	 *            which will write sentences to file
	 */
	public SentenceProcessor(final BufferedFileWriter... fileWriters) {
		this.fileWriters = fileWriters;
	}

	/**
	 * Split {@code line} into words, sort its and store in xml and csv file.
	 * 
	 * @param line
	 */
	public void processLine(final String line) {
		log.debug("readed line: {}", line);
		for (String word : splitLine(line)) {
			log.debug("processing : {}", word);
			if (isEndOfSentence(word)) {
				sentence.addWord(word);
				putCurrentSentenceToFiles();
			} else {
				sentence.addWord(word);
			}
		}
	}

	/**
	 * Put sentence into files in case there are any words in it. Clear sentence
	 * and increase sentence no
	 */
	public void putCurrentSentenceToFiles() {
		// first sort sentence
		if (sentence.isNotEmpty()) {
			sentence.sortSentence();
			log.info("Sorted {}", sentence);
			for (BufferedFileWriter fileWriter : fileWriters) {
				fileWriter.writeSentence(sentence);
			}
			sentence.startNewSentence();
		}
	}
}
