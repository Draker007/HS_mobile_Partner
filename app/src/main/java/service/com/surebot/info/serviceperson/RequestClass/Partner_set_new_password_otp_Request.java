package service.com.surebot.info.serviceperson.RequestClass;

public class Partner_set_new_password_otp_Request {
    String User_ID,Otp_code,docket;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getOtp_code() {
        return Otp_code;
    }

    public void setOtp_code(String otp_code) {
        Otp_code = otp_code;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}
