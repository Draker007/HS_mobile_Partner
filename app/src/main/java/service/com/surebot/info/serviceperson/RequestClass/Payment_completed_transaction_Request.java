package service.com.surebot.info.serviceperson.RequestClass;

public class Payment_completed_transaction_Request {
    String User_ID,Partner_ID,docket;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getPartner_ID() {
        return Partner_ID;
    }

    public void setPartner_ID(String partner_ID) {
        Partner_ID = partner_ID;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}
