package com.atechnos.quotebank;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "VishadSagar";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String KEY_VALUE_CHECK = "checkValue";
    private static final String KEY_QUOTES_SLEEP = "quotes_sleep";
    private static final String KEY_AUTHOR_SLEEP = "author_sleep";
    private static final String KEY_QUOTES_WAKE = "quotes_wake";
    private static final String KEY_AUTHOR_WAKE = "author_wake";
    private static final String KEY_TIME_WAKE = "time_wake";
    private static final String KEY_TIME_SLEEP = "time_sleep";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setQuotesSleep(String keyValue) {
        editor.putString(KEY_QUOTES_SLEEP, keyValue);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public String getQuotesSleep() {
        return pref.getString(KEY_QUOTES_SLEEP, null);
    }

    public void setAuthorSleep(String keyValue) {
        editor.putString(KEY_AUTHOR_SLEEP, keyValue);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public String getAuthorSleep() {
        return pref.getString(KEY_AUTHOR_SLEEP, null);
    }



    public void setQuotesWake(String keyValue) {
        editor.putString(KEY_QUOTES_WAKE, keyValue);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public String getQuotesWake() {
        return pref.getString(KEY_QUOTES_WAKE, null);
    }

    public void setAuthorWake(String keyValue) {
        editor.putString(KEY_AUTHOR_WAKE, keyValue);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public String getAuthorWake() {
        return pref.getString(KEY_AUTHOR_WAKE, null);
    }


    public void setTimeWake(String keyValue) {
        editor.putString(KEY_TIME_WAKE, keyValue);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public String getTimeWake() {
        return pref.getString(KEY_TIME_WAKE, null);
    }

    public void setTimeSleep(String keyValue) {
        editor.putString(KEY_TIME_SLEEP, keyValue);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public String getTimeSleep() {
        return pref.getString(KEY_TIME_SLEEP, null);
    }






//    public void setLogin(boolean isLoggedIn) {
//
//        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
//
//        // commit changes
//        editor.commit();
//
//        Log.d(TAG, "User login session modified!");
//    }
//
//    public boolean isLoggedIn() {
//        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
//    }



//    public void setKeyUser(String keyUser) {
//        editor.putString(KEY_USER, keyUser);
//        editor.commit();
//
//        Log.d(TAG, "User login session modified!");
//    }
//
//    public String getKeyUser() {
//        return pref.getString(KEY_USER, null);
//    }
//
//    public void setKeyAnswer(String keyAnswer) {
//        editor.putString(KEY_ANSWER, keyAnswer);
//        editor.commit();
//
//        Log.d(TAG, "User login session modified!");
//    }
//
//    public String getKeyAnswer() {
//        return pref.getString(KEY_ANSWER, null);
//    }
//
//    public void setKeyLocation(String keyLocation) {
//        editor.putString(KEY_LOCATION, keyLocation);
//        editor.commit();
//
//        Log.d(TAG, "User login session modified!");
//    }
//
//    public String getKeyLocation() {
//        return pref.getString(KEY_LOCATION, null);
//    }
//
//    public void setKeyUserName(String keyUserName) {
//        editor.putString(KEY_USER_NAME, keyUserName);
//        editor.commit();
//
//        Log.d(TAG, "User login session modified!");
//    }
//
//    public String getKeyUserName() {
//        return pref.getString(KEY_USER_NAME, null);
//    }
//
//    public void setKeyUserMobile(String keyUserMobile) {
//        editor.putString(KEY_USER_MOBILE, keyUserMobile);
//        editor.commit();
//
//        Log.d(TAG, "User login session modified!");
//    }
//
//    public String getKeyProfileImage() {
//        return pref.getString(KEY_PROFILE_IMAGE, null);
//    }
//
//    public void setKeyProfileImage(String keyProfileImage) {
//        editor.putString(KEY_PROFILE_IMAGE, keyProfileImage);
//        editor.commit();
//
//        Log.d(TAG, "User login session modified!");
//    }
//
//    public String getKeyUserMobile() {
//        return pref.getString(KEY_USER_MOBILE, null);
//    }

}

