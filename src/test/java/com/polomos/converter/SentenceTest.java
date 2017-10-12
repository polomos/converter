package com.polomos.converter;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Test class for {@link Sentence}
 * 
 * @author jpolom
 *
 */
public final class SentenceTest {

	@Test
	public void testCompare() {
		final Sentence s1 = createSentence();

		final Sentence s2 = createSentence();

		assertThat(s1).isEqualTo(s2);
		assertThat(s1.hashCode()).isEqualTo(s2.hashCode());
		s2.addWord("i");
		s2.addWord("psa");

		assertThat(s1).isNotEqualTo(s2);
		assertThat(s1.hashCode()).isNotEqualTo(s2.hashCode());

		s2.startNewSentence();
		s2.addWord("ala");
		s2.addWord("ma");
		s2.addWord("kota");
		assertThat(s1).isNotEqualTo(s2);
		assertThat(s1.hashCode()).isNotEqualTo(s2.hashCode());
	}

	@Test
	public void testCreateNewSentence() {

		final Sentence s = new Sentence();
		s.addWord("ala");

		assertThat(s.getLenght()).isEqualTo(1);
		assertThat(s.getSentenceNo()).isEqualTo(1);

		s.startNewSentence();
		assertThat(s.getLenght()).isEqualTo(0);
		assertThat(s.getSentenceNo()).isEqualTo(2);
	}

	@Test
	public void testSort() {
		final Sentence s = createSentence();
		assertThat(s.toString()).isEqualTo("Sentence{sentenceNo=1, words=[ala, ma, kota]}");

		s.sortSentence();
		assertThat(s.toString()).isEqualTo("Sentence{sentenceNo=1, words=[ala, kota, ma]}");
	}

	@Test
	public void testToCsv() {
		final Sentence s = createSentence();
		assertThat(s.toCsv()).isEqualTo("Sentence 1, ala, ma, kota");
	}

	@Test
	public void testToXml() {
		final Sentence s = createSentence();
		assertThat(s.toXml()).isEqualTo("<sentence><word>ala</word><word>ma</word><word>kota</word></sentence>");
	}

	@Test
	public void testIsNotEmpty() {
		final Sentence s = createSentence();
		assertThat(s.isNotEmpty()).isTrue();
		s.startNewSentence();
		assertThat(s.isNotEmpty()).isFalse();

	}

	private Sentence createSentence() {
		final Sentence s1 = new Sentence();
		s1.addWord("ala");
		s1.addWord("ma");
		s1.addWord("kota");
		return s1;
	}

}
