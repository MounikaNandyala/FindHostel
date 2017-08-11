package com.vitaltechlabs.findhostels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vitaltechlabs.findhostels.adapter.HostelListAdapter;
import com.vitaltechlabs.findhostels.model.HostelsListData;
import com.vitaltechlabs.findhostels.util.SharedPrefsUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by VTL on 10-08-2017.
 */

public class HostelList extends Fragment{
    /*Context mContext;
    String Address;
    TextView hostelNametxt;
    TextView hostelAddresstxt;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    TextView noData;
    Intent intent;

    ArrayList<HostelsListData> _hostelsListData = new ArrayList<HostelsListData>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hostel_list, container, false);
        mContext = getActivity();;
        noData = (TextView) rootView.findViewById(R.id.noData);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.hostelList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);


        try {
            String hostelname = SharedPrefsUtil.getStringPreference(mContext, "hostelname");
            if (hostelname != null && !hostelname.isEmpty()) {
                hostelNametxt.setText(hostelname + "");
            }
            String area = SharedPrefsUtil.getStringPreference(mContext, "area");
            String city = SharedPrefsUtil.getStringPreference(mContext, "city");
            if (area != null && !area.isEmpty()) {
                Address = area;
            }
            if (city != null && !city.isEmpty()) {
                Address = area + " , " + city;
            }
            if (Address != null) {
                hostelAddresstxt.setText(Address + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONArray tempjsonArray = new JSONArray(SharedPrefsUtil.getStringPreference(mContext, "HOSTEL_DATA"));
            Log.e("tempjsonArray", tempjsonArray.toString());
            adapterAssigning(tempjsonArray);
        } catch (Exception e) {

        }

    return rootView;
    }
    private void adapterAssigning(final JSONArray jsonArray) {
        if (jsonArray.length() != 0) {
            _hostelsListData = new ArrayList<HostelsListData>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jobj = jsonArray.getJSONObject(i);
                    String hostel_id = jobj.getString("hostel_id");
                    String hostelname = jobj.getString("hostelname");
                    String address = jobj.getString("address");
                    String gender = jobj.getString("gender");
                    String area = jobj.getString("area");
                    String city = jobj.getString("city");
                    _hostelsListData.add(new HostelsListData(hostel_id, hostelname, address, area, city, gender));
                } catch (Exception e) {
                }
            }
            mAdapter = new HostelListAdapter(getContext(), _hostelsListData);
            mRecyclerView.setAdapter(mAdapter);
            noData.setVisibility(View.GONE);
        } else {
            noData.setVisibility(View.VISIBLE);
        }
    }

    public void deleteListItem(final int positionValue) {
        _hostelsListData.remove(positionValue);
        Toast.makeText(mContext, "Hostel Successfully Deleted", Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged(); //update adapter
    }

    public void editItem(final int positionValue) {
        _hostelsListData.remove(positionValue);
        Toast.makeText(mContext, "Hostel Successfully Deleted", Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged(); //update adapter
    }*/
}
