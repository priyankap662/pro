package com.priya.intelimentassignment.util;

/**
 * This class contains App constant values
 *
 * @author Priyanka P
 *
 * @createdOn March 20, 2016
 */
public class Constants {


    public interface ErrorMessages
    {
        String SOME_ERROR_OCCURED = "Opps!!! Some error occured. Kindly check the network conneection and try again";
        String URL_NOT_PRESENT = "No more information available";
    }
    public interface ResponseJsonKeys
    {
        String ID = "id";
        String NAME = "name";
        String FROMCENTRAL = "fromcentral";
        String LOCATION = "location";
        String LATITUDE = "latitude";
        String LOGITUDE = "longitude";
        String RESPONSE_DATA = "responseData";
    }

    public interface Beans
    {
        public static final String APPLICATION_FACADE = "applicationFacade";

        public static final String APPLICATION_COMMUNICATION_SERVICE = "applicationCommunicationService";
    }


}
