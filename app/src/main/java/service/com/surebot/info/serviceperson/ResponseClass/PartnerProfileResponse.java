package service.com.surebot.info.serviceperson.ResponseClass;

public class PartnerProfileResponse {
    private PartnerProfileRecords [] partner_profile_details;
    private String Status_Response;

    public String getStatus_Response() {
        return Status_Response;
    }

    public void setStatus_Response(String status_Response) {
        Status_Response = status_Response;
    }

    public PartnerProfileRecords[] getPartner_profile_details() {
        return partner_profile_details;
    }

    public void setPartner_profile_details(PartnerProfileRecords[] partner_profile_details) {
        this.partner_profile_details = partner_profile_details;
    }

    public class PartnerProfileRecords{

        String User_ID,User_Name,   User_Contact_Number,User_Email,User_Gender,User_Image_Path,Category_ID, Category_Name,User_Full_Address;

        public String getUser_ID() {
            return User_ID;
        }

        public void setUser_ID(String user_ID) {
            User_ID = user_ID;
        }

        public String getUser_Name() {
            return User_Name;
        }

        public void setUser_Name(String user_Name) {
            User_Name = user_Name;
        }

        public String getUser_Contact_Number() {
            return User_Contact_Number;
        }

        public void setUser_Contact_Number(String user_Contact_Number) {
            User_Contact_Number = user_Contact_Number;
        }

        public String getUser_Email() {
            return User_Email;
        }

        public void setUser_Email(String user_Email) {
            User_Email = user_Email;
        }

        public String getUser_Gender() {
            return User_Gender;
        }

        public void setUser_Gender(String user_Gender) {
            User_Gender = user_Gender;
        }

        public String getUser_Image_Path() {
            return User_Image_Path;
        }

        public void setUser_Image_Path(String user_Image_Path) {
            User_Image_Path = user_Image_Path;
        }

        public String getCategory_ID() {
            return Category_ID;
        }

        public void setCategory_ID(String category_ID) {
            Category_ID = category_ID;
        }

        public String getCategory_Name() {
            return Category_Name;
        }

        public void setCategory_Name(String category_Name) {
            Category_Name = category_Name;
        }

        public String getUser_Full_Address() {
            return User_Full_Address;
        }

        public void setUser_Full_Address(String user_Full_Address) {
            User_Full_Address = user_Full_Address;
        }
    }
}
