package service.com.surebot.info.serviceperson.utils;

public class AppicationClass {

    public static String UserId_FromLogin;
    public static String UserName_FromLogin;

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
