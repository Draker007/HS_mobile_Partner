package service.com.surebot.info.serviceperson.ResponseClass;

public class About_me_Response {

    private   About_me_Records[] about_me_response;

    public About_me_Records[] getAbout_me_response() {
        return about_me_response;
    }

    public void setAbout_me_response(About_me_Records[] about_me_response) {
        this.about_me_response = about_me_response;
    }
    //2nd Class


    public class  About_me_Records {
        String About_ID,Introduction,Experience;

        public String getAbout_ID() {
            return About_ID;
        }

        public void setAbout_ID(String about_ID) {
            About_ID = about_ID;
        }

        public String getIntroduction() {
            return Introduction;
        }

        public void setIntroduction(String introduction) {
            Introduction = introduction;
        }

        public String getExperience() {
            return Experience;
        }

        public void setExperience(String experience) {
            Experience = experience;
        }
    }
}
