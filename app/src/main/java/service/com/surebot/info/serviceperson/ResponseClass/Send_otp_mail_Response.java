package service.com.surebot.info.serviceperson.ResponseClass;

public class Send_otp_mail_Response {
    String notification_status_response,notification_code_response;

    public String getNotification_status_response() {
        return notification_status_response;
    }

    public void setNotification_status_response(String notification_status_response) {
        this.notification_status_response = notification_status_response;
    }

    public String getNotification_code_response() {
        return notification_code_response;
    }

    public void setNotification_code_response(String notification_code_response) {
        this.notification_code_response = notification_code_response;
    }
}
