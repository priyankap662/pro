package com.priya.intelimentassignment.util;

import android.util.Log;

import com.priya.intelimentassignment.models.LocationDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This is a JsonParser which parse json response from net and store in local objects
 *
 * @author Priyanka P
 *
 * @createdOn 20 March, 2016
 *
 */
public class TestsJsonParser {
    private static String TAG = "TestsJsonParser";
    public List<LocationDetails> parseLocationDetailsJson(String locationDetailsJson) {

        if(locationDetailsJson == null || locationDetailsJson.isEmpty()) return null;

            try {
                List<LocationDetails> locationDetailsList = new ArrayList<LocationDetails>();

                JSONArray locationDetailsJsonArray = new JSONArray(locationDetailsJson);

                for (int i=0; i < locationDetailsJsonArray.length(); i++) {
                    JSONObject locationJson = locationDetailsJsonArray.getJSONObject(i);
                    LocationDetails locationDetail = new LocationDetails();

                    String id = locationJson.getString(Constants.ResponseJsonKeys.ID);
                    String name = locationJson.getString(Constants.ResponseJsonKeys.NAME);

                    JSONObject locationDataObject = locationJson.getJSONObject(Constants.ResponseJsonKeys.LOCATION);
                    String latitude = locationDataObject.getString(Constants.ResponseJsonKeys.LATITUDE);
                    String longitude = locationDataObject.getString(Constants.ResponseJsonKeys.LOGITUDE);

                    JSONObject fromCentralJson = locationJson.getJSONObject(Constants.ResponseJsonKeys.FROMCENTRAL);
                    HashMap<String, String> modeOfTransportWithTimeHashMap = new HashMap<>();
                    parseJsonToHashMap(fromCentralJson, modeOfTransportWithTimeHashMap);

                    locationDetail.setId(id);
                    locationDetail.setName(name);
                    locationDetail.setLatitude(latitude);
                    locationDetail.setLongitude(longitude);
                    locationDetail.setModeOfTrasnportationWithRequiedTime(modeOfTransportWithTimeHashMap);

                    locationDetailsList.add(locationDetail);
                }

                return locationDetailsList;

            } catch (JSONException e) {
                Log.e(TAG,
                        "Failed to create listNewsInformation : " + e.getMessage());
            }
        return null;
    }


    /*
    * This parses Json object of Mode of transport from  location response and convert it to hash map.
    * Key contains mode of transportation and value contains Time.
    * */
    public static Map<String,String> parseJsonToHashMap(JSONObject json , Map<String,String> out) throws JSONException{
        Iterator<String> keys = json.keys();
        while(keys.hasNext()){
            String key = keys.next();
            if(key != null) {
                String val = json.getString(key);
                if (val != null) {
                    out.put(key, val);
                }
            }
        }
        return out;
    }

}
