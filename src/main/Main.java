package main;

import textSearch.TextSearchConn;
import utils.Constants;
import classes.TextSearchResponse;
import enumerations.JSONClasses;
import json.JSONParsing;

public class Main {

	public static void main(String[] args) {
		getVersions( );
	}
	
	/**
	 * Get 100 results for query q, with offset to 0
	 */
	private static void getVersions( ){
		int limit = 100;
		
		String versionsSTR = TextSearchConn.getVersions( Constants.queryTestSimple , 0 , limit );
		TextSearchResponse versions = (TextSearchResponse) JSONParsing.parsed( versionsSTR, JSONClasses.Response ); 
		
		if( versions == null || versions.getItens() == null || versions.getItens( ).size( ) == 0 )
			System.out.println( "" );
		else
			System.out.println( versions.getItens().get( 0 ).getTitle( ) );
		
	}
	

}
