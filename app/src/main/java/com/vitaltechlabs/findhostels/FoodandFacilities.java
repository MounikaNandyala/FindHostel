package com.vitaltechlabs.findhostels;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by VTL on 04-08-2017.
 */

public class FoodandFacilities extends Fragment {
    Button foodmenu,facilities;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.foodandfacilities, container, false);
        foodmenu = (Button) rootView.findViewById(R.id.ac_btn);
        facilities = (Button) rootView.findViewById(R.id.non_ac_btn);
        foodmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // String array for alert dialog multi choice items
                String[] foodMenu = new String[]{
                        "South Indian",
                        "North Indian",
                        "Vegetarian",
                        "NON-Vegetarian"

                };
                // Boolean array for initial selected items
                final boolean[] food = new boolean[]{
                        false,
                        true,
                        false,
                        true,
                        false

                };

                // Convert the color array to list
                final List<String> foodList = Arrays.asList(foodMenu);


                builder.setMultiChoiceItems(foodMenu, food, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        // Update the current focused item's checked status
                        food[which] = isChecked;

                        // Get the current focused item
                        String currentItem = foodList.get(which);

                        // Notify the current action
                        Toast.makeText(getActivity(),
                                currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });

                // Specify the dialog is not cancelable
                builder.setCancelable(false);

                // Set a title for alert dialog
                builder.setTitle("Food Menu");

                // Set the positive/yes button click listener
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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
        facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // String array for alert dialog multi choice items
                String[] facilities = new String[]{
                        "TV/Room",
                        "Locker",
                        "Refrigirator",
                        "Mineral Water",
                        "Dhobi",
                        "Security",
                        "GYM",
                        "Power Backup",
                        "Wi-Fi",
                        "Hot Water",
                        "Laundry",
                        "Lift",
                        "Kitchen",
                        "CC Camera"
                        ,"Daily Clean" ,"Parking","WardRobes","Cab Facility"
                };

                // Boolean array for initial selected items
                final boolean[] checkedColors = new boolean[]{
                        false, // Red
                        true, // Green
                        false, // Blue
                        true, // Purple
                        false, // Olive
                        false, // Red
                        true, // Green
                        false, // Blue
                        true, // Purple
                        false,
                        false, // Red
                        true, // Green
                        false, // Blue
                        true, // Purple
                        false,false, // Red
                        true, // Green
                        false


                };

                // Convert the color array to list
                final List<String> facilitiesList = Arrays.asList(facilities);

                builder.setMultiChoiceItems(facilities, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        // Update the current focused item's checked status
                        checkedColors[which] = isChecked;

                        // Get the current focused item
                        String currentItem = facilitiesList.get(which);

                        // Notify the current action
                        Toast.makeText(getActivity(),
                                currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });

                // Specify the dialog is not cancelable
                builder.setCancelable(false);

                // Set a title for alert dialog
                builder.setTitle("Facilities");

                // Set the positive/yes button click listener
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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
}
