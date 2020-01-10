package service.com.surebot.info.serviceperson.RequestClass;

public class SubmitForApproval_Request {

    private String User_ID;
    private String Service_Mapping_ID;
    private String City;
    private String State;
    private String Zipcode;
    private String Radius_kms;
    private String Service_Amount;
    private String docket;


    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getService_Mapping_ID() {
        return Service_Mapping_ID;
    }

    public void setService_Mapping_ID(String service_Mapping_ID) {
        Service_Mapping_ID = service_Mapping_ID;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getRadius_kms() {
        return Radius_kms;
    }

    public void setRadius_kms(String radius_kms) {
        Radius_kms = radius_kms;
    }

    public String getService_Amount() {
        return Service_Amount;
    }

    public void setService_Amount(String service_Amount) {
        Service_Amount = service_Amount;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}


