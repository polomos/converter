package com.polomos.io;

import com.polomos.converter.Sentence;

public class XmlWriter extends BufferedFileWriter {

	private static final String XML_SUFFIX = ".xml";

	public XmlWriter(final String filePath) {
		super(filePath + XML_SUFFIX);
	}

	@Override
	protected String getFormatedSentence(Sentence toWrite) {
		return toWrite.toXml();
	}

}
