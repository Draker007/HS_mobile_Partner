package service.com.surebot.info.serviceperson.RequestClass;

public class SubmitForApproval_Request {

    private String User_ID;

    private String Service_Mapping_ID;

    private String Location_ID;

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

    public String getLocation_ID() {
        return Location_ID;
    }

    public void setLocation_ID(String location_ID) {
        Location_ID = location_ID;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}


