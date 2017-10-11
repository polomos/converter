package com.polomos.converter;

import static com.polomos.converter.WordUtil.isEndOfSentence;
import static com.polomos.converter.WordUtil.splitLine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.polomos.io.CsvWriter;
import com.polomos.io.XmlWriter;

import lombok.Getter;

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
	@Getter
	private int longestSentence = 0;
	private CsvWriter csvWriter;
	private XmlWriter xmlWriter;

	public SentenceProcessor(final CsvWriter csvWriter, final XmlWriter xmlWriter) {
		this.csvWriter = csvWriter;
		this.xmlWriter = xmlWriter;
	}

	/**
	 * Split line into words, sort its and store in file.
	 * 
	 * @param line
	 */
	public void processLine(final String line) {
		log.debug("readed line: {}", line);
		for (String word : splitLine(line)) {
			log.debug("processing : {}", word);
			if (isEndOfSentence(word)) {
				sentence.addWord(word);
				if (sentence.isNotEmpty()) {
					putSentenceToFiles();
					sentence.startNewSentence();
				}
			} else {
				sentence.addWord(word);
			}
		}
	}

	/**
	 * Put sentence to both csv and xml file
	 */
	private void putSentenceToFiles() {
		sentence.sortSentence();
		log.info("Sorted {}", sentence);
		csvWriter.writeSentence(sentence);
		xmlWriter.writeSentence(sentence);
		longestSentence = Math.max(longestSentence, sentence.getLenght());
	}
}
