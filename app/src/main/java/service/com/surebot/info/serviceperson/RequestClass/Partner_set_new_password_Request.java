package service.com.surebot.info.serviceperson.RequestClass;

public class Partner_set_new_password_Request {
    String User_ID,User_Password,User_Email,docket;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getUser_Password() {
        return User_Password;
    }

    public void setUser_Password(String user_Password) {
        User_Password = user_Password;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String user_Email) {
        User_Email = user_Email;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}
