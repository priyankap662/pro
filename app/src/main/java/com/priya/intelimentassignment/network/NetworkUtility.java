package com.priya.intelimentassignment.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

/**
*
* This class provides Network related functionalities
* like checking current network status
 *
* @author Priyanka P
*
* @createdOn March 19, 2016
 *
*/
public class NetworkUtility
{
	
	private static final String TAG = "Netowrk Utility";

	/**
	 * It Checks the network connection of the device.
	 *
	 * @param context current context to get Connectivity manager
	 * @return boolean It return true if network is available else returns false
	 *
	 * @author Priyanka P
	 *
	 * @see {@link ConnectivityManager}
	 *
	 * @createdOn March 19,2016
	 *
	 * @version 1.0
	 * */
	public static boolean isConnectedToInternet(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			Network[] info = connectivity.getAllNetworks();
			NetworkInfo networkInfo = null;
			if (info != null)
				for (int i = 0; i < info.length; i++)
					networkInfo = connectivity.getNetworkInfo(info[i]);
					if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
						Log.v(TAG, "isConnencted to internet = true");
						return true;
					}

		}
		Log.e(TAG, "isConnencted to internet = false;");
		return false;
	}



}
