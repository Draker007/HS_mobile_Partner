package service.com.surebot.info.serviceperson.RequestClass;

public class SendQuotetoUser_Request {

    private String User_ID;

    private String Quote_amount;

    private String Transaction_Partner_Quote_ID;

    private String docket;

    private String Status_ID;

    private  String Convenience_charge;

    private  String Partner_Quote;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getQuote_amount() {
        return Quote_amount;
    }

    public void setQuote_amount(String quote_amount) {
        Quote_amount = quote_amount;
    }

    public String getTransaction_Partner_Quote_ID() {
        return Transaction_Partner_Quote_ID;
    }

    public void setTransaction_Partner_Quote_ID(String transaction_Partner_Quote_ID) {
        Transaction_Partner_Quote_ID = transaction_Partner_Quote_ID;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }

    public String getStatus_ID() {
        return Status_ID;
    }

    public void setStatus_ID(String status_ID) {
        Status_ID = status_ID;
    }

    public String getConvenience_charge() {
        return Convenience_charge;
    }

    public void setConvenience_charge(String convenience_charge) {
        Convenience_charge = convenience_charge;
    }

    public String getPartner_Quote() {
        return Partner_Quote;
    }

    public void setPartner_Quote(String partner_Quote) {
        Partner_Quote = partner_Quote;
    }
}


