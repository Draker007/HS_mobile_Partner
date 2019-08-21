package service.com.surebot.info.serviceperson.RequestClass;

public class Add_account_details_Request {
    String User_ID,docket,Bank_Name,Bank_Branch_Name,Account_Number,IFSC_Code,Account_Holder_Name;

    public String getAccount_Holder_Name() {
        return Account_Holder_Name;
    }

    public void setAccount_Holder_Name(String account_Holder_Name) {
        Account_Holder_Name = account_Holder_Name;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getDocket() {
        return docket;
    }

    public void setDocket(String docket) {
        this.docket = docket;
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
