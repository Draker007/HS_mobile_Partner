package service.com.surebot.info.serviceperson.utils;

import java.util.ArrayList;

public class AppicationClass {

    public static String UserId_FromLogin;
    public static String UserName_FromLogin;
    public static ArrayList<String> test1 =new ArrayList<>();
    public static ArrayList<String> test2 = new ArrayList<>();


    public static ArrayList<String> getTest1() {
        return test1;
    }

    public static void setTest1(ArrayList<String> test1) {
        AppicationClass.test1 = test1;
    }

    public static ArrayList<String> getTest2() {
        return test2;
    }

    public static void setTest2(ArrayList<String> test2) {
        AppicationClass.test2 = test2;
    }

    //User Id From Login
    public static String getUserId_FromLogin() {
        return UserId_FromLogin;
    }

    public static void setUserId_FromLogin(String userId_FromLogin) {
        UserId_FromLogin = userId_FromLogin;
    }

    public static String getUserName_FromLogin() {
        return UserName_FromLogin;
    }

    public static void setUserName_FromLogin(String userName_FromLogin) {
        UserName_FromLogin = userName_FromLogin;
    }
}
