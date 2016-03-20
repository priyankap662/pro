
package com.priya.intelimentassignment.network;

import com.priya.intelimentassignment.models.Response;
import com.priya.intelimentassignment.util.Constants;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
* 
* This class handles network communication. Creates Http requests adn fetches data
*
* @author Priyanka P
*
* @createdOn Match 20, 2016
* 
*/

public class CommunicationChannel
{
	private static final String TAG = "CommunicationChannel";

	/**
	 * It hits the url and get data using URLConnection. data from web is added in response
	 *
	 * @param requestUrl Fetches data from this url
	 * @return  Response It contains status(failed/success), Message(Reason in case
	 *                   of failure), data
	 *
	 * @author Priyanka P
	 *
	 * @see {@link com.priya.intelimentassignment.models.Response}
	 *
	 * @createdOn March 20, 2016
	 *
	 * @version 1.0
	 * */
	protected Response submit(String requestUrl)
	{
		
		 InputStream in;
		   URL url;
		try {
			url = new URL(requestUrl);
			
			 URLConnection urlConnection = url.openConnection();
			   in = new BufferedInputStream(urlConnection.getInputStream());
			     
				   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

					StringBuffer sb = new StringBuffer("");
						String line = null;

						while ((line = bufferedReader.readLine()) != null)
						{
							sb.append(line);
						}
						bufferedReader.close();

						String responseDataStr = sb.toString();
						//Log.v(TAG, "Response: " + responseDataStr);
						in.close();
						return new Response(true, "", responseDataStr);
			   
			   	    
			  
			
		} catch (MalformedURLException e) {
			return new Response(false, Constants.ErrorMessages.SOME_ERROR_OCCURED, null);
		} catch (IOException e) {
			return new Response(false, Constants.ErrorMessages.SOME_ERROR_OCCURED, null);
		}
	}

}
