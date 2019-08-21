package service.com.surebot.info.serviceperson.ResponseClass;

public class Partner_payment_Response {
    Complete_transaction_record[] complete_transaction_response;

    public Complete_transaction_record[] getComplete_transaction_response() {
        return complete_transaction_response;
    }

    public void setComplete_transaction_response(Complete_transaction_record[] complete_transaction_response) {
        this.complete_transaction_response = complete_transaction_response;
    }

    public class Complete_transaction_record{
        String Transaction_ID,Total_Amount,Booking_Id,Service_Mapping_ID,Category_Name,service,Sub_Service_Name,Quantity,Quantity_Amount,Booking_Date,Booking_Start_Time;

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
    }
}
