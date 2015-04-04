package com.pasdam.opensearch.test;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.pasdam.opensearch.description.OpenSearchDescription;

public class Test {
	
	// TODO Use JUnit framework for testing
	// TODO Add more tests

	private static final String FOLDER_NAME = "descriptions";
	private static final String SCHEMA_FILE = "OpenSearch.xsd";

	private static final String[] DESCRIPTIONS = { "wikipedia" };

	public static void main(String[] args) {
		String filePath;

		for (String name : DESCRIPTIONS) {
			filePath = FOLDER_NAME + File.separator + name + ".xml";

			System.out.println("Reading file: " + filePath);
			
			// validate the description file
/*
 * TODO Find a valid xml schema for OpenSearch 1.1 specifications and use it instead of the one in the "descriptions" directory to validate the input descriptions
			try {
				validate(filePath);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
*/

			// Simply print the parsed opensearch xml description
			System.out.println(OpenSearchDescription.parse(new File(filePath))
					.toString());

			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

	/**
	 * @param filePath
	 *            - A pathname string
	 * @throws SAXException
	 *             - If a SAX error occurs during parsing
	 * @throws IOException
	 *             - If the validator is processing a SAXSource and the
	 *             underlying XMLReader throws an IOException.
	 */
	@SuppressWarnings("unused")
	private static void validate(String filePath) throws SAXException,
			IOException {
		// create schema by from an XSD file:
		String schemaLang = "http://www.w3.org/2001/XMLSchema";
		SchemaFactory jaxp = SchemaFactory.newInstance(schemaLang);
		Schema schema = jaxp.newSchema(new StreamSource(FOLDER_NAME + File.separator + SCHEMA_FILE));
		// prepare document validator:
		Validator validator = schema.newValidator();
		// prepare SAX handler and SAX result receiving validate data:
		SAXResult sax = new SAXResult(new DefaultHandler());
		// at last send valid data to out SAX handler:
		SAXSource source = new SAXSource(new InputSource(filePath));
		validator.validate(source, sax);
	}
}
