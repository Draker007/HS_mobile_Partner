package service.com.surebot.info.serviceperson.ResponseClass;

public class ChangePasswordResponse {

    String UserName,User_Role,User_ID,Status_Response,partner_change_password_response;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUser_Role() {
        return User_Role;
    }

    public void setUser_Role(String user_Role) {
        User_Role = user_Role;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getStatus_Response() {
        return Status_Response;
    }

    public void setStatus_Response(String status_Response) {
        Status_Response = status_Response;
    }

    public String getPartner_change_password_response() {
        return partner_change_password_response;
    }

    public void setPartner_change_password_response(String partner_change_password_response) {
        this.partner_change_password_response = partner_change_password_response;
    }
}
