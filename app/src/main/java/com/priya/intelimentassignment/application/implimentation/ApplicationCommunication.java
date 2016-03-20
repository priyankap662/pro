package com.priya.intelimentassignment.application.implimentation;

import android.content.Context;

import com.priya.intelimentassignment.application.interfaces.IApplicationCommunicationService;
import com.priya.intelimentassignment.models.Response;
import com.priya.intelimentassignment.network.CommunicationChannel;


/**
 * This class handles all the network related requests
 *
 * @author Priyanka P
 * @createdOn March 20, 2016
 * @modifiedOn
 * @see IApplicationCommunicationService
 */
public class ApplicationCommunication extends CommunicationChannel implements IApplicationCommunicationService {

    private static final String TAG = "ApplicationCommunication";

    private Context context;

    private static ApplicationCommunication instance;

    public ApplicationCommunication(Context context) {
        this.context = context;
    }

    public static ApplicationCommunication getInstance(Context context) {
        if (instance == null) {
            instance = new ApplicationCommunication(context);
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     * {@link IApplicationCommunicationService#fetchLatesNews(java.lang.String)}
     */
    @Override
    public Response fetchLatesNews(String requestUrl) {
        return submit(requestUrl);
    }



}
