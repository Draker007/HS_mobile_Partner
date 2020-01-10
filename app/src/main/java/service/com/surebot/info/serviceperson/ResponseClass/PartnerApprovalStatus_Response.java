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
        private String  Address_proof_details;
        private String  Identity_proof_details;
        private String  admin_partner_bank_account_status;

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

        public String getAddress_proof_details() {
            return Address_proof_details;
        }

        public void setAddress_proof_details(String address_proof_details) {
            Address_proof_details = address_proof_details;
        }

        public String getIdentity_proof_details() {
            return Identity_proof_details;
        }

        public void setIdentity_proof_details(String identity_proof_details) {
            Identity_proof_details = identity_proof_details;
        }

        public String getAdmin_partner_bank_account_status() {
            return admin_partner_bank_account_status;
        }

        public void setAdmin_partner_bank_account_status(String admin_partner_bank_account_status) {
            this.admin_partner_bank_account_status = admin_partner_bank_account_status;
        }
    }



}



