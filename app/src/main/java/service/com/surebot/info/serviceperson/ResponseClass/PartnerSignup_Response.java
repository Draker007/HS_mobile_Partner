package service.com.surebot.info.serviceperson.ResponseClass;

public class PartnerSignup_Response {
    String User_Name,User_Role,User_ID,Category_ID,register_response,Status_Response;

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
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

    public String getCategory_ID() {
        return Category_ID;
    }

    public void setCategory_ID(String category_ID) {
        Category_ID = category_ID;
    }

    public String getRegister_response() {
        return register_response;
    }

    public void setRegister_response(String register_response) {
        this.register_response = register_response;
    }

    public String getStatus_Response() {
        return Status_Response;
    }

    public void setStatus_Response(String status_Response) {
        Status_Response = status_Response;
    }
}
