package service.com.surebot.info.serviceperson.RequestClass;

public class    PartnerStartService_Request {

    private String User_ID;
    private String docket;
    private String Transaction_ID;
    private  String Status_ID;

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

    public String getTransaction_ID() {
        return Transaction_ID;
    }

    public void setTransaction_ID(String transaction_ID) {
        Transaction_ID = transaction_ID;
    }

    public String getStatus_ID() {
        return Status_ID;
    }

    public void setStatus_ID(String status_ID) {
        Status_ID = status_ID;
    }
}


