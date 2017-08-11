package com.vitaltechlabs.findhostels.serverapis;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.provider.Telephony.Carriers.PASSWORD;


public class RestApiCallUtil {
    public interface VolleyCallback {
        void onSuccessResponse(ApiRequestReferralCode referralCode, String result, HashMap<String, String> fetchValue, JSONArray jsonArray, JSONObject jobject);
    }

    public static ProgressDialog mProgressDialog;

    public static void postServerResponse(final Context context,
                                          final ApiRequestReferralCode api,
                                          final Map<String, String> passedparams) {
        try {
            if (isOnline(context)) {
                RequestQueue queue = Volley.newRequestQueue(context);
                showProgressDialog(context);
                String url = "";
                switch (api) {
                    case LOGIN:
                        url = ApiConstants.loginUrl;
                        break;
                    case BedAvailabilityUrl:
                        url = ApiConstants.BedAvailabilityUrl;
                        break;
                    case CHANGEPASSWORD:
                        url = ApiConstants.ChangePasswordUrl;
                        break;
                    case BEDPRICE:
                        url = ApiConstants.BedPriceUrl;
                        break;
                    case EDITBEDPRICE:
                        url = ApiConstants.EditBedPriceUrl;
                        break;
                    case FOODMENU:
                        url = ApiConstants.FoodUrl;
                        break;
                    case FACILITIES:
                        url = ApiConstants.FacilitiesUrl;
                        break;
                    case EDITHOSTEL:
                        url = ApiConstants.EditHostelUrl;
                        break;
                    case DELETEHOSTEL:
                        url = ApiConstants.DeleteHostelUrl;
                        break;
                }
                Log.e("url", url + "");
                StringRequest jsonObjRequest = new StringRequest
                        (Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    hideProgressDialog();
                                    //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                                    JSONObject jObj = null;
                                    JSONArray jsonArraydata = null;
                                    HashMap<String, String> value = new HashMap<String, String>();
                                    String responsetoActivity = "";
                                    String responseMessage = "";
                                    Log.e("response", response.toString());
                                    if (response != null) {
                                        jObj = new JSONObject(response);
                                    }

                                    switch (api) {
                                        case LOGIN:
                                            try {
                                                if (jObj != null) {
                                                    jObj = jObj.getJSONObject("response");
                                                    String response_info = jObj.getString("response_info");
                                                    if (response_info.equals("1")) {
                                                        if (jObj.getJSONArray("response_data") != null) {
                                                            jsonArraydata = jObj.getJSONArray("response_data");
                                                            ((VolleyCallback) context).onSuccessResponse(api, response_info, value, jsonArraydata, jObj);
                                                        } else {
                                                            ((VolleyCallback) context).onSuccessResponse(api, response_info, value, jsonArraydata, jObj);
                                                        }
                                                    } else {
                                                        ((VolleyCallback) context).onSuccessResponse(api, response_info, value, jsonArraydata, jObj);
                                                    }
                                                } else {
                                                    NeResponseError(context);
                                                }
                                            } catch (Exception e) {
                                            }

                                            break;
                                        case BedAvailabilityUrl:
                                            try {
                                                if (jObj != null) {
                                                    jObj = jObj.getJSONObject("response");
                                                    Integer response_info = jObj.getInt("response_info");
                                                    jsonArraydata = new JSONArray();
                                                    if (response_info == 1) {
                                                         responseMessage = jObj.getString("response_message");
                                                        Log.e("response Message",responseMessage  + "");
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    } else {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    }
                                                } else {
                                                    NeResponseError(context);
                                                }
                                            } catch (Exception e) {
                                                Log.e("error", e + "");
                                            }

                                            break;
                                        case CHANGEPASSWORD:
                                            try {
                                                if (jObj != null) {
                                                    jObj = jObj.getJSONObject("response");
                                                    Integer response_info = jObj.getInt("response_info");
                                                    String response_msg = jObj.getString("response_message");

                                                    Log.e("response_msg", response_msg + "");
                                                    jsonArraydata = new JSONArray();
                                                    if (response_info == 1) {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    } else {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    }
                                                } else {
                                                    NeResponseError(context);
                                                }
                                            } catch (Exception e) {
                                                Log.e("error", e + "");
                                            }

                                            break;
                                        case BEDPRICE:
                                            try {
                                                if (jObj != null) {
                                                    jObj = jObj.getJSONObject("response");
                                                    Integer response_info = jObj.getInt("response_info");
                                                    String response_msg = jObj.getString("response_message");

                                                    Log.e("response_msg", response_msg + "");
                                                    jsonArraydata = new JSONArray();
                                                    if (response_info == 1) {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    } else {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    }
                                                } else {
                                                    NeResponseError(context);
                                                }
                                            } catch (Exception e) {
                                                Log.e("error", e + "");
                                            }

                                            break;
                                        case EDITBEDPRICE:
                                            try {
                                                if (jObj != null) {
                                                    jObj = jObj.getJSONObject("response");
                                                    Integer response_info = jObj.getInt("response_info");
                                                    String response_msg = jObj.getString("response_message");

                                                    Log.e("response_msg", response_msg + "");
                                                    jsonArraydata = new JSONArray();
                                                    if (response_info == 1) {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    } else {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    }
                                                } else {
                                                    NeResponseError(context);
                                                }
                                            } catch (Exception e) {
                                                Log.e("error", e + "");
                                            }

                                            break;
                                        case FOODMENU:
                                            try {
                                                if (jObj != null) {
                                                    jObj = jObj.getJSONObject("response");
                                                    Integer response_info = jObj.getInt("response_info");
                                                    String response_msg = jObj.getString("response_message");

                                                    Log.e("response_msg", response_msg + "");
                                                    jsonArraydata = new JSONArray();
                                                    if (response_info == 1) {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    } else {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    }
                                                } else {
                                                    NeResponseError(context);
                                                }
                                            } catch (Exception e) {
                                                Log.e("error", e + "");
                                            }

                                            break;
                                        case FACILITIES:
                                            try {
                                                if (jObj != null) {
                                                    jObj = jObj.getJSONObject("response");
                                                    Integer response_info = jObj.getInt("response_info");
                                                    String response_msg = jObj.getString("response_message");

                                                    Log.e("response_msg", response_msg + "");
                                                    jsonArraydata = new JSONArray();
                                                    if (response_info == 1) {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    } else {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    }
                                                } else {
                                                    NeResponseError(context);
                                                }
                                            } catch (Exception e) {
                                                Log.e("error", e + "");
                                            }

                                            break;
                                        case EDITHOSTEL:
                                            try {
                                                if (jObj != null) {
                                                    jObj = jObj.getJSONObject("response");
                                                    Integer response_info = jObj.getInt("response_info");
                                                    String response_msg = jObj.getString("response_message");

                                                    Log.e("response_msg", response_msg + "");
                                                    jsonArraydata = new JSONArray();
                                                    if (response_info == 1) {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    } else {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    }
                                                } else {
                                                    NeResponseError(context);
                                                }
                                            } catch (Exception e) {
                                                Log.e("error", e + "");
                                            }

                                            break;
                                        case DELETEHOSTEL:
                                            try {
                                                if (jObj != null) {
                                                    jObj = jObj.getJSONObject("response");
                                                    Integer response_info = jObj.getInt("response_info");
                                                    String response_msg = jObj.getString("response_message");

                                                    Log.e("response_msg", response_msg + "");
                                                    jsonArraydata = new JSONArray();
                                                    if (response_info == 1) {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    } else {
                                                        ((VolleyCallback) context).onSuccessResponse(api, String.valueOf(response_info), value, jsonArraydata, jObj);
                                                    }
                                                } else {
                                                    NeResponseError(context);
                                                }
                                            } catch (Exception e) {
                                                Log.e("error", e + "");
                                            }

                                            break;

                                    }


                                } catch (Exception e) {
                                    hideProgressDialog();
                                }

                            }
                        },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        hideProgressDialog();
//								 Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(context, "we had some server issue..", Toast.LENGTH_SHORT).show();
                                    }
                                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        /*Map<String, String> params = new HashMap<String, String>();
                        params.put("ws_api_key", "!@#$%&OIUYTRE123456");
						params.put("customer_mobile", "9848677674");
						params.put("password", "123456");*/
                        Log.e("params", passedparams + "");
                        return passedparams;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/x-www-form-urlencoded");
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(jsonObjRequest);
            } else {
                NetworkError(context);
            }
        } catch (Exception e) {
            hideProgressDialog();
        }
    }

    public static void showProgressDialog(Context context) {

        try {
            hideProgressDialog();
            mProgressDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
            mProgressDialog.setMessage("Please Wait...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            mProgressDialog.show();
        } catch (Exception e) {

        }
    }

    public static void hideProgressDialog() {
        try {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        } catch (Exception e) {

        }
    }


    /**
     * Check Online Connectivity
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
//

    /**
     * Display error in case no net connection
     */

    public static void NetworkError(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("No Network")
                .setMessage("Please check the internet connection")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                });
        alert = builder.create();
        alert.show();

    }

    /**
     * Display error in case no net connection
     */
    public static AlertDialog alert;

    public static void NeResponseError(Context context) {

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    context, AlertDialog.THEME_HOLO_LIGHT);
            builder.setTitle("No Data")

                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();

                        }
                    });
            if (builder != null) {
                builder.create().show();
            }

        } catch (Exception e) {

        }
    }
}
