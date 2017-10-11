package com.polomos.converter;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.polomos.io.WordUtil;

public class WordUtilTest {

	@Test
	public void testEndOfSentence() {
		testSpecialSymbol(".");
		testSpecialSymbol("!");
		testSpecialSymbol("?");
	}

	@Test
	public void testSpecialWords() {
		assertThat(WordUtil.isEndOfSentence("Mr.")).isFalse();
	}

	@Test
	public void testSpecialCharacterReplace() {
		assertThat(WordUtil.removeSpecialCharacters("asd")).isEqualTo("asd");
		assertThat(WordUtil.removeSpecialCharacters(".asd")).isEqualTo("asd");
		assertThat(WordUtil.removeSpecialCharacters("couldn't")).isEqualTo("couldn't");
		assertThat(WordUtil.removeSpecialCharacters("(and")).isEqualTo("and");
		assertThat(WordUtil.removeSpecialCharacters("finger)")).isEqualTo("finger");
		assertThat(WordUtil.removeSpecialCharacters("asd,")).isEqualTo("asd");
		assertThat(WordUtil.removeSpecialCharacters("asd?")).isEqualTo("asd");
		assertThat(WordUtil.removeSpecialCharacters("asd!")).isEqualTo("asd");
		assertThat(WordUtil.removeSpecialCharacters("asd:")).isEqualTo("asd");
	}

	private void testSpecialSymbol(String dot) {
		assertThat(WordUtil.isEndOfSentence(dot)).isTrue();
		assertThat(WordUtil.isEndOfSentence("a" + dot)).isTrue();
		assertThat(WordUtil.isEndOfSentence("asdf" + dot)).isTrue();
		assertThat(WordUtil.isEndOfSentence(dot + "a")).isFalse();
		assertThat(WordUtil.isEndOfSentence(dot + "asdf")).isFalse();
	}
}
