package json;

import classes.TextSearchResponse;

import com.google.gson.*;

import enumerations.JSONClasses;

public class JSONParsing {

	public static Object parsed( String toParse, JSONClasses classe ) {
		Gson gson = new GsonBuilder( ).create( );
		
		if( toParse == null || checkErrors( toParse ) )
			return null;
		
		TextSearchResponse requestParameters = null;
	
		try {
			if ( classe == JSONClasses.Response ) {
				requestParameters = gson.fromJson(toParse, TextSearchResponse.class);
				return requestParameters;
			} else {
				requestParameters = null;
			}
		} catch (JsonSyntaxException e) {
			System.err.println("JSON contains syntax errors");
		}
		return requestParameters;
	}

	private static boolean checkErrors( String toParse ) {
		
		String archiveError = "The arquivo.pt server returned the response: ", endError = ".\nTry again later.";
		
		String err403 = "You don't have permission to access this resource",
				err404 = "The page you are looking for doesn't exist",
				err500 = "We are sorry, but something went wrong",
				err502 = "Bad Gateway",
				err503 = "We are currently performing scheduled maintenance";
		
		if ( toParse.contains( err403 ) ) {
			System.err.println ( archiveError + err403 + endError );
			return true;
		} else if ( toParse.contains( err404 ) ) {
			System.err.println( archiveError + err404 + endError );
			return true;
		} else if ( toParse.contains( err500 ) ) {	
			System.err.println( archiveError + err500 + endError );
			return true;
		} else if ( toParse.contains( err502 ) ) {	
			System.err.println( archiveError + err502 + endError );
			return true;
		} else if ( toParse.contains( err503 ) ) {	
			System.err.println( archiveError + err503 + endError );
			return true;
		} 
		
		return false;
	}
	
}
