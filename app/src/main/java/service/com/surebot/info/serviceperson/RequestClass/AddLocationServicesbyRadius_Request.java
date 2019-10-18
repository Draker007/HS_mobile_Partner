package service.com.surebot.info.serviceperson.RequestClass;

public class AddLocationServicesbyRadius_Request {
    private String User_ID;

    private String Zipcode;

    private String Radius_In_KMs;

    private String docket;

    private String Service_ID;

    private String Service_Amount;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getRadius_In_KMs() {
        return Radius_In_KMs;
    }

    public void setRadius_In_KMs(String radius_In_KMs) {
        Radius_In_KMs = radius_In_KMs;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }

    public String getService_ID() {
        return Service_ID;
    }

    public void setService_ID(String service_ID) {
        Service_ID = service_ID;
    }

    public String getService_Amount() {
        return Service_Amount;
    }

    public void setService_Amount(String service_Amount) {
        Service_Amount = service_Amount;
    }
}



