package com.vitaltechlabs.findhostels;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.vitaltechlabs.findhostels.serverapis.ApiRequestReferralCode;
import com.vitaltechlabs.findhostels.serverapis.Constants;
import com.vitaltechlabs.findhostels.serverapis.RestApiCallUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by VTL on 04-08-2017.
 */

public class BedPrice extends Fragment implements  RestApiCallUtil.VolleyCallback {
    Button acBed,nonAcBed;
    EditText sharingprice1,sharingprice2,sharingprice3,sharingprice4,sharingprice5;
    String oneSharing,twoSharing,threeSharing,fourSharing,fiveSharing;
    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bed_price, container, false);
        mContext=getActivity();
        acBed=(Button) rootView.findViewById(R.id.ac_bed) ;
        nonAcBed=(Button) rootView.findViewById(R.id.non_ac_bed) ;

        acBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater li = LayoutInflater.from(getContext());
                View promptsView = li.inflate(R.layout.price_item, null);
                sharingprice1=(EditText) promptsView.findViewById(R.id.sharingprice1);
                sharingprice2=(EditText) promptsView.findViewById(R.id.sharingprice2);
                sharingprice3=(EditText) promptsView.findViewById(R.id.sharingprice3);
                sharingprice4=(EditText) promptsView.findViewById(R.id.sharingprice4);
                sharingprice5=(EditText) promptsView.findViewById(R.id.sharingprice5);

                // set prompts.xml to alertdialog builder
                builder.setView(promptsView);
                // Specify the dialog is not cancelable
                builder.setCancelable(false);

                // Set a title for alert dialog
                builder.setTitle("AC Beds Availability");

                // Set the positive/yes button click listener
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        oneSharing = sharingprice1.getText().toString();
                        twoSharing =  sharingprice2.getText().toString();
                        threeSharing = sharingprice3.getText().toString();
                        fourSharing =  sharingprice4.getText().toString();
                        fiveSharing =  sharingprice5.getText().toString();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(Constants.ws_api_key, Constants.ws_api_key_value);
                        params.put("hostel_id", "2");
                        params.put("type", "ac");
                        params.put(Constants.one_sharing, oneSharing);
                        params.put(Constants.two_sharing, twoSharing);
                        params.put(Constants.three_sharing, threeSharing);
                        params.put(Constants.four_sharing, fourSharing);
                        params.put(Constants.five_sharing, fiveSharing);
                        RestApiCallUtil.postServerResponse(mContext, ApiRequestReferralCode.EDITBEDPRICE, params);
                    }
                });
                // Set the neutral/cancel button click listener
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the neutral button
                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        });
        nonAcBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater li = LayoutInflater.from(getContext());
                View promptsView = li.inflate(R.layout.price_item, null);
               // set prompts.xml to alertdialog builder
                builder.setView(promptsView);
                // Specify the dialog is not cancelable
                builder.setCancelable(false);
                // Set a title for alert dialog
                builder.setTitle("NON-AC Beds Availability");

                // Set the positive/yes button click listener
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        oneSharing = sharingprice1.getText().toString();
                        twoSharing =  sharingprice2.getText().toString();
                        threeSharing = sharingprice3.getText().toString();
                        fourSharing =  sharingprice4.getText().toString();
                        fiveSharing =  sharingprice5.getText().toString();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(Constants.ws_api_key, Constants.ws_api_key_value);
                        params.put("hostel_id", "2");
                        params.put("type", "nonac");
                        params.put(Constants.one_sharing, oneSharing);
                        params.put(Constants.two_sharing, twoSharing);
                        params.put(Constants.three_sharing, threeSharing);
                        params.put(Constants.four_sharing, fourSharing);
                        params.put(Constants.five_sharing, fiveSharing);
                        RestApiCallUtil.postServerResponse(mContext, ApiRequestReferralCode.EDITBEDPRICE, params);
                    }
                });
                // Set the neutral/cancel button click listener
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the neutral button
                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        });


        return rootView;
    }

    @Override
    public void onSuccessResponse(final ApiRequestReferralCode referralCode, final String response_info, final HashMap<String, String> fetchValue, final JSONArray jsonArraydata, JSONObject jObject) {
        Log.e("referralCode", referralCode.toString() + "");
        Log.e("response", response_info + "");
        try {
            switch (referralCode) {
                case BEDPRICE:
                    if (response_info != null) {
                        if (response_info.equals("1")) {
                            String response_message = jObject.getString("response_message");
                            Toast.makeText(mContext, "Successfully update", Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, "msg " + response_message, Toast.LENGTH_SHORT).show();
                            /*Intent i = new Intent(mContext, MainActivity.class);
                            startActivity(i);*/
                        } else {
                            Toast.makeText(mContext, "Not Available", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, "Not Available", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case EDITBEDPRICE:
                    if (response_info != null) {
                        if (response_info.equals("1")) {
                            String response_message = jObject.getString("response_message");
                            Toast.makeText(mContext, "Successfully update", Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, "msg " + response_message, Toast.LENGTH_SHORT).show();
                            /*Intent i = new Intent(mContext, MainActivity.class);
                            startActivity(i);*/
                        } else {
                            Toast.makeText(mContext, "Not Available", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, "Not Available", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        } catch (Exception e) {

        }
    }
}
