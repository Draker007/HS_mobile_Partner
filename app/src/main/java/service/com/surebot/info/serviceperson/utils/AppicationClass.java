package service.com.surebot.info.serviceperson.utils;

import java.util.ArrayList;

public class AppicationClass {

    public static String UserId_FromLogin;
    public static String UserName_FromLogin;

    public static String Premium_PartenerId;

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



    static ArrayList<SendquotetoUser_New> gAddedServices_ArrayList = new ArrayList<>();

    public static ArrayList<SendquotetoUser_New> getList() {
        return new ArrayList<>(gAddedServices_ArrayList);
    }

    public static void setList(ArrayList<SendquotetoUser_New> listToBeSet) {
        if (listToBeSet != null)
            gAddedServices_ArrayList.addAll(listToBeSet);
    }

    public static String getPremium_PartenerId() {
        return Premium_PartenerId;
    }

    public static void setPremium_PartenerId(String premium_PartenerId) {
        Premium_PartenerId = premium_PartenerId;
    }
}
