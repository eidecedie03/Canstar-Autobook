package com.pirateking.canstar.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionHelper {
    private static String TAG = SessionHelper.class.getSimpleName();

    SharedPreferences pref;

    String playername;

    Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;

    //
    String firstname,lastname,email,mobile,birthday,address,geolocation,url,score,password,userType,id;

    private static final String PREF_NAME = "BCDALogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String PLAYER_NAME = "playername";

    // userinfo
    private  static final String ID = "id";
    private  static final String FIRSTNAME = "firstname";
    private  static final String LASTNAME = "lastname";
    private  static final String EMAIL = "email";
    private static final String MOBILE = "mobile";
    private  static final String BIRTHDAY = "birthday";
    private  static final String ADDRESS = "address";
    private  static final String GEOLOCATION = "latlang";
    private static final String URL = "url";
    private  static final String USERTYPE = "usertype";
    private  static final String SCORE = "score";
    private  static final String PASSWORD = "password";

    public SessionHelper(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public void setID(String id){
        editor.putString(ID,  id);
        Log.d("Playername123",""+id);
        editor.commit();
        this.id = pref.getString(ID, id);
        Log.d(TAG, "Player modified" + pref.getString(ID, id));
    }

    public String getID(){
        this.id = pref.getString(ID,null);
        Log.d("Playername123",""+this.id);
        return id;
    }

    public void setPlayerName(String playerName){
        editor.putString(PLAYER_NAME,  playerName);
        Log.d("firstname","1"+playerName);
        editor.commit();
        this.playername = pref.getString(PLAYER_NAME, playerName);
        Log.d(TAG, "Player modified" + pref.getString(PLAYER_NAME, playerName));
    }

    public String getplayername(){
        this.playername = pref.getString(PLAYER_NAME,null);
        Log.d("firstname","1"+this.firstname);
        return playername;
    }

    public void setFirstname(String firstname){
        editor.putString(FIRSTNAME,  firstname);
        Log.d("1",""+firstname);
        editor.commit();
        this.firstname = pref.getString(FIRSTNAME, firstname);
        Log.d(TAG, "1" + pref.getString(FIRSTNAME, firstname));
    }

    public String getFirstname(){
        this.firstname = pref.getString(FIRSTNAME,null);
        Log.d("Playername123",""+this.playername);
        return firstname;
    }

    public void setLastname(String lastname){
        editor.putString(LASTNAME,  lastname);
        Log.d("1",""+firstname);
        editor.commit();
        this.lastname = pref.getString(LASTNAME, lastname);
        Log.d(TAG, "1" + pref.getString(LASTNAME, lastname));
    }

    public String getLastname(){
        this.lastname = pref.getString(LASTNAME,null);
        Log.d("Playername123",""+this.lastname);
        return lastname;
    }

    public void setEmail(String email){
        editor.putString(EMAIL,  email);
        Log.d("1",""+email);
        editor.commit();
        this.email = pref.getString(EMAIL, email);
        Log.d(TAG, "1" + pref.getString(EMAIL, email));
    }

    public String getEmail(){
        this.email = pref.getString(EMAIL,null);
        Log.d("Playername123",""+this.email);
        return email;
    }

    public void setMobile(String mobile){
        editor.putString(MOBILE,  mobile);
        Log.d("1",""+mobile);
        editor.commit();
        this.mobile = pref.getString(MOBILE, mobile);
        Log.d(TAG, "1" + pref.getString(MOBILE, mobile));
    }

    public String getMobile(){
        this.mobile = pref.getString(MOBILE,null);
        Log.d("Playername123",""+this.mobile);
        return mobile;
    }

    public void setBirthdate(String birthday){
        editor.putString(BIRTHDAY,  birthday);
        Log.d("1",""+birthday);
        editor.commit();
        this.birthday = pref.getString(BIRTHDAY, birthday);
        Log.d(TAG, "1" + pref.getString(BIRTHDAY, birthday));
    }

    public String getBirthdate(){
        this.birthday = pref.getString(BIRTHDAY,null);
        Log.d("Playername123",""+this.birthday);
        return birthday;
    }

    public void setAddress(String address){
        editor.putString(ADDRESS,  address);
        Log.d("1",""+address);
        editor.commit();
        this.address = pref.getString(ADDRESS, address);
        Log.d(TAG, "1" + pref.getString(ADDRESS, address));
    }

    public String getAddress(){
        this.address = pref.getString(ADDRESS,null);
        Log.d("Playername123",""+this.address);
        return address;
    }


    public void setGeolocation(String geolocation){
        editor.putString(GEOLOCATION,  geolocation);
        Log.d("1",""+geolocation);
        editor.commit();
        this.geolocation = pref.getString(GEOLOCATION, geolocation);
        Log.d(TAG, "1" + pref.getString(GEOLOCATION, geolocation));
    }

    public String getGeolocation(){
        this.geolocation = pref.getString(GEOLOCATION,null);
        Log.d("Playername123",""+this.geolocation);
        return geolocation;
    }

    public void setUrl(String url){
        editor.putString(URL,  url);
        Log.d("1",""+url);
        editor.commit();
        this.url = pref.getString(URL, url);
        Log.d(TAG, "1" + pref.getString(URL, url));
    }

    public String getUrl(){
        this.url = pref.getString(URL,null);
        Log.d("Playername123",""+this.url);
        return url;
    }

    public void setUserType(String userType){
        editor.putString(USERTYPE,  userType);
        Log.d("1",""+userType);
        editor.commit();
        this.userType = pref.getString(USERTYPE, userType);
        Log.d(TAG, "1" + pref.getString(USERTYPE, userType));
    }

    public String getUsertype(){
        this.userType = pref.getString(USERTYPE,null);
        Log.d("Playername123",""+this.userType);
        return userType;
    }

    public void setScore(String score){
        editor.putString(SCORE,  score);
        Log.d("1",""+score);
        editor.commit();
        this.score = pref.getString(SCORE, score);
        Log.d(TAG, "1" + pref.getString(SCORE, score));
    }

    public String getScore(){
        this.score = pref.getString(SCORE,null);
        Log.d("Playername123",""+this.score);
        return score;
    }




    public boolean isLoggedIn(){
        Log.d(TAG, "UserLogin");
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);

    }
}