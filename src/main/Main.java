package main;

import textSearch.TextSearchConn;
import utils.Constants;

import classes.Item;
import classes.TextSearchResponse;
import enumerations.JSONClasses;
import json.JSONParsing;

public class Main {

	public static void main(String[] args) {
		System.out.println( "---> getTitleFirsttVersion() <---" );
		getTitleFirsttVersion( );
		System.out.println( "\n\n---> getFirstVersionEuro() <---" );
		getFirstVersionEuro( );
		System.out.println( "\n\n---> getFiveVersion() <---" );
		getFiveVersion( );
		System.out.println( "\n\n---> getNewestVersion() <---" );
		getNewestVersion( );
		System.out.println( "\n\n---> getJSONResponse() <---" );
		getJSONResponse( );
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
	 * Get the most recent item from 2005 with the terms "Edith Piaf"
	 */
	private static void getNewestVersion( ){
		int limit = 1;
		String from = "2005";
		String to = "2005";
		String order = "new";
		String versionsSTR = TextSearchConn.getVersions( Constants.queryTestNewest , 0 , limit , from , to , order );
		TextSearchResponse versions = (TextSearchResponse) JSONParsing.parsed( versionsSTR, JSONClasses.Response ); 
		
		if( versions == null || versions.getItens() == null || versions.getItens( ).size( ) == 0 )
			System.out.println( "Could not find versions for search." );
		else
			System.out.println( "Source:" + versions.getItens().get( 0 ).getSource( ) + "  Timestamp:" + versions.getItens().get( 0 ).getTstamp( ) );

	}
	
	/**
	 * Gets the json of the API response for the query "Fernando Pessoa"
	 */
	private static void getJSONResponse( ){
		int limit = 10;
		boolean prettyPrint = true;
		String versionsSTR = TextSearchConn.getVersions( Constants.queryTestJSON , 0 , limit , prettyPrint );
		
		System.out.println( versionsSTR );
		
	}
	
}
