package service.com.surebot.info.serviceperson.ResponseClass;

public class Partnerlogin_Response {
    String UserName,User_Role,User_ID,User_Premium,partner_login_response,Status_Response;

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

    public String getUser_Premium() {
        return User_Premium;
    }

    public void setUser_Premium(String user_Premium) {
        User_Premium = user_Premium;
    }

    public String getPartner_login_response() {
        return partner_login_response;
    }

    public void setPartner_login_response(String partner_login_response) {
        this.partner_login_response = partner_login_response;
    }

    public String getStatus_Response() {
        return Status_Response;
    }

    public void setStatus_Response(String status_Response) {
        Status_Response = status_Response;
    }
}
