package com.polomos.io;

import java.util.regex.Pattern;

import com.google.common.collect.ImmutableSet;

/**
 * Util class for handling sentence and words. It provides methods for removing
 * special characters and detecting end of sentence.
 * 
 * @author JPOLOM
 *
 */
public final class WordUtil {

	private static final ImmutableSet<String> periodWords = ImmutableSet.of("Mr.", "Mrs.", "Ms.", "Prof.", "Dr.",
			"Gen.", "Rep.", "Sen.", "St.", "Sr.", "Jr.", "Ph.", "Ph.D.", "M.D.", "B.A.", "M.A.", "D.D.", "D.D.S.",
			"B.C.", "b.c.", "a.m.", "A.M.", "p.m.", "P.M.", "A.D.", "a.d.", "B.C.E.", "C.E.", "i.e.", "etc.", "e.g.",
			"al.");
	private static final Pattern END_OF_SENTENCE = Pattern.compile(".*[.!?]$");
	private static final Pattern SPECIAL_CHARACTER = Pattern.compile("[.!?,-/(/):]");

	/** Unused private constructor */
	private WordUtil() {
	}

	/**
	 * Checks if {@code word} is the last one in sentence.
	 * 
	 * @param word
	 * @return {@code true} in case provided work is the last one in sentence,
	 *         {@code false} otherwise
	 */
	public static boolean isEndOfSentence(final String word) {

		if (END_OF_SENTENCE.matcher(word).matches() && !periodWords.contains(word)) {
			return true;
		}
		return false;
	}

	/**
	 * Remove special characters from {@code word}
	 * 
	 * @param word
	 * @return word without special characters
	 */
	public static String removeSpecialCharacters(final String word) {
		return periodWords.contains(word) ? word : SPECIAL_CHARACTER.matcher(word).replaceAll("");
	}
}
