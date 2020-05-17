package tp.pr1.util;

import java.io.BufferedReader;
import java.io.IOException;

import tp.p1.Exceptions.FileContentsException;

public class LoadLine {
	public static final String wrongPrefixMsg  = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";
	
	public static String[] loadLine(BufferedReader inStream, String prefix, boolean isList) throws IOException, FileContentsException
	{
		String line = inStream.readLine().trim();
	// absence of the prefix is invalid
	if ( ! line . startsWith(prefix + ":") )
		throw new FileContentsException(wrongPrefixMsg + prefix);
	// cut the prefix and the following colon off the line
	// then trim it to get the attribute contents
	String contentString = line. substring(prefix . length()+1).trim();
	String[]  words;
	// the attribute contents are not empty
	if (! contentString.equals("")) {
	if(! isList ) {

	words = contentString.split("\\s+");

	if(words.length != 1)
	throw new FileContentsException(lineTooLongMsg + prefix);
	}
	else
		words = contentString.split(",\\s*");
	// the attribute contents are empty
	}
	else
	{
	if
	(! isList )
	throw new
		FileContentsException(lineTooShortMsg + prefix);
	words =new String[0];
	}
	return words;
	}
	
}
