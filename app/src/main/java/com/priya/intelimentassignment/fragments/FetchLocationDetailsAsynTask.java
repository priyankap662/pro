package com.priya.intelimentassignment.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.priya.intelimentassignment.R;
import com.priya.intelimentassignment.application.implimentation.IOCContainer;
import com.priya.intelimentassignment.application.interfaces.IApplicationFacade;
import com.priya.intelimentassignment.models.Response;
import com.priya.intelimentassignment.util.Constants;


/**
 * This Async task hits the url to fetch recent location details from the net.
 * doInBackground returns Response object which contains Status, Data and
 * message in case of failure.
 * onPostExecute reponse data is updated to respective fragment.
 *
 * @createdOn March 20, 2016
 */
public class FetchLocationDetailsAsynTask extends AsyncTask<Void, Void, Response> {

    /* Tag for logging */
    private static String TAG = "NewsListActivity";

    /* Holds current fragment context*/
    private static Test2Fragment context;

    /* iApplicationFacade handles all the persistant, Network and parsing requests */
    IApplicationFacade iApplicationFacade;

    private static final String LOCATION_DETAILS_URL = "http://express-it.optusnet.com.au/sample.json";
    private ProgressDialog progressDialog;

    FetchLocationDetailsAsynTask(Test2Fragment contextFragment) {
        this.context= contextFragment;
        iApplicationFacade = (IApplicationFacade) IOCContainer.getInstance().getBean(Constants.Beans.APPLICATION_FACADE);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context.getActivity());
        progressDialog.setMessage(context.getResources().getString(R.string.fetching_location_data));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Response doInBackground(Void... params) {

        Response locationResponse = iApplicationFacade
                .getLatesNewsFromUrl(LOCATION_DETAILS_URL);
        Log.v(TAG, "latestNewsResponse = " + locationResponse);
        return locationResponse;
    }

    @Override
    protected void onPostExecute(Response locationResponse) {
        super.onPostExecute(locationResponse);
        progressDialog.dismiss();

            if(context != null)
            ((Test2Fragment) context).updateLocationDetailsResponse(locationResponse);
    }
    }
