package com.pirateking.canstar.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.service.autofill.SaveInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pirateking.canstar.R;
import com.pirateking.canstar.app.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registration extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    Context context = this;
    private GoogleMap mMap;
    private Button btn_proceed,btn_save_address,btn_search, btn_register_car_owner;
    private EditText mInputfirstname, mInputLastname, mInputBirthday, mInputEmail, mInputMobile, mInputPassword, mInputConfirmPassword, mInputSearchAddress;
    private RadioButton mRadioCarOwner, mRadioUser;


    private RelativeLayout layout_reg_info;
    private RelativeLayout layout_map;
    private ScrollView layout_scrollview_register;

    int status = 0;
    private String user_level = "";

    //google variables
    private Location lastlocation;
    private String global_latlang;
    private double global_lattitude;
    private double global_longtitude;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Marker currentUserLocationMarker;
    private static final int Request_User_location_code = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registration);
        InitializeVariables();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + status, Toast.LENGTH_SHORT).show();
                ChooseUsers();
                Validation();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String address = mInputSearchAddress.getText().toString();
                List<Address> addressList = null;
                MarkerOptions usermarketoptions = new MarkerOptions();

                if(!TextUtils.isEmpty(address)){
                    Geocoder geocoder = new Geocoder(context);
                    try {
                        addressList  = geocoder.getFromLocationName(address, 6);

                        if(addressList != null){
                            for (int i = 0; i<addressList.size();i++){
                                Address userAddress = addressList.get(i);
                                LatLng latLng = new LatLng(userAddress.getLatitude(),userAddress.getLongitude());
                                usermarketoptions.position(latLng);
                                usermarketoptions.title(address);
                                usermarketoptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE ));
                                mMap.addMarker(usermarketoptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

                                global_latlang = Double.toString(userAddress.getLatitude()) + Double.toString(userAddress.getLongitude()) ;
                                Toast.makeText(context, " " + global_latlang, Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    Toast.makeText(context, "write any location name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_register_car_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + user_level, Toast.LENGTH_SHORT).show();
                if(mInputSearchAddress.getText().toString().isEmpty()) mInputSearchAddress.setError("please input address");
                if(global_latlang.isEmpty()) Toast.makeText(context, "address invalid", Toast.LENGTH_SHORT).show();
                else{
                    SaveInfo();
                }
            }
        });


    }


    private void Validation() {

        if (mInputfirstname.getText().toString().isEmpty())
            mInputfirstname.setError("please enter first name");
        if (mInputLastname.getText().toString().isEmpty())
            mInputLastname.setError("please enter last name");
        if (mInputEmail.getText().toString().isEmpty()) mInputEmail.setError("please enter email");
        if (mInputMobile.getText().toString().isEmpty())
            mInputMobile.setError("please enter mobile number");
        if (mInputBirthday.getText().toString().isEmpty())
            mInputBirthday.setError("please enter birthday");
        if (mInputPassword.getText().toString().isEmpty())
            mInputPassword.setError("please enter password");
        if (mInputConfirmPassword.getText().toString().isEmpty())
            mInputConfirmPassword.setError("please enter confirm password");
//        if(mInputPassword.getText().toString().equals(mInputConfirmPassword.getText().toString())) {
//            mInputPassword.setError("please check your password");
//            mInputConfirmPassword.setError("please check your password");
//        }
        else {

            if (status == 1) {
                layout_scrollview_register.setVisibility(View.GONE);
                btn_proceed.setVisibility(View.GONE);
                layout_map.setVisibility(View.VISIBLE);
                user_level = "Car owner";
                Toast.makeText(context, "" + user_level, Toast.LENGTH_SHORT).show();
            } else if (status == 2) {
                user_level = "user";
                Toast.makeText(context, "" + user_level, Toast.LENGTH_SHORT).show();
                SaveInfo();
            }

        }
    }

    private void ChooseUsers() {
        if (mRadioUser.isChecked()) {
            this.status = 2;
            Toast.makeText(context, "" + status, Toast.LENGTH_SHORT).show();
        } else {
            this.status = 1;
            Toast.makeText(context, "" + status, Toast.LENGTH_SHORT).show();
        }
    }


    private void InitializeVariables() {
        btn_proceed = (Button) findViewById(R.id.btn_register);
        mInputfirstname = (EditText) findViewById(R.id.input_firstname);
        mInputLastname = (EditText) findViewById(R.id.input_lastname);
        mInputEmail = (EditText) findViewById(R.id.input_email);
        mInputMobile = (EditText) findViewById(R.id.input_mobile);
        mInputBirthday = (EditText) findViewById(R.id.input_birthdate);
        layout_reg_info = (RelativeLayout) findViewById(R.id.layout_reg_info);
        layout_map = (RelativeLayout) findViewById(R.id.layout_map);
        layout_scrollview_register = (ScrollView) findViewById(R.id.layout_scroll_view);
        mRadioCarOwner = (RadioButton) findViewById(R.id.radioCarOwner);
        mRadioUser = (RadioButton) findViewById(R.id.radioUser);
        mInputPassword = (EditText) findViewById(R.id.input_password);
        mInputConfirmPassword = (EditText) findViewById(R.id.input_confirmpassword);
        mInputSearchAddress = (EditText) findViewById(R.id.input_search_address);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_register_car_owner = (Button)findViewById(R.id.btn_register1);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(context, Main.class));
        finish();
    }


    public boolean checkUserLocationPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_location_code);
            }
            else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_location_code);
            }
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode){
           case Request_User_location_code:
               if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                   if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                       if(googleApiClient == null){
                           buildGoogleApiClient();
                       }
                       mMap.setMyLocationEnabled(true);
                   }
               }
               else {
                   Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show();
               }
               return;
       }
    }

    private void SaveInfo() {
        showLoadingDialog(context);
        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://" + getApiUrl() + "/autobook/api/user", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismissDialog();
                startActivity(new Intent(context, Main.class));
                finish();
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("error").equals("false")) {
                        JSONArray jsonArray = obj.getJSONArray("details");
                        Toast.makeText(context, "Data send", Toast.LENGTH_SHORT).show();
//                        dbHelper.insertPrices(mlistPrices);
                    } else {
                        showMessageDialog(context, "Error", "Network Error");
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Iwatcher", e.getMessage());
                    showMessageDialog(context, "Error Network 404", e.getMessage().toString());
                }
//                showMessageDialog(context,"Error", "Network Error");
                dismissDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showMessageDialog(context, "Error", "Network Error");
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        JSONObject object = new JSONObject(res);
                        showMessageDialog(context, "Error", object.getString("message").toString());
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
                params.put("firstName", mInputfirstname.getText().toString());
                params.put("lastName", mInputLastname.getText().toString());
                params.put("emailaddress", mInputEmail.getText().toString());
                params.put("birthday", mInputBirthday.getText().toString());
                params.put("number", mInputMobile.getText().toString());
                params.put("password", mInputPassword.getText().toString());
                params.put("UserType", user_level);
                return params;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        App.getInstance().addToReqQueue(postRequest);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  ==  PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }

    }

    private synchronized void buildGoogleApiClient(){
       googleApiClient = new GoogleApiClient.Builder(context)
               .addConnectionCallbacks(this)
               .addOnConnectionFailedListener(this)
               .addApi(LocationServices.API)
               .build();

       googleApiClient.connect();
    }



    @Override
    public void onLocationChanged(Location location) {
        lastlocation = location;

        if(currentUserLocationMarker != null){
            currentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("user current address");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        currentUserLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

        if(googleApiClient != null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, (com.google.android.gms.location.LocationListener) this);
        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  ==  PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    MaterialDialog loadingdialog;
    public void showLoadingDialog(Context context){
        loadingdialog =  new MaterialDialog.Builder(context)
                .title("test loading")
                .content("please wait")
                .progress(true, 0)
                .show();
    }

    public void dismissDialog(){
        loadingdialog.dismiss();
    }

    public void showMessageDialog(Context context, String status, String message){
        if(status.equals("Success")) {
            new MaterialDialog.Builder(context).content("Success").contentColor(getResources().getColor(R.color.colorPrimary)).positiveText("OK").show();
        }
        else if(status.equals("Error")){
            new MaterialDialog.Builder(context).content("Error").contentColor(getResources().getColor(R.color.colorPrimaryDark)).negativeText("Dismiss").show();
        }

    }

    public String getApiUrl() {
        return getSharedPreferences("API_URL", Context.MODE_PRIVATE).getString("API_URL", "192.168.21.28");
    }
}
