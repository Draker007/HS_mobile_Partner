package service.com.surebot.info.serviceperson.ResponseClass;

public class GetListofCountry_Response {

    private GetListofCountry_List[] get_country_list_response;

    private String service_status;

    public String getService_status() {
        return service_status;
    }

    public void setService_status(String service_status) {
        this.service_status = service_status;
    }

    public GetListofCountry_List[] getGet_country_list_response() {
        return get_country_list_response;
    }

    public void setGet_country_list_response(GetListofCountry_List[] get_country_list_response) {
        this.get_country_list_response = get_country_list_response;
    }

    public class GetListofCountry_List {

        private String Country_ID;

        private String Country_Code;

        private String Country_Name;

        private String Country_Name_Code;

        private String selectcountryhint="Select Country";

        public String getCountry_ID() {
            return Country_ID;
        }

        public void setCountry_ID(String country_ID) {
            Country_ID = country_ID;
        }

        public String getCountry_Code() {
            return Country_Code;
        }

        public void setCountry_Code(String country_Code) {
            Country_Code = country_Code;
        }

        public String getCountry_Name() {
            return Country_Name;
        }

        public void setCountry_Name(String country_Name) {
            Country_Name = country_Name;
        }

        public String getCountry_Name_Code() {
            return Country_Name_Code;
        }

        public void setCountry_Name_Code(String country_Name_Code) {
            Country_Name_Code = country_Name_Code;
        }

        public String getSelectcountryhint() {
            return selectcountryhint;
        }

        public void setSelectcountryhint(String selectcountryhint) {
            this.selectcountryhint = selectcountryhint;
        }
    }

}
