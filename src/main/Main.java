package main;

import textSearch.TextSearchConn;
import utils.Constants;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import classes.Item;
import classes.TextSearchResponse;
import enumerations.JSONClasses;
import json.JSONParsing;

public class Main {
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main( String[ ] args ) {
		System.out.println( "---> getTitleFirsttVersion() <---" );
		getTitleFirsttVersion( );
		System.out.println( "\n\n---> getFirstVersionEuro() <---" );
		getFirstVersionEuro( );
		System.out.println( "\n\n---> getFiveVersion() <---" );
		getFiveVersion( );
		System.out.println( "\n\n---> getJSONResponse() <---" );
		getJSONResponse( );
		System.out.println( "\n\n---> getURLVersions() <---" );
		getURLVersions( );
		System.out.println( "\n\n---> getTextExtracted() <---" );
		getTextExtracted( );
		System.out.println( "\n\n---> getTotalVersion() <---" );
		getTotalVersion( );
	}
	
	/**
	 * Get 100 results for query q, with offset to 0
	 */
	private static void getTitleFirsttVersion( ){
		int limit = 100;
		
		String versionsSTR = TextSearchConn.getVersions( Constants.queryTestSimple , 0 , limit );
		TextSearchResponse versions = (TextSearchResponse) JSONParsing.parsed( versionsSTR, JSONClasses.Response ); 
		
		if( versions == null || versions.getItens() == null || versions.getItens( ).size( ) == 0 )
			System.out.println( "Could not find versions for search." );
		else
			System.out.println( "Title: " + versions.getItens().get( 0 ).getTitle( ) );
		
	}
	
	
	/**
	 * Get the first search result "euro 2004 -moeda" 
	 * and return the source and timestamp of this version
	 */
	private static void getFirstVersionEuro( ){
		int limit = 1;
		String from = "20031231235959";
		String to = "20060101000000";
		String versionsSTR = TextSearchConn.getVersions( Constants.queryTestNeg , 0 , limit, from , to );
		TextSearchResponse versions = (TextSearchResponse) JSONParsing.parsed( versionsSTR, JSONClasses.Response ); 
		
		if( versions == null || versions.getItens() == null || versions.getItens( ).size( ) == 0 )
			System.out.println( "Could not find versions for search." );
		else
			System.out.println( "Source:" + versions.getItens().get( 0 ).getSource( ) + "  Timestamp:" + versions.getItens().get( 0 ).getTstamp( ) );
	}

	
	/**
	 * Get the first 5 search results for "Stephen Hawking" from 2005 and 2010.
	 */
	private static void getFiveVersion( ){
		int limit = 5;
		String from = "2005";
		String to = "2010";
		String versionsSTR = TextSearchConn.getVersions( Constants.queryTestExpression , 0 , limit, from , to );
		TextSearchResponse versions = (TextSearchResponse) JSONParsing.parsed( versionsSTR, JSONClasses.Response ); 
		
		for( Item version : versions.getItens( ) ) {
			System.out.println( "Source:" + version.getSource( ) + "  Timestamp:" + version.getTstamp( ) +  " Title:" + version.getTitle( ) + " Encoding:" + version.getEncoding( ) );
		}
		
	}
	
	/**
	 * Get first 30 Timestamp version of the http://www.expresso.pt URL
	 */
	private static void getURLVersions( ){
		int limit = 40;
		String versionsSTR = TextSearchConn.getURLVersions( Constants.queryURLSearch ,  0 , limit, false);
		TextSearchResponse versions = (TextSearchResponse) JSONParsing.parsed( versionsSTR, JSONClasses.Response );
		
		for( Item version : versions.getItens( ) ) {
			System.out.println( "Timestamp:" + version.getTstamp( ) +  " Title:" + version.getTitle( ) );
		}
	}
	
	/**
	 * Get the text extracted from the site rr.pt in the version of 20040627082547
	 */
	private static void getTextExtracted( ) {
		String versionsSTR = TextSearchConn.getMetadataInfo( Constants.queryTextExtracted , false);
		TextSearchResponse versions = (TextSearchResponse) JSONParsing.parsed( versionsSTR, JSONClasses.Response );
		
		try {
			URL url = new URL( versions.getItens( ).get( 0 ).getParseText( ) );
			Scanner sc = new Scanner( url.openStream( ), "UTF-8" ); // read from your scanner
			while ( sc.hasNextLine( ) ) {
				String line = sc.nextLine( );
				System.out.println( line );
			}
		} catch( IOException ex ) {
		   // there was some connection problem, or the file did not exist on the server,
		   // or URL was not in the right format.
		   ex.printStackTrace( ); // for now, simply output it.
		}
		
	}
	
	/**
	 * Get total preserved versions of the web page expresso.pt
	 */
	private static void getTotalVersion( ){
		int limit = 1;
		String versionsSTR = TextSearchConn.getURLVersions( Constants.queryURLSearch ,  0 , limit, false);
		TextSearchResponse versions = (TextSearchResponse) JSONParsing.parsed( versionsSTR, JSONClasses.Response );
		
		System.out.println( "Total Items:" + versions.getTotalItems( ) );
	}
	
	/**
	 * Gets the json of the API response for the query "Fernando Pessoa"
	 */
	private static void getJSONResponse( ){
		int limit = 10;
		boolean prettyPrint = true;
		String versionsSTR = TextSearchConn.getVersions( Constants.queryTestJSON , 0 , limit , prettyPrint );
		if( versionsSTR == null || versionsSTR.equals( "" ) )
			System.out.println( "Could not find versions for search." );
		else
			System.out.println( versionsSTR );
	}
	
}
