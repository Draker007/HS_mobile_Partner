package service.com.surebot.info.serviceperson.RequestClass;

public class GetCityList_Request {
    private String User_ID;

    private String State_ID;

    private String docket;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getState_ID() {
        return State_ID;
    }

    public void setState_ID(String state_ID) {
        State_ID = state_ID;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}
