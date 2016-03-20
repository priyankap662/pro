package com.priya.intelimentassignment.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * This class gives basic functionality to the app.
 *
 * Created by Priyanka on 20-03-2016.
 */
public class ApplicationUtility {

    public static boolean isValidString(String str) {
        if (str != null && !str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void displayErrorMessage(String message, View view)
    {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
    }
}
