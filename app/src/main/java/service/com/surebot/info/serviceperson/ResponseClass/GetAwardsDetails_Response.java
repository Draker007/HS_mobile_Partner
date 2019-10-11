package service.com.surebot.info.serviceperson.ResponseClass;

public class GetAwardsDetails_Response {

   private GetAwards_Details[]  get_award_and_certificate_photos_details_response;

    public GetAwards_Details[] getGet_award_and_certificate_photos_details_response() {
        return get_award_and_certificate_photos_details_response;
    }

    public void setGet_award_and_certificate_photos_details_response(GetAwards_Details[] get_award_and_certificate_photos_details_response) {
        this.get_award_and_certificate_photos_details_response = get_award_and_certificate_photos_details_response;
    }

    public class GetAwards_Details {

        private String ID;
        private String User_ID;
        private String Document_Category_ID;
        private String Document_Name;
        private String Document_Description;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getUser_ID() {
            return User_ID;
        }

        public void setUser_ID(String user_ID) {
            User_ID = user_ID;
        }

        public String getDocument_Category_ID() {
            return Document_Category_ID;
        }

        public void setDocument_Category_ID(String document_Category_ID) {
            Document_Category_ID = document_Category_ID;
        }

        public String getDocument_Name() {
            return Document_Name;
        }

        public void setDocument_Name(String document_Name) {
            Document_Name = document_Name;
        }

        public String getDocument_Description() {
            return Document_Description;
        }

        public void setDocument_Description(String document_Description) {
            Document_Description = document_Description;
        }
    }


}
