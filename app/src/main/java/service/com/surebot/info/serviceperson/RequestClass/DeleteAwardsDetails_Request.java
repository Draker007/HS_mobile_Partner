package service.com.surebot.info.serviceperson.RequestClass;

public class DeleteAwardsDetails_Request {

    private String User_ID;

    private String Award_ID;

    private String docket;


    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getAward_ID() {
        return Award_ID;
    }

    public void setAward_ID(String award_ID) {
        Award_ID = award_ID;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
    }
}
