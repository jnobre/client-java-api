package textSearch;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import utils.Constants;

public class TextSearchConn {

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
		String response = TextSearchClient.executeHttpGet( Constants.API + "?q=" + encodeQuery + "&maxItems=" + limit + "&offset=" + offset );
		return response;
	}
	
	/**
	 * Get the *limit* results for the query *q*, between the *from* and the *to* 
	 * @param q
	 * @param offset
	 * @param limit
	 * @param from
	 * @param to
	 * @return
	 */
	public static String getVersions( String q, int offset, int limit, String from, String to ){
		String encodeQuery;
		try {
			encodeQuery = URLEncoder.encode( q, "UTF-8" ).replace( "+", "%20" );
		} catch (UnsupportedEncodingException e) {
			System.out.println( "Unsupported Encoding" );
			return null;
		}
		String response = TextSearchClient.executeHttpGet( Constants.API + "?q=" + encodeQuery + "&maxItems=" + limit + "&offset=" + offset + "&from=" + from + "&to=" + to );
		return response;
	}
	

	
	/**
	 * Get json
	 * @param q
	 * @param offset
	 * @param limit
	 * @param prettyPrint
	 * @return
	 */
	public static String getVersions( String q, int offset, int limit , boolean prettyPrint ){
		String encodeQuery;
		try {
			encodeQuery = URLEncoder.encode( q, "UTF-8" ).replace( "+", "%20" );
		} catch (UnsupportedEncodingException e) {
			System.out.println( "Unsupported Encoding" );
			return null;
		}
		String response = TextSearchClient.executeHttpGet( Constants.API + "?q=" + encodeQuery + "&maxItems=" + limit + "&offset=" + offset + "&prettyPrint=" + prettyPrint );
		return response;
	}
	
	/**
	 * Get URL versions 
	 * @param versionHistory
	 * @param offset
	 * @param limit
	 * @param prettyPrint
	 * @return
	 */
	public static String getURLVersions( String versionHistory, int offset, int limit , boolean prettyPrint ){
		String encodeQuery;
		try {
			encodeQuery = URLEncoder.encode( versionHistory, "UTF-8" ).replace( "+", "%20" );
		} catch (UnsupportedEncodingException e) {
			System.out.println( "Unsupported Encoding" );
			return null;
		}
		String response = TextSearchClient.executeHttpGet( Constants.API + "?versionHistory=" + encodeQuery + "&maxItems=" + limit + "&offset=" + offset + "&prettyPrint=" + prettyPrint );
		return response;
	}
	
	/**
	 * Get metadata info
	 * @param metadata
	 * @param prettyPrint
	 * @return
	 */
	public static String getMetadataInfo( String metadata , boolean prettyPrint ){
		String encodeQuery;
		try {
			encodeQuery = URLEncoder.encode( metadata, "UTF-8" ).replace( "+", "%20" );
		} catch ( UnsupportedEncodingException e ) {
			System.out.println( "Unsupported Encoding" );
			return null;
		}
		String response = TextSearchClient.executeHttpGet( Constants.API + "?metadata=" + encodeQuery + "&prettyPrint=" + prettyPrint );
		return response;
	}
	
	/**
	 * Used to get a response to a given URL
	 * @param url
	 * @return
	 */
	public static String getCustomUrl(String url) {
		String response = TextSearchClient.executeHttpGet(Constants.API + url);				
		return response;
	}
	
}
