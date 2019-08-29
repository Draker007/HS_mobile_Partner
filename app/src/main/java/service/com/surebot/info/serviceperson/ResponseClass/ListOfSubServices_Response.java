package service.com.surebot.info.serviceperson.ResponseClass;

public class ListOfSubServices_Response {

    private ListOfSubServices_Records[] sub_services_response;

    public ListOfSubServices_Records[] getSub_services_response() {
        return sub_services_response;
    }

    public void setSub_services_response(ListOfSubServices_Records[] sub_services_response) {
        this.sub_services_response = sub_services_response;
    }

    public static class ListOfSubServices_Records {
        private String Service_Mapping_ID;

        private String Service_ID;

        private String Service_Name;

        private String Service_Description;

        private String Service_Amount;


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

        public String getService_Description() {
            return Service_Description;
        }

        public void setService_Description(String service_Description) {
            Service_Description = service_Description;
        }

        public String getService_Amount() {
            return Service_Amount;
        }

        public void setService_Amount(String service_Amount) {
            Service_Amount = service_Amount;
        }
    }
}

