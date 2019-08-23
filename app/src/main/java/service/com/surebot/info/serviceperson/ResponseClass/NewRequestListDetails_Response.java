package service.com.surebot.info.serviceperson.ResponseClass;

public class NewRequestListDetails_Response {

    private NewRequestserviceDetails_Records [] partner_service_details_response;

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
}


