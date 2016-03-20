package com.priya.intelimentassignment.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.priya.intelimentassignment.R;
import com.priya.intelimentassignment.adapter.ViewPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * This fragment demostrates multiple UI widgets. UI of this fragment will work on any phone or tablet.
 * To instantiate this fragment, public default constructor can be used.
 */
public class Test1Fragment extends Fragment {

    @InjectView(R.id.horizontalScrollHolder)
    LinearLayout horizontalScrollHolder;

    @InjectView(R.id.pager)
    ViewPager pager;

    @InjectView(R.id.textViewForSelectedItemInGroup1)
    TextView textViewForSelectedItemInGroup1;

    @InjectView(R.id.redbutton)
    Button redButton;
    @InjectView(R.id.bluebutton)
    Button bluebutton;
    @InjectView(R.id.greenbutton)
    Button greenbutton;

    @InjectView(R.id.uitest_page)
    View uiTestView;

    @InjectView(R.id.pagerIndicator)
    CirclePageIndicator pagerIndicator;

    private Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test1, container, false);
        ButterKnife.inject(this, view);
        populateHorizontalScrollWithItems();
        initializeViewPager();
        return view;
    }

    private void initializeViewPager() {
        pager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        pagerIndicator.setViewPager(pager);
    }

    private void populateHorizontalScrollWithItems() {

        horizontalScrollHolder.removeAllViews();
        for (int i = 0; i < 5; i++) {
            TextView textForHorizintalScroll = getHorizontalScrollItem();
            textForHorizintalScroll.setText("Item " + (i+1));
            textForHorizintalScroll.setClickable(true);
            textForHorizintalScroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String selectedText = ((TextView) v).getText().toString();
                    textViewForSelectedItemInGroup1.setText(selectedText);
                }
            });
            horizontalScrollHolder.addView(textForHorizintalScroll);
        }
    }

    /*
    * @return TextView The item which gets populate in horizontal scroll
    * */
    @NonNull
    private TextView getHorizontalScrollItem() {
        TextView textForHorizintalScroll = new TextView(context);
        textForHorizintalScroll.setGravity(Gravity.CENTER);
        int itemDimension = (int) getResources().getDimension(R.dimen.horizontal_scroll_item_dimen);

        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(itemDimension, itemDimension);
        layoutParam.setMargins(5, 5, 5, 5);
        textForHorizintalScroll.setLayoutParams(layoutParam);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textForHorizintalScroll.setBackgroundColor(getResources().getColor(android.R.color.white, null));
            textForHorizintalScroll.setTextAppearance(R.style.MediamText);
        }
        else
        {
            textForHorizintalScroll.setBackgroundColor(getResources().getColor(android.R.color.white));
            textForHorizintalScroll.setTextAppearance(context,R.style.MediamText);
        }
        textForHorizintalScroll.setTextColor(getResources().getColor(R.color.colorAccent));

        return textForHorizintalScroll;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.greenbutton)
    public void greenButtonClicked()
    {
        uiTestView.setBackgroundResource(android.R.color.holo_green_dark);
    }

    @OnClick(R.id.bluebutton)
    public void blueButtonClicked()
    {
        uiTestView.setBackgroundResource(android.R.color.holo_blue_dark);
    }

    @OnClick(R.id.redbutton)
    public void redButtonClicked()
    {
        uiTestView.setBackgroundResource(android.R.color.holo_red_dark);
    }

}
