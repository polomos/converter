package com.polomos.io;

import com.polomos.converter.Sentence;

/**
 * Concrete implementation of file writer, which writes {@link Sentence} in csv
 * format.
 * 
 * @author jpolom
 *
 */
public class XmlWriter extends SentenceWriter {

	public static final String XML_SUFFIX = ".xml";

	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<text>\n";
	private static final String XML_FOOTER = "</text>";

	public XmlWriter(final String filePath) {
		super(filePath + XML_SUFFIX);
		write(XML_HEADER);
	}

	@Override
	protected String getFormatedSentence(Sentence toWrite) {
		return toWrite.toXml();
	}

	@Override
	protected void handleEndOfFile() {
		write(XML_FOOTER);
	}

}
