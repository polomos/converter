package com.polomos.io;

import com.polomos.converter.Sentence;

public class XmlWriter extends BufferedFileWriter {

	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<text>\n";
	private static final String XML_FOOTER = "</text>";
	private static final String XML_SUFFIX = ".xml";

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
