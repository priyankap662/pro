package com.priya.intelimentassignment.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.priya.intelimentassignment.R;
import com.priya.intelimentassignment.util.ApplicationUtility;

/**
 * This fragment is used in view pager in Test1
 * To instantiate this fragment, public default constructor can be used.
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {


    public ViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_pager_fragment, container, false);

        int number = 0;
        String numberStr = getArguments().getString("number");
        if(numberStr!=null && !numberStr.isEmpty()) number = Integer.parseInt(numberStr);

        TextView tv = (TextView) v.findViewById(R.id.tvFragmentNumber);
        tv.setText("Fragment "+numberStr);

        Button btnInFragment = (Button) v.findViewById(R.id.clickMeButton);
        btnInFragment.setTag(numberStr);
        btnInFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                String num = (String)v.getTag();
                if(activity != null) {
                    ApplicationUtility.displayErrorMessage("Fragment "+num+" Clicked", v);
                }
            }
        });

        return v;
    }

    public static ViewPagerFragment newInstance(String number) {

        ViewPagerFragment f = new ViewPagerFragment();
        Bundle b = new Bundle();
        b.putString("number", number);

        f.setArguments(b);

        return f;
    }
}
