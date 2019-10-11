package service.com.surebot.info.serviceperson.ResponseClass;

public class GetCityList_Response {

    private GetCityList_Details[] get_cities_list_response;

    public GetCityList_Details[] getGet_cities_list_response() {
        return get_cities_list_response;
    }

    public void setGet_cities_list_response(GetCityList_Details[] get_cities_list_response) {
        this.get_cities_list_response = get_cities_list_response;
    }

    public class GetCityList_Details {

        private String City_ID;

        private String City_Name;

        private String State_ID;

        private String State_Name;

        public String getCity_ID() {
            return City_ID;
        }

        public void setCity_ID(String city_ID) {
            City_ID = city_ID;
        }

        public String getCity_Name() {
            return City_Name;
        }

        public void setCity_Name(String city_Name) {
            City_Name = city_Name;
        }

        public String getState_ID() {
            return State_ID;
        }

        public void setState_ID(String state_ID) {
            State_ID = state_ID;
        }

        public String getState_Name() {
            return State_Name;
        }

        public void setState_Name(String state_Name) {
            State_Name = state_Name;
        }
    }
}
