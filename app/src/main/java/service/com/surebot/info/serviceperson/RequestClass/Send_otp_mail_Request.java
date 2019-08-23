package service.com.surebot.info.serviceperson.RequestClass;

public class Send_otp_mail_Request {


    String EmailId,docket;

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}
