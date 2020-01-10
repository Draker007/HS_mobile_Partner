package service.com.surebot.info.serviceperson.ResponseClass;

public class GetSubServicesByZipcode_Response {

    private  GetSubServicesByZipcode_Details[] partner_list_services_zipcode;

    public GetSubServicesByZipcode_Details[] getPartner_list_services_zipcode() {
        return partner_list_services_zipcode;
    }

    public void setPartner_list_services_zipcode(GetSubServicesByZipcode_Details[] partner_list_services_zipcode) {
        this.partner_list_services_zipcode = partner_list_services_zipcode;
    }

    public class GetSubServicesByZipcode_Details {
        private String Service_Mapping_ID;
        private String Service_ID;
        private String Service_Name;
        private String Service_Amount;
        private String Approval_Status;
        private String Zipcode;
        private String Zipcode_ID;

        public String getService_Mapping_ID() {
            return Service_Mapping_ID;
        }

        public void setService_Mapping_ID(String service_Mapping_ID) {
            Service_Mapping_ID = service_Mapping_ID;
        }

        public String getService_ID() {
            return Service_ID;
        }

        public void setService_ID(String service_ID) {
            Service_ID = service_ID;
        }

        public String getService_Name() {
            return Service_Name;
        }

        public void setService_Name(String service_Name) {
            Service_Name = service_Name;
        }

        public String getService_Amount() {
            return Service_Amount;
        }

        public void setService_Amount(String service_Amount) {
            Service_Amount = service_Amount;
        }

        public String getApproval_Status() {
            return Approval_Status;
        }

        public void setApproval_Status(String approval_Status) {
            Approval_Status = approval_Status;
        }

        public String getZipcode() {
            return Zipcode;
        }

        public void setZipcode(String zipcode) {
            Zipcode = zipcode;
        }

        public String getZipcode_ID() {
            return Zipcode_ID;
        }

        public void setZipcode_ID(String zipcode_ID) {
            Zipcode_ID = zipcode_ID;
        }
    }
}


