package service.com.surebot.info.serviceperson.ResponseClass;

public class CustomerSupport_Response {

    private CustomerSupport_Records [] services_response;

    public CustomerSupport_Records[] getServices_response() {
        return services_response;
    }

    public void setServices_response(CustomerSupport_Records[] services_response) {
        this.services_response = services_response;
    }

    public class CustomerSupport_Records{
        private String Support_ID;

        private String Contact_NO;

        private String Email_ID;

        private String Office_Address;

        public String getSupport_ID() {
            return Support_ID;
        }

        public void setSupport_ID(String support_ID) {
            Support_ID = support_ID;
        }

        public String getContact_NO() {
            return Contact_NO;
        }

        public void setContact_NO(String contact_NO) {
            Contact_NO = contact_NO;
        }

        public String getEmail_ID() {
            return Email_ID;
        }

        public void setEmail_ID(String email_ID) {
            Email_ID = email_ID;
        }

        public String getOffice_Address() {
            return Office_Address;
        }

        public void setOffice_Address(String office_Address) {
            Office_Address = office_Address;
        }
    }
}


