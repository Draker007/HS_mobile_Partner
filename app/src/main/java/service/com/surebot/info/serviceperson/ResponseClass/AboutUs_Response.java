package service.com.surebot.info.serviceperson.ResponseClass;

public class AboutUs_Response {

    private  AboutUs_Record[] services_response;

    public AboutUs_Record[] getServices_response() {
        return services_response;
    }

    public void setServices_response(AboutUs_Record[] services_response) {
        this.services_response = services_response;
    }

    public class AboutUs_Record {

      private String About_ID;
        private String details;

        public String getAbout_ID() {
            return About_ID;
        }

        public void setAbout_ID(String about_ID) {
            About_ID = about_ID;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }
}
