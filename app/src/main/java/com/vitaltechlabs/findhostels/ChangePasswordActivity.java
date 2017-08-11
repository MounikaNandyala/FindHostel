package com.vitaltechlabs.findhostels;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vitaltechlabs.findhostels.serverapis.ApiRequestReferralCode;
import com.vitaltechlabs.findhostels.serverapis.Constants;
import com.vitaltechlabs.findhostels.serverapis.RestApiCallUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity implements RestApiCallUtil.VolleyCallback {
    Button update;
    EditText _oldPassword, _newPassword, _confirmPassword;
    String oldPassword, newPassword, confirmPassword, phoneNumber;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = ChangePasswordActivity.this;
        _oldPassword = (EditText) findViewById(R.id.oldpassword);
        _newPassword = (EditText) findViewById(R.id.newpassword);
        _confirmPassword = (EditText) findViewById(R.id.confirmpassword);
        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = "9848677674";
                oldPassword = _oldPassword.getText().toString();
                newPassword = _newPassword.getText().toString();
                confirmPassword = _confirmPassword.getText().toString();
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constants.ws_api_key, Constants.ws_api_key_value);
                params.put(Constants.cell, phoneNumber);//"9848677674"
                params.put(Constants.new_password, newPassword);//"123456"
                params.put(Constants.old_password, oldPassword);
                params.put(Constants.confirm_password, confirmPassword);
                RestApiCallUtil.postServerResponse(mContext, ApiRequestReferralCode.CHANGEPASSWORD, params);
            }
        });


    }

    @Override
    public void onSuccessResponse(final ApiRequestReferralCode referralCode, final String response_info, final HashMap<String, String> fetchValue, final JSONArray jsonArraydata, final JSONObject jobject) {
        Log.e("referralCode", referralCode.toString() + "");
        Log.e("response", response_info + "");
        try {
            switch (referralCode) {
                case CHANGEPASSWORD:
                    if (response_info != null) {
                        if (response_info.equals("1")) {
                            String response_message = jobject.getString("response_message");
                            Log.e("response_info", response_message + "");
                            Log.e("response_info", response_message + "");
                            Toast.makeText(mContext, "Successfully Changed", Toast.LENGTH_SHORT).show();
                            Toast.makeText(mContext, "msg " + response_message, Toast.LENGTH_SHORT).show();
                            /*Intent i = new Intent(mContext, MainActivity.class);
                            startActivity(i);*/
                        } else {
                            Toast.makeText(mContext, "Not Changed", Toast.LENGTH_SHORT).show();
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
