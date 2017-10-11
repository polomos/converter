package com.polomos.converter;

import static com.polomos.converter.WordUtil.removeSpecialCharacters;

import java.util.ArrayList;
import java.util.Collections;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Ordering;

public class Sentence {

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
		return "Sentence " + sentenceNo + ", " + Joiner.on(", ").join(words) + "\n";
	}

	/**
	 * Convert current sentence to XML
	 * 
	 * @return
	 */
	public String toXml() {
		return "<sentence><word>" + Joiner.on("</word><word>").join(words) + "</word></sentence>\n";
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

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("sentenceNo", sentenceNo).add("words", words).toString();
	}

	public void sortSentence() {
		Collections.sort(words, Ordering.from(String.CASE_INSENSITIVE_ORDER));
	}

	/**
	 * @return number of words in sentence
	 */
	public int getLenght() {
		return words.size();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(sentenceNo, words);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Sentence other = (Sentence) obj;
		return Objects.equal(this.sentenceNo, other.sentenceNo) && Objects.equal(this.words, other.words);
	}

}
