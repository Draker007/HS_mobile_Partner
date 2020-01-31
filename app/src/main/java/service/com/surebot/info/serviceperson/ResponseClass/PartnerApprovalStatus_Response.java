package service.com.surebot.info.serviceperson.ResponseClass;

public class PartnerApprovalStatus_Response {

    private PartnerApprovalStatus[] partner_approval_status;

    public PartnerApprovalStatus[] getPartner_approval_status() {
        return partner_approval_status;
    }

    public void setPartner_approval_status(PartnerApprovalStatus[] partner_approval_status) {
        this.partner_approval_status = partner_approval_status;
    }

    public class PartnerApprovalStatus{

        private String  User_ID;
        private String  User_Name;


        private String  Address_proof_status;
        private String  Identity_Verfication_proof_status;
        private String  Bank_account_status;

        public String getUser_ID() {
            return User_ID;
        }

        public void setUser_ID(String user_ID) {
            User_ID = user_ID;
        }

        public String getUser_Name() {
            return User_Name;
        }

        public void setUser_Name(String user_Name) {
            User_Name = user_Name;
        }


        public String getAddress_proof_status() {
            return Address_proof_status;
        }

        public void setAddress_proof_status(String address_proof_status) {
            Address_proof_status = address_proof_status;
        }

        public String getIdentity_Verfication_proof_status() {
            return Identity_Verfication_proof_status;
        }

        public void setIdentity_Verfication_proof_status(String identity_Verfication_proof_status) {
            Identity_Verfication_proof_status = identity_Verfication_proof_status;
        }

        public String getBank_account_status() {
            return Bank_account_status;
        }

        public void setBank_account_status(String bank_account_status) {
            Bank_account_status = bank_account_status;
        }
    }



}



