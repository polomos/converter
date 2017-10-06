package com.polomos.converter;

import java.util.ArrayList;

public class Sentence {
	private ArrayList<String> words = new ArrayList<>();
	private int sentenceNo = 1;

	public boolean addWord(final String word) {
		words.add(word);
		return true;
	}

	/**
	 * Convert current sentence to CSV
	 * 
	 * @return
	 */
	public String toCsv() {
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
	public void clear() {
		words.clear();
	}

	/**
	 * @return current sentence no
	 */
	public int getSentenceNo() {
		return sentenceNo;
	}
}
