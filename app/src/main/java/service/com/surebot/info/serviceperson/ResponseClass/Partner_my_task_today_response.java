package service.com.surebot.info.serviceperson.ResponseClass;

public class Partner_my_task_today_response {

    Partner_my_task_today_Records []  partner_my_task_today_response;

    public Partner_my_task_today_Records[] getPartner_my_task_today_response() {
        return partner_my_task_today_response;
    }

    public void setPartner_my_task_today_response(Partner_my_task_today_Records[] partner_my_task_today_response) {
        this.partner_my_task_today_response = partner_my_task_today_response;
    }

    public class Partner_my_task_today_Records {

        private String  Transaction_ID;

        private String  Booking_Id;

        private String  Booking_Start_Time;

        private String  Booking_Date;

        private String  AddressID;

        private String  Latitude_val;

        private String  Longitude_val;

        private String User_Full_Address;

        private String User_ID;

        private String User_Name;

        private String User_Contact_Number;

        private String Services;

        private String Sub_Services;

        public String getTransaction_ID() {
            return Transaction_ID;
        }

        public void setTransaction_ID(String transaction_ID) {
            Transaction_ID = transaction_ID;
        }

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

        public String getBooking_Date() {
            return Booking_Date;
        }

        public void setBooking_Date(String booking_Date) {
            Booking_Date = booking_Date;
        }

        public String getAddressID() {
            return AddressID;
        }

        public void setAddressID(String addressID) {
            AddressID = addressID;
        }

        public String getLatitude_val() {
            return Latitude_val;
        }

        public void setLatitude_val(String latitude_val) {
            Latitude_val = latitude_val;
        }

        public String getLongitude_val() {
            return Longitude_val;
        }

        public void setLongitude_val(String longitude_val) {
            Longitude_val = longitude_val;
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

        public String getServices() {
            return Services;
        }

        public void setServices(String services) {
            Services = services;
        }

        public String getSub_Services() {
            return Sub_Services;
        }

        public void setSub_Services(String sub_Services) {
            Sub_Services = sub_Services;
        }
    }
}


