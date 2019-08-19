package service.com.surebot.info.serviceperson.Manager;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "FirstTimeLaunch";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String USERNAME = "USERNAME";
    private static final String USER_ROLE = "USER_ROLE";
    public static final String USER_ID = "USER_ID";

    public CacheManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }


    public void setUserName(String userName){
        editor.putString(USERNAME, userName);
        editor.commit();
    }
    public String getUserName(){
        return pref.getString(USERNAME,null);
    }

    public void setUserRole(String userRole){
        editor.putString(USER_ROLE,userRole);
        editor.commit();
    }

    public String getUserRole(){
        return pref.getString(USER_ROLE,null);
    }

    public void setUserId(String userId){
        editor.putString(USER_ID,userId);
        editor.commit();
    }

    public String getUserId(){
        return pref.getString(USER_ID,null);
    }




}
