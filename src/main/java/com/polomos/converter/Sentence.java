package com.polomos.converter;

import static com.polomos.converter.WordUtil.removeSpecialCharacters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.google.common.base.Joiner;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Sentence {

	private static Comparator<String> stringAlphabeticalComparator = new Comparator<String>() {
		@Override
		public int compare(String str1, String str2) {
			return ComparisonChain.start().compare(str1, str2, String.CASE_INSENSITIVE_ORDER).compare(str2, str1)
					.result();
		}
	};

	private ArrayList<String> words = new ArrayList<>();
	private int sentenceNo = 1;

	/**
	 * Add {@code word} to sentence. Before adding it special characters are
	 * removed. Word is added only in case it has length > 0
	 * 
	 * @param word
	 * @return the word, which is finaly added to sentence
	 */
	public String addWord(final String word) {
		final String finalWord = removeSpecialCharacters(word);
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
		return "Sentence " + sentenceNo + ", " + Joiner.on(", ").join(words);
	}

	/**
	 * Convert current sentence to XML
	 * 
	 * @return
	 */
	public String toXml() {
		return "<sentence><word>" + Joiner.on("</word><word>").join(words) + "</word></sentence>";
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
	 * Removes all words from current sentence and increase sentence number.
	 */
	public void startNewSentence() {
		words.clear();
		sentenceNo++;
	}

	/**
	 * @return current sentence no
	 */
	public int getSentenceNo() {
		return sentenceNo;
	}

	public void sortSentence() {
		Collections.sort(words, Ordering.from(stringAlphabeticalComparator));
	}

	/**
	 * @return number of words in sentence
	 */
	public int getLenght() {
		return words.size();
	}
}
