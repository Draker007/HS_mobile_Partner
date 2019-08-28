package service.com.surebot.info.serviceperson.ResponseClass;

public class Account_details_Response {

    private account_details_records[] partner_account_details_response;

    public account_details_records[] getAccount_details_records() {
        return partner_account_details_response;
    }

    public void setAccount_details_records(account_details_records[] account_details_records) {
        this.partner_account_details_response = account_details_records;
    }


    //2nd Class
    public class account_details_records{

        String Bank_Name,Bank_Branch_Name,Account_Holder_Name,Account_Number,IFSC_Code,User_ID,Approval_Status;

        public String getUser_ID() {
            return User_ID;
        }

        public void setUser_ID(String user_ID) {
            User_ID = user_ID;
        }

        public String getApproval_Status() {
            return Approval_Status;
        }

        public void setApproval_Status(String approval_Status) {
            Approval_Status = approval_Status;
        }

        public String getBank_Name() {
            return Bank_Name;
        }

        public void setBank_Name(String bank_Name) {
            Bank_Name = bank_Name;
        }

        public String getBank_Branch_Name() {
            return Bank_Branch_Name;
        }

        public void setBank_Branch_Name(String bank_Branch_Name) {
            Bank_Branch_Name = bank_Branch_Name;
        }

        public String getAccount_Holder_Name() {
            return Account_Holder_Name;
        }

        public void setAccount_Holder_Name(String account_Holder_Name) {
            Account_Holder_Name = account_Holder_Name;
        }

        public String getAccount_Number() {
            return Account_Number;
        }

        public void setAccount_Number(String account_Number) {
            Account_Number = account_Number;
        }

        public String getIFSC_Code() {
            return IFSC_Code;
        }

        public void setIFSC_Code(String IFSC_Code) {
            this.IFSC_Code = IFSC_Code;
        }

    }


}
