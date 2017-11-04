package testing;

import static org.junit.Assert.*;


import org.junit.Test;

import textSearch.TextSearchConn;
import utils.Constants;
import classes.TextSearchResponse;
import enumerations.JSONClasses;
import json.JSONParsing;

public class GetVersions {

	/**
	 * Test the first 5 versions of preserved web pages
	 */
	@Test
	public void testFirstVersions( ) {
		int numberVersions = 5;
		
		String versionsSTR = TextSearchConn.getVersions( Constants.queryTestSimple , 0 , numberVersions );
		TextSearchResponse versions = (TextSearchResponse) JSONParsing.parsed( versionsSTR, JSONClasses.Response );
		//test if you can find at least the first 5 routes
		assertEquals( numberVersions, versions.getItens( ).size( ) );
	}
	
}
