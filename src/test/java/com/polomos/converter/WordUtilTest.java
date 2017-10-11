package com.polomos.converter;

import static com.polomos.converter.WordUtil.isEndOfSentence;
import static com.polomos.converter.WordUtil.removeSpecialCharacters;
import static com.polomos.converter.WordUtil.splitLine;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class WordUtilTest {

	@Test
	public void testEndOfSentence() {
		testEndOfSentence(".");
		testEndOfSentence("!");
		testEndOfSentence("?");
		assertThat(isEndOfSentence("Mr.")).isFalse();
		assertThat(isEndOfSentence("end.")).isTrue();
	}

	@Test
	public void testSpecialCharacterReplace() {
		assertThat(removeSpecialCharacters("asd")).isEqualTo("asd");
		assertThat(removeSpecialCharacters(".asd")).isEqualTo("asd");
		assertThat(removeSpecialCharacters("couldn't")).isEqualTo("couldn't");
		assertThat(removeSpecialCharacters("(and")).isEqualTo("and");
		assertThat(removeSpecialCharacters("finger)")).isEqualTo("finger");
		assertThat(removeSpecialCharacters("asd,")).isEqualTo("asd");
		assertThat(removeSpecialCharacters("asd?")).isEqualTo("asd");
		assertThat(removeSpecialCharacters("asd!")).isEqualTo("asd");
		assertThat(removeSpecialCharacters("asd:")).isEqualTo("asd");
	}

	@Test
	public void testSpliLine() {
		assertThat(splitLine("Ala ma kota")).containsOnly("Ala", "ma", "kota");
		assertThat(splitLine("Ala ma kota,ok?")).containsOnly("Ala", "ma", "kota", "ok?");
		assertThat(splitLine("Ala ma kota.")).containsOnly("Ala", "ma", "kota.");
	}

	private void testEndOfSentence(final String symbol) {
		assertThat(isEndOfSentence(symbol)).isTrue();
		assertThat(isEndOfSentence("a" + symbol)).isTrue();
		assertThat(isEndOfSentence("asdf" + symbol)).isTrue();
		assertThat(isEndOfSentence(symbol + "a")).isFalse();
		assertThat(isEndOfSentence(symbol + "asdf")).isFalse();
	}
}
