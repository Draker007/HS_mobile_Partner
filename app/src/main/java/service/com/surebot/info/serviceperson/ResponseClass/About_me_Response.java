package service.com.surebot.info.serviceperson.ResponseClass;

public class About_me_Response {

    private   About_me_Records about_me_response;

    public About_me_Records getAbout_me_response() {
        return about_me_response;
    }

    public void setAbout_me_response(About_me_Records about_me_response) {
        this.about_me_response = about_me_response;
    }

    //2nd Class


    public class  About_me_Records {
        String UserAddInfoId,User_Intro,User_Experience;

        public String getUserAddInfoId() {
            return UserAddInfoId;
        }

        public void setUserAddInfoId(String userAddInfoId) {
            UserAddInfoId = userAddInfoId;
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
    }
}
