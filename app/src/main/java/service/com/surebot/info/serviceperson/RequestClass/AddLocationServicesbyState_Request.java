package service.com.surebot.info.serviceperson.RequestClass;

public class AddLocationServicesbyState_Request {
    private String User_ID;

    private String docket;

    private String Service_ID;

    private String State_ID;

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

    public String getState_ID() {
        return State_ID;
    }

    public void setState_ID(String state_ID) {
        State_ID = state_ID;
    }

    public String getService_Amount() {
        return Service_Amount;
    }

    public void setService_Amount(String service_Amount) {
        Service_Amount = service_Amount;
    }
}


