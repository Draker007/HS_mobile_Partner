package service.com.surebot.info.serviceperson.ResponseClass;

public class GetAddressProof_Response {


    private GetAddressProof_Details[] get_address_proof_details_response;

    public GetAddressProof_Details[] getGet_address_proof_details_response() {
        return get_address_proof_details_response;
    }

    public void setGet_address_proof_details_response(GetAddressProof_Details[] get_address_proof_details_response) {
        this.get_address_proof_details_response = get_address_proof_details_response;
    }

    public class GetAddressProof_Details {

        private String ID;
        private String User_ID;
        private String Document_Category_ID;
        private String Document_Name;


        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getUser_ID() {
            return User_ID;
        }

        public void setUser_ID(String user_ID) {
            User_ID = user_ID;
        }

        public String getDocument_Category_ID() {
            return Document_Category_ID;
        }

        public void setDocument_Category_ID(String document_Category_ID) {
            Document_Category_ID = document_Category_ID;
        }

        public String getDocument_Name() {
            return Document_Name;
        }

        public void setDocument_Name(String document_Name) {
            Document_Name = document_Name;
        }
    }
}
