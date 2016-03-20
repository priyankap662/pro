package com.priya.intelimentassignment.application.implimentation;


import com.priya.intelimentassignment.application.interfaces.IApplicationCommunicationService;
import com.priya.intelimentassignment.application.interfaces.IApplicationFacade;
import com.priya.intelimentassignment.models.LocationDetails;
import com.priya.intelimentassignment.models.Response;
import com.priya.intelimentassignment.util.TestsJsonParser;

import java.util.List;



/**
 *
 * This is a Facade class. Every request comes to this class first and it call the appropriate method
 * from communication (/ persistance) classes.
 *
 * @author Priyanka P
 *
 * @createdOn March 20, 2016
 *
 */
public class ApplicationFacade implements IApplicationFacade {

	private IApplicationCommunicationService applicationCommunicationService;

	public static ApplicationFacade instance;

	private ApplicationFacade(
			IApplicationCommunicationService applicationCommunicationService) {
		this.applicationCommunicationService = applicationCommunicationService;
	}

	public synchronized static IApplicationFacade getInstance(
			IApplicationCommunicationService applicationCommunicationService) {
		if (instance == null) {
			instance = new ApplicationFacade(
					applicationCommunicationService);
		}
		return instance;
	}


	/**
	 * {@inheritDoc}
	 * {@link IApplicationFacade#getLatesNewsFromUrl(String)}
	 */
	@Override
	public Response getLatesNewsFromUrl(
			String urlForLatestNews) {

		Response reponse = applicationCommunicationService
				.fetchLatesNews(urlForLatestNews);
		return reponse;

	}

	/**
	 * {@inheritDoc}
	 * {@link IApplicationFacade#parseJson(String)}
	 */
	@Override
	public List<LocationDetails> parseJson(String locationDetailsJson) {
		return new TestsJsonParser().parseLocationDetailsJson(locationDetailsJson);
	}
}
