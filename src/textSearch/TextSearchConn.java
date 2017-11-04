package textSearch;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import rest.RestClient;
import utils.Constants;

public class TextSearchConn {
	
	/* 	offset - obtem a partir do offset
	 * 	results - n de resultados obtidos */

	/**
	 * Get the *limit* results for the query *q*	
	 * @param q
	 * @param offset
	 * @param limit
	 * @return
	 */
	public static String getVersions( String q, int offset, int limit ){
		String encodeQuery;
		try {
			encodeQuery = URLEncoder.encode( q, "UTF-8" ).replace( "+", "%20" );
		} catch (UnsupportedEncodingException e) {
			System.out.println( "Unsupported Encoding" );
			return null;
		}
		String response = TextSearchClient.executeHttpGet( Constants.API + "?q=" + encodeQuery + "&limit=" + limit + "&offset=" + offset );
		return response;
	}
	
	
	
	/**
	 * Used to get a response to a given URL
	 * @param url
	 * @return
	 */
	public static String getCustomUrl(String url) {
		String response = RestClient.executeHttpGet(Constants.API + url);				
		return response;
	}
	
}
