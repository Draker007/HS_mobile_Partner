package service.com.surebot.info.serviceperson.ResponseClass;

public class GetSubServicesByCity_Response {

    private  GetSubServicesByCity_Details[] partner_list_services_city;

    public GetSubServicesByCity_Details[] getPartner_list_services_city() {
        return partner_list_services_city;
    }

    public void setPartner_list_services_city(GetSubServicesByCity_Details[] partner_list_services_city) {
        this.partner_list_services_city = partner_list_services_city;
    }

    public class GetSubServicesByCity_Details {

        private String Service_Mapping_ID;
        private String Service_ID;
        private String Service_Name;
        private String Service_Amount;
        private String Approval_Status;
        private String City_ID;

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

        public String getCity_ID() {
            return City_ID;
        }

        public void setCity_ID(String city_ID) {
            City_ID = city_ID;
        }
    }
}
