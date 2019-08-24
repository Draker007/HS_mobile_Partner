package service.com.surebot.info.serviceperson.ResponseClass;

public class PaymentReceived_Response {

    private  PaymentReceived_Records[] completed_payment_response;

    public PaymentReceived_Records[] getCompleted_payment_response() {
        return completed_payment_response;
    }

    public void setCompleted_payment_response(PaymentReceived_Records[] completed_payment_response) {
        this.completed_payment_response = completed_payment_response;
    }

    public class PaymentReceived_Records{

        private  String Transaction_ID;

        private  String Total_Amount;

        private  String Booking_Id;

        private  String Service_Mapping_ID;

        private  String Category_Name;

        private  String service;

        private  String Sub_Service_Name;

        private  String Booking_Date;


        public String getTransaction_ID() {
            return Transaction_ID;
        }

        public void setTransaction_ID(String transaction_ID) {
            Transaction_ID = transaction_ID;
        }

        public String getTotal_Amount() {
            return Total_Amount;
        }

        public void setTotal_Amount(String total_Amount) {
            Total_Amount = total_Amount;
        }

        public String getBooking_Id() {
            return Booking_Id;
        }

        public void setBooking_Id(String booking_Id) {
            Booking_Id = booking_Id;
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

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getSub_Service_Name() {
            return Sub_Service_Name;
        }

        public void setSub_Service_Name(String sub_Service_Name) {
            Sub_Service_Name = sub_Service_Name;
        }

        public String getBooking_Date() {
            return Booking_Date;
        }

        public void setBooking_Date(String booking_Date) {
            Booking_Date = booking_Date;
        }
    }
}



