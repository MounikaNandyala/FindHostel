package com.vitaltechlabs.findhostels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vitaltechlabs.findhostels.serverapis.ApiRequest;
import com.vitaltechlabs.findhostels.serverapis.ApiRequestReferralCode;
import com.vitaltechlabs.findhostels.serverapis.Constants;
import com.vitaltechlabs.findhostels.serverapis.RestApiCallUtil;
import com.vitaltechlabs.findhostels.util.SharedPrefsUtil;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Shiva on 7/31/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, RestApiCallUtil.VolleyCallback
{
	@BindView(R.id.loginBtn)
	Button loginBtn;

	@BindView(R.id.troubleloginBtn)
	Button troubleloginBtn;

	@BindView(R.id.phoneET)
	EditText phoneET;


	@BindView(R.id.passwordET)
	EditText passwordET;

	Context mContext;

	String phoneNumber = "";
	String password = "";

	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		ActionBar bar = getSupportActionBar();
		if (bar != null)
		{
			TextView tv = new TextView(getApplicationContext());
			ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
			tv.setLayoutParams(lp);
			tv.setText(bar.getTitle());
			tv.setGravity(Gravity.CENTER);
			tv.setTextColor(Color.WHITE);
			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
			bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
			bar.setCustomView(tv);
		}

		ButterKnife.bind(this);
		mContext = LoginActivity.this;
		loginBtn.setOnClickListener(this);
		troubleloginBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(final View view)
	{
		switch (view.getId())
		{
			case R.id.loginBtn:
				if (validatingFields())
				{
					phoneNumber = phoneET.getText().toString();
					password =  passwordET.getText().toString();
					Map<String, String> params = new HashMap<String, String>();
					params.put(Constants.ws_api_key, Constants.ws_api_key_value);
					params.put(Constants.customer_mobile, phoneNumber);//"9848677674"
					params.put(Constants.password, password);//"123456"
					RestApiCallUtil.postServerResponse(mContext, ApiRequestReferralCode.LOGIN, params);
				}
				//sendRequest(ApiRequestHelper.getRateUs(ptnt_cd, p_rating, p_feedBack, p_deviceId, p_os_version, p_os_type, p_app_version));
				break;
			case R.id.troubleloginBtn:
				Intent intent = new Intent(mContext, MainActivity.class);
				startActivity(intent);
				break;
		}
	}

	private boolean validatingFields()
	{
		boolean success = true;
		phoneNumber = phoneET.getText().toString();
		password = passwordET.getText().toString();
		if (phoneNumber.length() == 0)
		{
			Toast.makeText(mContext, "Please enter mobile number", Toast.LENGTH_SHORT).show();
			success = false;
			return success;
		}
		else if (phoneNumber.length() != 10)
		{
			Toast.makeText(mContext, "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
			success = false;
			return success;
		}
		if (password.length() == 0)
		{
			Toast.makeText(mContext, "Please enter password", Toast.LENGTH_SHORT).show();
			success = false;
			return success;
		}

		return success;
	}

	@Override
	public void onSuccessResponse(final ApiRequestReferralCode referralCode, final String response_info, final HashMap<String, String> fetchValue, final JSONArray jsonArraydata, JSONObject Jobject)
	{
		Log.e("referralCode", referralCode.toString() + "");
		Log.e("response", response_info + "");
		try
		{
			switch (referralCode)
			{
				case LOGIN:
					if (response_info != null)
					{
						if (response_info.equals("1"))
						{
							String hostel_id = jsonArraydata.getJSONObject(0).getString(Constants.hostel_id);
							SharedPrefsUtil.setStringPreference(mContext, "HOSTEL_DATA", jsonArraydata.toString());
							SharedPrefsUtil.setStringPreference(mContext, "hostel_id", hostel_id);
							String hostelname = jsonArraydata.getJSONObject(0).getString("hostelname");
							SharedPrefsUtil.setStringPreference(mContext, "hostelname", hostelname);
							String area = jsonArraydata.getJSONObject(0).getString("area");
							SharedPrefsUtil.setStringPreference(mContext, "area", area);
							String city = jsonArraydata.getJSONObject(0).getString("city");
							SharedPrefsUtil.setStringPreference(mContext, "city", city);
							Toast.makeText(mContext, "Successfully Login", Toast.LENGTH_SHORT).show();
//							Toast.makeText(mContext, "Hostel Name " + hostelname, Toast.LENGTH_SHORT).show();
							Toast.makeText(mContext, "Hostel Id " + hostel_id, Toast.LENGTH_SHORT).show();
							Intent i = new Intent(mContext, HostelsActivity.class);
							startActivity(i);
						}
						else
						{
							Toast.makeText(mContext, "Invalid Credentials", Toast.LENGTH_SHORT).show();
						}
					}
					else
					{
						Toast.makeText(mContext, "Invalid Credentials", Toast.LENGTH_SHORT).show();
					}
					break;
			}
		}
		catch (Exception e)
		{

		}
	}
}
