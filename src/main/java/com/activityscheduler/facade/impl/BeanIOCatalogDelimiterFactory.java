package com.activityscheduler.facade.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.lang3.StringUtils;
import org.beanio.stream.RecordIOException;
import org.beanio.stream.RecordReader;
import org.beanio.stream.delimited.DelimitedRecordParserFactory;

public class BeanIOCatalogDelimiterFactory extends DelimitedRecordParserFactory {

	@Override
	public RecordReader createReader(Reader in) throws IllegalArgumentException {

		if (!(in instanceof BufferedReader)) {
			in = new BufferedReader(in);
		}
		final BufferedReader reader = (BufferedReader) in;
		return new CatalogReader(reader);
	}

	public class CatalogReader implements RecordReader {

		private String recordText;
		private int lineNumber;
		private BufferedReader reader;

		public CatalogReader(BufferedReader reader) {
			this.reader = reader;
		}

		@Override
		public Object read() throws IOException, RecordIOException {

			String[] readObj = new String[2];
			++lineNumber;
			recordText = reader.readLine();

			if (StringUtils.isEmpty(recordText)) {
				return null;
			}

			String lastWord = recordText.substring(recordText.lastIndexOf(" ") + 1);

			if (lastWord.contains("min")) {
				readObj[0] = recordText.replace(lastWord, "").trim();
				readObj[1] = lastWord.replace("min", "");
			} else if (lastWord.equalsIgnoreCase("sprint")) {
				readObj[0] = recordText;
				readObj[1] = "15";
			} else {
				throw new IOException(String.format(
						"Invalid record formed in the input at line : %d Input should either end with min or sprint keyword",
						lineNumber));
			}
			return readObj;
		}

		@Override
		public void close() throws IOException {
			reader.close();
		}

		@Override
		public int getRecordLineNumber() {
			return lineNumber;
		}

		@Override
		public String getRecordText() {
			return recordText;
		}
	}
}
