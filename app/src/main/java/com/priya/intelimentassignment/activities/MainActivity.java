package com.priya.intelimentassignment.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.priya.intelimentassignment.R;
import com.priya.intelimentassignment.fragments.Test1Fragment;
import com.priya.intelimentassignment.fragments.Test2Fragment;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link AppCompatActivity} subclass.
 * This is the launcher activity which contains sliding menu bar which displays different
 * fragment on its selection.
 *
 * <p>This fragment displays location details and also open the map with given latitude, longitude<p/>
 *
 * @since 19 March, 2016
 * @author Priyanka P
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Test2Fragment.OnTest2FragmentInteractionListener {

    @InjectView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @InjectView(R.id.nav_view)
    NavigationView nav_view;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    int currentFragmentId;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
            currentFragmentId = savedInstanceState.getInt("currentFragmentId");
        }else {
            currentFragment =  new Test1Fragment();
            currentFragmentId = R.id.nav_uitest;
             FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
             Log.v("","ft="+ft+", currentFragment="+currentFragment);
             ft.add(R.id.fragmentContainer, currentFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "currentFragment", currentFragment);
        outState.putInt("currentFragmentId",currentFragmentId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (currentFragmentId != id) {
            if (id == R.id.nav_uitest) {
                currentFragment = new Test1Fragment();
            } else if (id == R.id.nav_jsontest) {
                currentFragment = new Test2Fragment();
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentContainer, currentFragment).commit();
            currentFragmentId = id;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    * It opens Google Map application with marker on given latitude and longitude
    * @param name This name gets displays on marker on the google map
    * */
    @Override
    public void onNavigateToMapButtonClick(double latitude, double longitude, String name) {
        //geo:0,0?q=lat,lng(label)
        String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%f,%f(%s)", latitude, longitude,name);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }


}
