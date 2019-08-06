package service.com.surebot.info.serviceperson.ResponseClass;

public class ListOfServices_Response {
    private  ListOfServices_Records[] services_response;

    public ListOfServices_Records[] getServices_response() {
        return services_response;
    }

    public void setServices_response(ListOfServices_Records[] services_response) {
        this.services_response = services_response;
    }

    public class ListOfServices_Records{
        private String Service_Mapping_ID;

        private String Service_ID;

        private String Service_Name;

        private  String Service_path;


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

        public String getService_path() {
            return Service_path;
        }

        public void setService_path(String service_path) {
            Service_path = service_path;
        }
    }
}
