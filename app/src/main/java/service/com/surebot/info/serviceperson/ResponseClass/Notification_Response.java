package service.com.surebot.info.serviceperson.ResponseClass;

public class Notification_Response {
    partner_services_notification_records[] partner_services_notification_response;

    public partner_services_notification_records[] getPartner_services_notification_response() {
        return partner_services_notification_response;
    }

    public void setPartner_services_notification_response(partner_services_notification_records[] partner_services_notification_response) {
        this.partner_services_notification_response = partner_services_notification_response;
    }

    public class partner_services_notification_records{
        String Booking_Id,Booking_Start_Time,Transaction_ID,AddressID,User_Full_Address,User_ID,User_Name,User_Contact_Number,Booking_date;

        public String getBooking_Id() {
            return Booking_Id;
        }

        public void setBooking_Id(String booking_Id) {
            Booking_Id = booking_Id;
        }

        public String getBooking_Start_Time() {
            return Booking_Start_Time;
        }

        public void setBooking_Start_Time(String booking_Start_Time) {
            Booking_Start_Time = booking_Start_Time;
        }

        public String getTransaction_ID() {
            return Transaction_ID;
        }

        public void setTransaction_ID(String transaction_ID) {
            Transaction_ID = transaction_ID;
        }

        public String getAddressID() {
            return AddressID;
        }

        public void setAddressID(String addressID) {
            AddressID = addressID;
        }

        public String getUser_Full_Address() {
            return User_Full_Address;
        }

        public void setUser_Full_Address(String user_Full_Address) {
            User_Full_Address = user_Full_Address;
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

        public String getUser_Contact_Number() {
            return User_Contact_Number;
        }

        public void setUser_Contact_Number(String user_Contact_Number) {
            User_Contact_Number = user_Contact_Number;
        }
    }
}
