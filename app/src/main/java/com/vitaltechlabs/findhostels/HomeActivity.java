package com.vitaltechlabs.findhostels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.vitaltechlabs.findhostels.model.HostelsListData;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context mContext;
    String Address;
    TextView hostelNametxt;
    TextView hostelAddresstxt;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    TextView noData;
    Intent intent;

    ArrayList<HostelsListData> _hostelsListData = new ArrayList<HostelsListData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = HomeActivity.this;
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frame_layout, new BedsAvailability());
        tx.commit();


        try {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View view = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.nav_header_main, navigationView);
            hostelNametxt = (TextView) view.findViewById(R.id.hostelNametxt);
            hostelAddresstxt = (TextView) view.findViewById(R.id.hostelAddresstxt);
            navigationView.setNavigationItemSelectedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onBackPressed() {
        try {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.call) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.bed_availability) {
            fragmentClass = BedsAvailability.class;
        } else if ( id == R.id.bed_prices) {
            fragmentClass = BedPrice.class;
        } else if (id == R.id.foodfea) {
            fragmentClass = FoodandFacilities.class;
        } else if (id == R.id.change_password) {
            intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
            startActivity(intent);
        }/*else if (id == R.id.home) {
            fragmentClass = HostelList.class;
        }*/

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();

        /*if (id == R.id.bed_availability) {

            intent = new Intent(getApplicationContext(), BedsAvailabilityActivity.class);

            // Handle the camera action
        } else if (id == R.id.bed_prices) {

            //intent = new Intent(getApplicationContext(), Bed_prices_Activity.class);

        } else if (id == R.id.foodfea) {
            //intent = new Intent(getApplicationContext(), FoodandFeatures.class);

        }else if (id == R.id.change_password) {
            intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);

        }*//* else if (id == R.id.logout) {
            intent = new Intent(getApplicationContext(), LogoutActivity.class);

        }*//*else if (id == R.id.home) {
            //intent = new Intent(getApplicationContext(), HostelsActivity.class);

        }

        startActivity(intent);*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void deleteListItem(final int positionValue) {
        _hostelsListData.remove(positionValue);
        Toast.makeText(getApplicationContext(), "Hostel Successfully Deleted", Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged(); //update adapter
    }

    public void editItem(final int positionValue) {
        _hostelsListData.remove(positionValue);
        Toast.makeText(getApplicationContext(), "Hostel Successfully Deleted", Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged(); //update adapter
    }
}

