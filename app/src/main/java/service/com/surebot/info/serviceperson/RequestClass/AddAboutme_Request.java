package service.com.surebot.info.serviceperson.RequestClass;

public class AddAboutme_Request {
 private String User_ID;

    private String User_Intro;

    private String User_Experience;

    private String docket;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getUser_Intro() {
        return User_Intro;
    }

    public void setUser_Intro(String user_Intro) {
        User_Intro = user_Intro;
    }

    public String getUser_Experience() {
        return User_Experience;
    }

    public void setUser_Experience(String user_Experience) {
        User_Experience = user_Experience;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}

