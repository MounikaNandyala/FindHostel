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

public class BedsAvailability extends Fragment implements View.OnClickListener, RestApiCallUtil.VolleyCallback {

    Button acAvailability;

    Button nonAcAvailability;
    Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_beds_availability, container, false);


        acAvailability = (Button) rootView.findViewById(R.id.ac_btn);
        nonAcAvailability = (Button) rootView.findViewById(R.id.non_ac_btn);
        acAvailability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Availability", "Beds");
                final boolean[] checkedAvailability = new boolean[]{
                        false,
                        true,
                        false,
                        true,
                        false
                };
                final String type = "ac";
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constants.ws_api_key, Constants.ws_api_key_value);
                params.put("hostel_id", "2");
                params.put("type", type);
                //params.put("checkedAvailability", String.valueOf(checkedAvailability));
                RestApiCallUtil.postServerResponse(mContext, ApiRequestReferralCode.BedAvailabilityUrl, params);
            }
        });
        nonAcAvailability.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        final boolean[] checkedAvailability = new boolean[]{
                false,
                true,
                false,
                true,
                false

        };

        switch (v.getId()) {
            case R.id.ac_bed:
                final String type = "ac";
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // String array for alert dialog multi choice items
                String[] sharing_list = new String[]{
                        "1 Sharing",
                        "2 Sharing",
                        "3 Sharing",
                        "4 Sharing",
                        "5 Sharing"
                };


                // Convert the color array to list
                final List<String> colorsList = Arrays.asList(sharing_list);
                builder.setMultiChoiceItems(sharing_list, checkedAvailability, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        // Update the current focused item's checked status
                        checkedAvailability[which] = isChecked;

                        // Get the current focused item
                        String currentItem = colorsList.get(which);

                        // Notify the current action
                        Toast.makeText(getActivity(),
                                currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });

                // Specify the dialog is not cancelable
                builder.setCancelable(false);

                // Set a title for alert dialog
                builder.setTitle("AC Availability");

                // Set the positive/yes button click listener
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(Constants.ws_api_key, Constants.ws_api_key_value);
                        params.put("type", type);
                        Log.e("Availability", "Beds");
                        params.put("checkedAvailability", String.valueOf(checkedAvailability));
                        RestApiCallUtil.postServerResponse(mContext, ApiRequestReferralCode.BedAvailabilityUrl, params);

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

                break;
            case R.id.non_ac_bed:
                // Build an AlertDialog
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());

                // String array for alert dialog multi choice items
                String[] non_ac_sharing = new String[]{
                        "1 Sharing",
                        "2 Sharing",
                        "3 Sharing",
                        "4 Sharing",
                        "5 Sharing"
                };

                // Boolean array for initial selected items
                final boolean[] nonAccheckedAvailability = new boolean[]{
                        false,
                        true,
                        false,
                        true,
                        false

                };

                // Convert the color array to list
                final List<String> colorsList1 = Arrays.asList(non_ac_sharing);

                builder1.setMultiChoiceItems(non_ac_sharing, nonAccheckedAvailability, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        // Update the current focused item's checked status
                        nonAccheckedAvailability[which] = isChecked;

                        // Get the current focused item
                        String currentItem = colorsList1.get(which);

                        // Notify the current action
                        Toast.makeText(getActivity(),
                                currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });

                // Specify the dialog is not cancelable
                builder1.setCancelable(false);

                // Set a title for alert dialog
                builder1.setTitle("NON-AC Availability");

                // Set the positive/yes button click listener
                builder1.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                // Set the neutral/cancel button click listener
                builder1.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the neutral button
                    }
                });

                AlertDialog dialog1 = builder1.create();
                // Display the alert dialog on interface
                dialog1.show();
        }

    }

    @Override
    public void onSuccessResponse(final ApiRequestReferralCode referralCode, final String response_info, final HashMap<String, String> fetchValue, final JSONArray jsonArraydata, JSONObject jObject) {
        Log.e("referralCode", referralCode.toString() + "");
        Log.e("response", response_info + "");
        try {
            switch (referralCode) {
                case BedAvailabilityUrl:
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

