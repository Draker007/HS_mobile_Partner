package service.com.surebot.info.serviceperson.RequestClass;

public class ChangePasswordRequest {

    String User_ID,Password,docket, Current_Password;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }

    public String getCurrent_Password() {
        return Current_Password;
    }

    public void setCurrent_Password(String current_Password) {
        Current_Password = current_Password;
    }
}
