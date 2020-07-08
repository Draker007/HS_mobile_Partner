package service.com.surebot.info.serviceperson.utils;

import java.util.ArrayList;

public class AppicationClass {

    public static String UserId_FromLogin="pBmfARqTEHNoZYZnWurQdFEbRND2";
    public static String UserName_FromLogin="1";
    public static String UserTableID="9oXlKpfQ6oVUe9TOuqqg";
    public static  String CategoryId_FromLogin="1";
    public static  String UserType_FromCountryList="1";

    public static ArrayList<String> test1 =new ArrayList<>();
    public static ArrayList<String> test2 = new ArrayList<>();


    public static ArrayList<String> newrequestservicesammount = new ArrayList<>();
    public static ArrayList<String> newrequestservicesid = new ArrayList<>();

    public static ArrayList<String> addservicemapingid =new ArrayList<>();
    public static ArrayList<String> addserviceammount =new ArrayList<>();
    public static ArrayList<String> addLocation =new ArrayList<>();
    public static ArrayList<String> addnewserviceammount =new ArrayList<>();
    public static ArrayList<String> addlocationservicecities =new ArrayList<>();

    public static ArrayList<String> addlocationservicestates =new ArrayList<>();
    public static ArrayList<String> addAwardsDetails =new ArrayList<>();

    public static ArrayList<String> getAwardsDetails =new ArrayList<>();


    public static ArrayList<String> multipleaddservicemapingid =new ArrayList<>();


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

    public static ArrayList<String> getNewrequestservicesammount() {
        return newrequestservicesammount;
    }

    public static void setNewrequestservicesammount(ArrayList<String> newrequestservicesammount) {
        AppicationClass.newrequestservicesammount = newrequestservicesammount;
    }

    public static ArrayList<String> getNewrequestservicesid() {
        return newrequestservicesid;
    }

    public static void setNewrequestservicesid(ArrayList<String> newrequestservicesid) {
        AppicationClass.newrequestservicesid = newrequestservicesid;
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


    public static ArrayList<String> getAddnewserviceammount() {
        return addnewserviceammount;
    }

    public static void setAddnewserviceammount(ArrayList<String> addnewserviceammount) {
        AppicationClass.addnewserviceammount = addnewserviceammount;
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

    public static ArrayList<String> getAddAwardsDetails() {
        return addAwardsDetails;
    }

    public static void setAddAwardsDetails(ArrayList<String> addAwardsDetails) {
        AppicationClass.addAwardsDetails = addAwardsDetails;
    }

    public static ArrayList<String> getGetAwardsDetails() {
        return getAwardsDetails;
    }

    public static void setGetAwardsDetails(ArrayList<String> getAwardsDetails) {
        AppicationClass.getAwardsDetails = getAwardsDetails;
    }

    public static ArrayList<String> getAddlocationservicecities() {
        return addlocationservicecities;
    }

    public static void setAddlocationservicecities(ArrayList<String> addlocationservicecities) {
        AppicationClass.addlocationservicecities = addlocationservicecities;
    }

    public static ArrayList<String> getAddlocationservicestates() {
        return addlocationservicestates;
    }

    public static void setAddlocationservicestates(ArrayList<String> addlocationservicestates) {
        AppicationClass.addlocationservicestates = addlocationservicestates;
    }

    public static String getUserType_FromCountryList() {
        return UserType_FromCountryList;
    }

    public static void setUserType_FromCountryList(String userType_FromCountryList) {
        UserType_FromCountryList = userType_FromCountryList;
    }

    public static ArrayList<String> getMultipleaddservicemapingid() {
        return multipleaddservicemapingid;
    }

    public static void setMultipleaddservicemapingid(ArrayList<String> multipleaddservicemapingid) {
        AppicationClass.multipleaddservicemapingid = multipleaddservicemapingid;
    }
}
