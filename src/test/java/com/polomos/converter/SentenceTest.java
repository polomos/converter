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
		final Sentence s1 = new Sentence();
		s1.addWord("ala");
		s1.addWord("ma");
		s1.addWord("kota");

		final Sentence s2 = new Sentence();
		s2.addWord("ala");
		s2.addWord("ma");
		s2.addWord("kota");

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
}
