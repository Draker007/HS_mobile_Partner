package service.com.surebot.info.serviceperson.utils;

import java.util.ArrayList;

public class AppicationClass {

    public static String UserId_FromLogin;
    public static String UserName_FromLogin;
    public static  String CategoryId_FromLogin;
    public static ArrayList<String> test1 =new ArrayList<>();
    public static ArrayList<String> test2 = new ArrayList<>();
    public static ArrayList<String> addservicemapingid =new ArrayList<>();
    public static ArrayList<String> addserviceammount =new ArrayList<>();

    public static String getCategoryId_FromLogin() {
        return CategoryId_FromLogin;
    }

    public static void setCategoryId_FromLogin(String categoryId_FromLogin) {
        CategoryId_FromLogin = categoryId_FromLogin;
    }

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


    public static ArrayList<String> getAddservicemapingid() {
        return addservicemapingid;
    }

    public static void setAddservicemapingid(ArrayList<String> addservicemapingid) {
        AppicationClass.addservicemapingid = addservicemapingid;
    }

    public static ArrayList<String> getAddserviceammount() {
        return addserviceammount;
    }

    public static void setAddserviceammount(ArrayList<String> addserviceammount) {
        AppicationClass.addserviceammount = addserviceammount;
    }



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
