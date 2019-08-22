package service.com.surebot.info.serviceperson.ResponseClass;

public class NewRequestList_Response {
    private  NewRequestList_Response_Records[]  partner_my_task_details_response;

    public NewRequestList_Response_Records[] getPartner_my_task_details_response() {
        return partner_my_task_details_response;
    }

    public void setPartner_my_task_details_response(NewRequestList_Response_Records[] partner_my_task_details_response) {
        this.partner_my_task_details_response = partner_my_task_details_response;
    }

    public  class NewRequestList_Response_Records{
         private  String User_ID;
         private  String User_Name;
        private  String User_Full_Address;
        private  String Phone_location;

        private  String Booking_Date;
        private  String Transaction_ID;
        private  String Booking_Start_Time;
        private  String AddressID;

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



        public String getBooking_Date() {
            return Booking_Date;
        }

        public void setBooking_Date(String booking_Date) {
            Booking_Date = booking_Date;
        }

        public String getTransaction_ID() {
            return Transaction_ID;
        }

        public void setTransaction_ID(String transaction_ID) {
            Transaction_ID = transaction_ID;
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


    }
}


