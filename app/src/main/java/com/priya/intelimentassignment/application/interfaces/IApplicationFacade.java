package com.priya.intelimentassignment.application.interfaces;


import com.priya.intelimentassignment.models.LocationDetails;
import com.priya.intelimentassignment.models.Response;

import java.util.List;

/**
* 
* This is a Facade interface. Every request comes to this and and it call the appropriate method
 * from communication / persistence classes.
*
* @author Priyanka P
*
* @createdOn 20 March, 2016
*
*/
public interface IApplicationFacade
{

	/**
	 * It downloads the image from the URL given in the parameter.
	 *
	 * @param urlForLatestNews url to download data from web
	 * @return Response It contains status(failed/success), Message(Reason in case
	 *                   of failure), data
	 *
	 * @author Priyanka P
	 *
	 * @createdOn March 20, 2016
	 *
	 * @version 1.0
	 * */
	Response getLatesNewsFromUrl(String urlForLatestNews);

	/**
	 * It parses the response from web and convert it to List of news objects
	 *
	 * @param locationDetailsJson Json in string from to be parsed
	 * @return List<LocationDetails> returns parsed information in List
	 *
	 *
	 * @see LocationDetails
	 * @author Priyanka P
	 * @createdOn
	 * @version 1.0
	 * */
	List<LocationDetails> parseJson(String locationDetailsJson);


}
