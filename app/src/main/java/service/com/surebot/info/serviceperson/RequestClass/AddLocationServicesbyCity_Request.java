package service.com.surebot.info.serviceperson.RequestClass;

public class AddLocationServicesbyCity_Request {
    private String User_ID;
    private String docket;
    private String Service_ID;
    private String City_ID;
    private String Service_Amount;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
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

    public String getCity_ID() {
        return City_ID;
    }

    public void setCity_ID(String city_ID) {
        City_ID = city_ID;
    }

    public String getService_Amount() {
        return Service_Amount;
    }

    public void setService_Amount(String service_Amount) {
        Service_Amount = service_Amount;
    }
}


