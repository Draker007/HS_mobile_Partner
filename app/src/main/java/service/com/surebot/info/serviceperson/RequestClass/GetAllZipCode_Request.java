package service.com.surebot.info.serviceperson.RequestClass;

public class GetAllZipCode_Request {
    private String User_ID;

    private String Country_ID;

    private String docket;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(String country_ID) {
        Country_ID = country_ID;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}

