package com.priya.intelimentassignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.priya.intelimentassignment.R;
import com.priya.intelimentassignment.application.implimentation.IOCContainer;
import com.priya.intelimentassignment.application.interfaces.IApplicationFacade;
import com.priya.intelimentassignment.models.LocationDetails;
import com.priya.intelimentassignment.models.Response;
import com.priya.intelimentassignment.network.NetworkUtility;
import com.priya.intelimentassignment.util.ApplicationUtility;
import com.priya.intelimentassignment.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Test2Fragment.OnTest2FragmentInteractionListener} interface
 * to handle interaction events.
 * <p>This fragment displays location details and also open the map with given latitude, longitude<p/>
 *
 * @since 20 March, 2016
 * @author Priyanka P
 */
public class Test2Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private OnTest2FragmentInteractionListener mListener;

    @InjectView(R.id.locationNamesSpn)
    Spinner locationNamesSpn;

    @InjectView(R.id.modeOfTransportTv)
    TextView modeOfTransportTv;

    @InjectView(R.id.navigateBtn)
    Button navigateBtn;
    private ArrayAdapter adapter;

    @InjectView(R.id.modeOfTransportRel)
    RelativeLayout modeOfTransportRel;

    Context context;
    List<LocationDetails> locationDetailsList;

    List<String> locationNamesList;

    LocationDetails selectedLocationDetails;

    View view;

    /* iApplicationFacade handles all the persistant, Network and parsing requests */
    IApplicationFacade iApplicationFacade;


    public Test2Fragment() {

        iApplicationFacade = (IApplicationFacade) IOCContainer.getInstance().getBean(Constants.Beans.APPLICATION_FACADE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test2, container, false);
        ButterKnife.inject(this, view);

        locationNamesSpn.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (NetworkUtility.isConnectedToInternet(context)) {
            new FetchLocationDetailsAsynTask(this).execute();
        } else if (context != null) {
            hideViewsInCaseOfNoData();
            ApplicationUtility.displayErrorMessage(context.getResources().getString(R.string.ERROR_INTERNET_CONNECTION_NOT_AVIALABLE), view);
        }
    }

    private void hideViewsInCaseOfNoData() {
        locationNamesSpn.setVisibility(View.GONE);
        navigateBtn.setVisibility(View.GONE);
        navigateBtn.setVisibility(View.GONE);
        modeOfTransportRel.setVisibility(View.GONE);
    }

    private void showViewsInCaseOfData() {
        locationNamesSpn.setVisibility(View.VISIBLE);
        navigateBtn.setVisibility(View.VISIBLE);
        navigateBtn.setVisibility(View.VISIBLE);
        modeOfTransportRel.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.navigateBtn)
    public void onNavigationToMapButtonPressed() {
        if (selectedLocationDetails != null) {
            if (!ApplicationUtility.isValidString(selectedLocationDetails.getLatitude()) || !ApplicationUtility.isValidString(selectedLocationDetails.getLongitude())) {
                if (context != null && view != null)
                    ApplicationUtility.displayErrorMessage(context.getResources().getString(R.string.LOCATION_LAT_LONG_NOT_AVAILABLE), view);
            } else if (mListener != null) {
                mListener.onNavigateToMapButtonClick(Double.parseDouble(selectedLocationDetails.getLatitude()), Double.parseDouble(selectedLocationDetails.getLongitude()), selectedLocationDetails.getName());
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTest2FragmentInteractionListener) {
            this.context = context;
            mListener = (OnTest2FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        selectedLocationDetails = locationDetailsList.get(position);

        if (selectedLocationDetails != null) {
            HashMap<String, String> modeOfTrasnportationWithRequiedTime = selectedLocationDetails.getModeOfTrasnportationWithRequiedTime();
            printModeOfTransportWithTime(modeOfTrasnportationWithRequiedTime);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*
    * It populates textView with mode of transport data.
    *
    * @param mp It contains mode of tranport in key and Time in value part of HashMap
    * */
    private void printModeOfTransportWithTime(Map mp) {
        StringBuffer modeOfTranportInfo = new StringBuffer();

        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            modeOfTranportInfo.append("  " + pair.getKey() + " = " + pair.getValue()+"\n");
        }

        if (modeOfTranportInfo.length() > 0)
            modeOfTransportTv.setText(modeOfTranportInfo.toString());
        else if (context != null)
            modeOfTransportTv.setText(context.getResources().getString(R.string.DATA_NOT_AVIALABLE));
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnTest2FragmentInteractionListener {
        void onNavigateToMapButtonClick(double latitude, double longitude, String name);
    }

    /*
    * This method populates the Spinner with Location names and visible the UI elements.
    * */
    private void updateLocationNamesSpinner() {
        if (locationNamesList != null && !locationNamesList.isEmpty()) {

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_selectable_list_item, locationNamesList);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            locationNamesSpn.setAdapter(dataAdapter);

            showViewsInCaseOfData();
        }
    }

    /*
    *
    * It populates location data from net in the widgets. It parses locationDetailsList and creates
    * locationNamesList which goes in names Spinner.
    *
    * @param locationResponse It comes from Asyn task which fetches the location data from net
    * */
    void updateLocationDetailsResponse(Response locationResponse) {
        if (locationResponse == null || !locationResponse.isSuccess()) {
            ApplicationUtility.displayErrorMessage(context.getResources().getString(R.string.SOME_ERROR_OCCURED_NETWORK), view);
        } else {
            showViewsInCaseOfData();
            List<LocationDetails> locationDetailsList = iApplicationFacade.parseJson((String) locationResponse.getData());

            this.locationDetailsList = locationDetailsList;

            if (locationNamesList != null) {
                locationNamesList.clear();
            } else {
                locationNamesList = new ArrayList<String>();
            }

            if (locationDetailsList != null && !locationDetailsList.isEmpty()) {
                for (LocationDetails locationDetail : locationDetailsList
                        ) {
                    locationNamesList.add(locationDetail.getName());
                }
            }
            updateLocationNamesSpinner();
        }
    }
}
