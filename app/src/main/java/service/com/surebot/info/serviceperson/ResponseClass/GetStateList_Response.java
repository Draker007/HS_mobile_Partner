package service.com.surebot.info.serviceperson.ResponseClass;

public class GetStateList_Response {

    //Old get_states_list_response []

    private  GetStateList_Details[] get_states_list_response;

    public GetStateList_Details[] getGet_states_list_response() {
        return get_states_list_response;
    }

    public void setGet_states_list_response(GetStateList_Details[] get_states_list_response) {
        this.get_states_list_response = get_states_list_response;
    }

    public class GetStateList_Details {

        private String State_ID;

        private String State_Code;

        private String State_Name;

        private String State_Name_Code;


        private String Country_Name;


        private String Country_ID;

        private String Partner_ID;

        private String Partner_name;

        private String Partner_alloted;


        public String getState_ID() {
            return State_ID;
        }

        public void setState_ID(String state_ID) {
            State_ID = state_ID;
        }

        public String getState_Code() {
            return State_Code;
        }

        public void setState_Code(String state_Code) {
            State_Code = state_Code;
        }

        public String getState_Name() {
            return State_Name;
        }

        public void setState_Name(String state_Name) {
            State_Name = state_Name;
        }

        public String getState_Name_Code() {
            return State_Name_Code;
        }

        public void setState_Name_Code(String state_Name_Code) {
            State_Name_Code = state_Name_Code;
        }

        public String getCountry_Name() {
            return Country_Name;
        }

        public void setCountry_Name(String country_Name) {
            Country_Name = country_Name;
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

        public String getPartner_name() {
            return Partner_name;
        }

        public void setPartner_name(String partner_name) {
            Partner_name = partner_name;
        }

        public String getPartner_alloted() {
            return Partner_alloted;
        }

        public void setPartner_alloted(String partner_alloted) {
            Partner_alloted = partner_alloted;
        }
    }
}
