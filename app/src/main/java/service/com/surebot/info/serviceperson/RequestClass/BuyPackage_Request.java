package service.com.surebot.info.serviceperson.RequestClass;

public class BuyPackage_Request {
    private String User_ID;

    private String Package_ID;

    private String docket;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getPackage_ID() {
        return Package_ID;
    }

    public void setPackage_ID(String package_ID) {
        Package_ID = package_ID;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}


