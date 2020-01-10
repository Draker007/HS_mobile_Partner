package service.com.surebot.info.serviceperson.ResponseClass;

public class NewRequestListDetails_Response {

   private NewRequestpersonalDetails_Records [] partner_user_details_response;
   private NewRequestserviceDetails_Records [] partner_service_details_response;

    public NewRequestpersonalDetails_Records[] getPartner_user_details_response() {
        return partner_user_details_response;
    }

    public void setPartner_user_details_response(NewRequestpersonalDetails_Records[] partner_user_details_response) {
        this.partner_user_details_response = partner_user_details_response;
    }

    public NewRequestserviceDetails_Records[] getPartner_service_details_response() {
        return partner_service_details_response;
    }

    public void setPartner_service_details_response(NewRequestserviceDetails_Records[] partner_service_details_response) {
        this.partner_service_details_response = partner_service_details_response;
    }

    public  class NewRequestserviceDetails_Records{
        private  String  Transaction_Partner_Quote_ID;
        private  String  Transaction_ID;
        private  String  Service_Mapping_ID;
        private  String  Category_Name;
        private  String  Service_Name;
        private  String  Sub_Service_Name;
        private  String  Quantity;
        private  String  Quantity_Amount;

        public String getTransaction_Partner_Quote_ID() {
            return Transaction_Partner_Quote_ID;
        }

        public void setTransaction_Partner_Quote_ID(String transaction_Partner_Quote_ID) {
            Transaction_Partner_Quote_ID = transaction_Partner_Quote_ID;
        }

        public String getTransaction_ID() {
            return Transaction_ID;
        }

        public void setTransaction_ID(String transaction_ID) {
            Transaction_ID = transaction_ID;
        }

        public String getService_Mapping_ID() {
            return Service_Mapping_ID;
        }

        public void setService_Mapping_ID(String service_Mapping_ID) {
            Service_Mapping_ID = service_Mapping_ID;
        }

        public String getCategory_Name() {
            return Category_Name;
        }

        public void setCategory_Name(String category_Name) {
            Category_Name = category_Name;
        }

        public String getService_Name() {
            return Service_Name;
        }

        public void setService_Name(String service_Name) {
            Service_Name = service_Name;
        }

        public String getSub_Service_Name() {
            return Sub_Service_Name;
        }

        public void setSub_Service_Name(String sub_Service_Name) {
            Sub_Service_Name = sub_Service_Name;
        }

        public String getQuantity() {
            return Quantity;
        }

        public void setQuantity(String quantity) {
            Quantity = quantity;
        }

        public String getQuantity_Amount() {
            return Quantity_Amount;
        }

        public void setQuantity_Amount(String quantity_Amount) {
            Quantity_Amount = quantity_Amount;
        }
    }


    public  class NewRequestpersonalDetails_Records{
        private  String  Transaction_Partner_Quote_ID;

        private  String User_Full_Address;

        private  String Phone_location;

        private  String Transaction_ID;

        private  String User_ID;


        private  String User_Name;

        private  String Booking_Date;

        private  String Booking_Start_Time;

        private  String AddressID;

        private  String Admin_fixed_charge;

        public String getTransaction_Partner_Quote_ID() {
            return Transaction_Partner_Quote_ID;
        }

        public void setTransaction_Partner_Quote_ID(String transaction_Partner_Quote_ID) {
            Transaction_Partner_Quote_ID = transaction_Partner_Quote_ID;
        }

        public String getUser_Full_Address() {
            return User_Full_Address;
        }

        public void setUser_Full_Address(String user_Full_Address) {
            User_Full_Address = user_Full_Address;
        }

        public String getPhone_location() {
            return Phone_location;
        }

        public void setPhone_location(String phone_location) {
            Phone_location = phone_location;
        }

        public String getTransaction_ID() {
            return Transaction_ID;
        }

        public void setTransaction_ID(String transaction_ID) {
            Transaction_ID = transaction_ID;
        }

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

        public String getBooking_Date() {
            return Booking_Date;
        }

        public void setBooking_Date(String booking_Date) {
            Booking_Date = booking_Date;
        }

        public String getBooking_Start_Time() {
            return Booking_Start_Time;
        }

        public void setBooking_Start_Time(String booking_Start_Time) {
            Booking_Start_Time = booking_Start_Time;
        }

        public String getAddressID() {
            return AddressID;
        }

        public void setAddressID(String addressID) {
            AddressID = addressID;
        }

        public String getAdmin_fixed_charge() {
            return Admin_fixed_charge;
        }

        public void setAdmin_fixed_charge(String admin_fixed_charge) {
            Admin_fixed_charge = admin_fixed_charge;
        }
    }
}


