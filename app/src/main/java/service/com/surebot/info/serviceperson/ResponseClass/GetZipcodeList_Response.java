package service.com.surebot.info.serviceperson.ResponseClass;

public class GetZipcodeList_Response {
    private  GetZipcodeList_Details[] get_zipcode_response;

    public GetZipcodeList_Details[] getGet_zipcode_response() {
        return get_zipcode_response;
    }

    public void setGet_zipcode_response(GetZipcodeList_Details[] get_zipcode_response) {
        this.get_zipcode_response = get_zipcode_response;
    }

    public class GetZipcodeList_Details {

        private String Zipcode_ID;

        private String Zipcode;

        private String Area;

        private String City_ID;

        private String State_ID;

        private String Country_ID;

        private String Longitude;

        private String Latitude;

        public String getZipcode_ID() {
            return Zipcode_ID;
        }

        public void setZipcode_ID(String zipcode_ID) {
            Zipcode_ID = zipcode_ID;
        }

        public String getZipcode() {
            return Zipcode;
        }

        public void setZipcode(String zipcode) {
            Zipcode = zipcode;
        }

        public String getArea() {
            return Area;
        }

        public void setArea(String area) {
            Area = area;
        }

        public String getCity_ID() {
            return City_ID;
        }

        public void setCity_ID(String city_ID) {
            City_ID = city_ID;
        }

        public String getState_ID() {
            return State_ID;
        }

        public void setState_ID(String state_ID) {
            State_ID = state_ID;
        }

        public String getCountry_ID() {
            return Country_ID;
        }

        public void setCountry_ID(String country_ID) {
            Country_ID = country_ID;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String longitude) {
            Longitude = longitude;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String latitude) {
            Latitude = latitude;
        }
    }

}
