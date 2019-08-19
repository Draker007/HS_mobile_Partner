package service.com.surebot.info.serviceperson.ResponseClass;

public class Payment_completed_transaction_Response {
    private Completed_Payment_Records[] complete_transcation_response;

    public Completed_Payment_Records[] getComplete_transcation_response() {
        return complete_transcation_response;
    }

    public void setComplete_transcation_response(Completed_Payment_Records[] complete_transcation_response) {
        this.complete_transcation_response = complete_transcation_response;
    }

    private class Completed_Payment_Records {

        String Transaction_ID,Booking_Id,User_Name,Category_Name,Services,Total_Amount;

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

        public String getUser_Name() {
            return User_Name;
        }

        public void setUser_Name(String user_Name) {
            User_Name = user_Name;
        }

        public String getCategory_Name() {
            return Category_Name;
        }

        public void setCategory_Name(String category_Name) {
            Category_Name = category_Name;
        }

        public String getServices() {
            return Services;
        }

        public void setServices(String services) {
            Services = services;
        }

        public String getTotal_Amount() {
            return Total_Amount;
        }

        public void setTotal_Amount(String total_Amount) {
            Total_Amount = total_Amount;
        }
    }
}
