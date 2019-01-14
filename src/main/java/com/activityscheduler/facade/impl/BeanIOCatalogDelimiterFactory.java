package com.activityscheduler.facade.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.lang3.StringUtils;
import org.beanio.stream.RecordIOException;
import org.beanio.stream.RecordReader;
import org.beanio.stream.delimited.DelimitedRecordParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.activityscheduler.constant.AppConfiguration;
import com.activityscheduler.constant.ErrorMessages;

/**
 * A custom activity catalog BeanIO Delimiter factory extending
 * {@linkplain DelimitedRecordParserFactory} that creates a Reader to read the
 * file containing list of activities
 * 
 * @author rbutti
 *
 */
public class BeanIOCatalogDelimiterFactory extends DelimitedRecordParserFactory {

	private static Logger logger = LoggerFactory.getLogger(BeanIOCatalogDelimiterFactory.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.beanio.stream.delimited.DelimitedRecordParserFactory#createReader(java.io
	 * .Reader)
	 */
	@Override
	public RecordReader createReader(Reader in) throws IllegalArgumentException {

		if (!(in instanceof BufferedReader)) {
			in = new BufferedReader(in);
		}
		final BufferedReader reader = (BufferedReader) in;
		return new CatalogReader(reader);
	}

	/**
	 * CatalogReader extends BeanIO's {@linkplain RecordReader} to provide logic to
	 * parse the input activities file record to an Activity object
	 * 
	 * @author rbutti
	 *
	 */
	public class CatalogReader implements RecordReader {

		private static final String KEYWORD_FOR_SPRINT = "sprint";
		private static final String KEYWORD_FOR_DURATION = "min";
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
			logger.trace("reading the line : {}", lineNumber);

			// read the line from the file
			recordText = reader.readLine();
			logger.debug("Record text processing : {}", recordText);

			if (StringUtils.isEmpty(recordText)) {
				logger.info("End of the file reached");
				return null;
			}

			String lastWord = recordText.substring(recordText.lastIndexOf(" ") + 1);

			// check if the lastword of the record ends in min or sprint to determine the
			// type of record
			if (lastWord.contains(KEYWORD_FOR_DURATION)) {

				logger.trace("The record contains the duration of the activity");
				readObj[0] = recordText.replace(lastWord, "").trim();
				readObj[1] = lastWord.replace(KEYWORD_FOR_DURATION, "");
			} else if (lastWord.equalsIgnoreCase(KEYWORD_FOR_SPRINT)) {

				logger.trace("The record is a sprint");
				readObj[0] = recordText;
				readObj[1] = AppConfiguration.DEFAULT_SPRINT_DURATION;
			} else {
				throw new IOException(String.format(
						ErrorMessages.INVALID_RECORD_TYPE,
						lineNumber));
			}

			logger.trace("Read Object: {}, {}", readObj[0], readObj[1]);
			return readObj;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.beanio.stream.RecordReader#close()
		 */
		@Override
		public void close() throws IOException {
			reader.close();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.beanio.stream.RecordReader#getRecordLineNumber()
		 */
		@Override
		public int getRecordLineNumber() {
			return lineNumber;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.beanio.stream.RecordReader#getRecordText()
		 */
		@Override
		public String getRecordText() {
			return recordText;
		}
	}
}
