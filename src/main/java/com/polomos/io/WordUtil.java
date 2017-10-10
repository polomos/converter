package com.polomos.io;

import java.util.regex.Pattern;

import com.google.common.collect.ImmutableSet;

public final class WordUtil {

	private static final ImmutableSet<String> periodWords = ImmutableSet.of("Mr.", "Mrs.", "Ms.", "Prof.", "Dr.",
			"Gen.", "Rep.", "Sen.", "St.", "Sr.", "Jr.", "Ph.", "Ph.D.", "M.D.", "B.A.", "M.A.", "D.D.", "D.D.S.",
			"B.C.", "b.c.", "a.m.", "A.M.", "p.m.", "P.M.", "A.D.", "a.d.", "B.C.E.", "C.E.", "i.e.", "etc.", "e.g.",
			"al.");

	private static final Pattern END_OF_SENTENCE = Pattern.compile(".*[.!?]$");

	private static final Pattern SPECIAL_CHARACTER = Pattern.compile("[^a-zA-Z0-9']");

	public static boolean isEndOfSentence(final String word) {

		if (END_OF_SENTENCE.matcher(word).matches()) {
			if (periodWords.contains(word)) {
				return false;
			}
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
		return SPECIAL_CHARACTER.matcher(word).replaceAll("");
	}
}
