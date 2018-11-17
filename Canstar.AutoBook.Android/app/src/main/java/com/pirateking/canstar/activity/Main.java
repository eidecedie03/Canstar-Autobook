package com.pirateking.canstar.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.pirateking.canstar.R;
import com.pirateking.canstar.activity_user.UserHome;
import com.pirateking.canstar.app.App;
import com.pirateking.canstar.helpers.SessionHelper;
import com.pirateking.canstar.models.UserInfoObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Base {

    TextView tv_registration;
    Context context = this;
    ImageView logo;

    private EditText mInputUsername, mInputPassword;
    private SessionHelper mSession;
    private Button btn_login;
    List<UserInfoObject> mlistUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        InitializeVariables();

        logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("API URL");
                builder.setMessage("Enter API URL: 192.168.1.2:80");

                final EditText input = new EditText(view.getContext());
                input.setText(getApiUrl());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setMaxLines(1);

                builder.setView(input);

                builder.setNeutralButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String apiUrl = input.getText().toString();
                        SharedPreferences sharedPref = getSharedPreferences("API_URL", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("API_URL", apiUrl);
                        editor.commit();
                        Toast.makeText(context, "http://"+ getApiUrl()+":8080/Newtrends/api/v1/index.php/ogag", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
                return false;
            }
        });

        tv_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Registration.class));
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
            }
        });

    }

    private void Validation(){
        if(mInputUsername.getText().toString().isEmpty()) mInputUsername.setError("Enter Email");
        if(mInputPassword.getText().toString().isEmpty()) mInputPassword.setError("Enter Password");
        else {
            getlogin();
        }
    }

    private void InitializeVariables(){
        tv_registration = (TextView)findViewById(R.id.tv_register);
        logo = (ImageView)findViewById(R.id.logo);
        mInputUsername = (EditText)findViewById(R.id.input_email);
        mInputPassword = (EditText)findViewById(R.id.input_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        mSession = getSessionHelper();
    }

    public void getlogin() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://192.168.10.104/autobook/api/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
//                    Toast.makeText(context, "response", Toast.LENGTH_SHORT).show();
                    JSONObject obj = new JSONObject(response);
                    Log.d("raweq","" + response);

                    if(obj.getString("Error").equalsIgnoreCase("false")){
//                        Toast.makeText(context, "meron", Toast.LENGTH_SHORT).show();




                        String ID = obj.getString("id");
                        String firstname = obj.getString("FirstName");
                        String lastname = obj.getString("LastName");
                        String Email = obj.getString("EmailAddress");
                        String mobile = obj.getString("Number");
                        String bday = obj.getString("Birthday");
                        String Address = obj.getString("Address");
                        String location = obj.getString("GeoLocation");
                        String url = obj.getString("Avatar");
                        String usertype = obj.getString("UserType");
                        String score = obj.getString("score");
                        Log.d("Test123","123"+usertype);


                        mSession.setID(ID);
                        mSession.setFirstname(firstname);
                        mSession.setLastname(lastname);
                        mSession.setEmail(Email);
                        mSession.setMobile(mobile);
                        mSession.setBirthdate(bday);
                        mSession.setAddress(Address);
                        mSession.setGeolocation(location);
                        mSession.setUrl(url);
                        mSession.setUserType(usertype);
                        mSession.setScore(score);
                        LoginSuccess();

                    }
                    else if(obj.getString("error").equalsIgnoreCase("true")) {
                        Toast.makeText(context, "Please Check your email and password", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Iwatcher", e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        JSONObject object = new JSONObject(res);
//                        showDialog("Error", object.getString("message"), SweetAlertDialog.ERROR_TYPE);
                    } catch (UnsupportedEncodingException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            public Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("emailaddress", mInputUsername.getText().toString().trim());
                params.put("password", mInputPassword.getText().toString().trim());
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        App.getInstance().addToReqQueue(postRequest);
    }

    private void LoginSuccess(){
        mSession.setLogin(true);
        Toast.makeText(context, "" + mSession.getUsertype().toString(), Toast.LENGTH_SHORT).show();
        if(mSession.getUsertype().equalsIgnoreCase("Car owner")) {
            Toast.makeText(context, "Car", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(context, Home.class));
            finish();
        }
        else{
            Toast.makeText(context, "User", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(context, UserHome.class));
            finish();
        }
    }
}
