package com.priya.intelimentassignment.application.interfaces;


import com.priya.intelimentassignment.models.Response;

/**
* 
* This interface contains all the method to access network information for the application
*
* @author Priyanka P
* @see 
*
* @createdOn 20 March, 2016
*/
public interface IApplicationCommunicationService
{
	final String NETWORK_FAILED = "Network Connection Failure";
	final String SOME_ERROR_OCCURED  = "Some Error Occured";

	/**
	 * It fetches data from given Url
	 *
	 * @param requestUrl url to download data from net
	 * @return Response It contains status(failed/success), Message(Reason in case
	 *                   of failure), data
	 *
	 * @author Priyanka P
	 *
	 * @createdOn March 20, 2016
	 *
	 * @version 1.0
	 * */
	public Response fetchLatesNews(String requestUrl);

}
