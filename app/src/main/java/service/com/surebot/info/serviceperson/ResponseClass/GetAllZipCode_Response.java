package service.com.surebot.info.serviceperson.ResponseClass;

public class GetAllZipCode_Response {

    private  GetAllZipCode_Details[] list_zipcode_response;

    public GetAllZipCode_Details[] getList_zipcode_response() {
        return list_zipcode_response;
    }

    public void setList_zipcode_response(GetAllZipCode_Details[] list_zipcode_response) {
        this.list_zipcode_response = list_zipcode_response;
    }

    public class GetAllZipCode_Details {
        private  String Zipcode_ID;
        private  String Zipcode;

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
    }

}
