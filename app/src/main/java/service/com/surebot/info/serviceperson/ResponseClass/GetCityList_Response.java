package service.com.surebot.info.serviceperson.ResponseClass;

public class GetCityList_Response {

    //Old get_cities_list_response



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

        private String Partner_alloted;

        private String Country_ID;

        private String Partner_ID;

        private String User_Name;


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

        public String getPartner_alloted() {
            return Partner_alloted;
        }

        public void setPartner_alloted(String partner_alloted) {
            Partner_alloted = partner_alloted;
        }

        public String getCountry_ID() {
            return Country_ID;
        }

        public void setCountry_ID(String country_ID) {
            Country_ID = country_ID;
        }

        public String getPartner_ID() {
            return Partner_ID;
        }

        public void setPartner_ID(String partner_ID) {
            Partner_ID = partner_ID;
        }

        public String getUser_Name() {
            return User_Name;
        }

        public void setUser_Name(String user_Name) {
            User_Name = user_Name;
        }
    }
}
