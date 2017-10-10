package com.polomos.converter;

import java.util.ArrayList;
import java.util.Collections;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Ordering;
import com.polomos.io.WordUtil;

public class Sentence {
	private ArrayList<String> words = new ArrayList<>();
	private int sentenceNo = 1;

	/**
	 * Add {@code word} to sentence. Before adding it special characters are
	 * removed. Word is added only in case it has length > 0
	 * 
	 * @param word
	 * @return
	 */
	public String addWord(final String word) {
		final String finalWord = WordUtil.removeSpecialCharacters(word);
		if (finalWord.length() > 0) {
			words.add(finalWord);
		}
		return finalWord;
	}

	/**
	 * Convert current sentence to CSV
	 * 
	 * @return
	 */
	public String toCsv() {
		return Joiner.on(",").join(words);
	}

	/**
	 * Convert current sentence to XML
	 * 
	 * @return
	 */
	public String toXml() {
		return words.toString();
	}

	/**
	 * Checks if there are any words in sentece
	 * 
	 * @return
	 */
	public boolean isNotEmpty() {
		return !words.isEmpty();
	}

	/**
	 * Removes all words from sentence
	 */
	public void startNew() {
		words.clear();
		sentenceNo++;
	}

	/**
	 * @return current sentence no
	 */
	public int getSentenceNo() {
		return sentenceNo;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("sentenceNo", sentenceNo).add("words", words).toString();
	}

	public void sortSentence() {
		Collections.sort(words, Ordering.from(String.CASE_INSENSITIVE_ORDER));
	}
}
