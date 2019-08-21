package service.com.surebot.info.serviceperson.ResponseClass;

public class Partner_package_Response {
    Partner_package_records[] partner_package_response;

    public Partner_package_records[] getPartner_package_response() {
        return partner_package_response;
    }

    public void setPartner_package_response(Partner_package_records[] partner_package_response) {
        this.partner_package_response = partner_package_response;
    }

    public class Partner_package_records {
        String Package_ID,Services,Cost,Priority,Package_Status;

        public String getPackage_ID() {
            return Package_ID;
        }

        public void setPackage_ID(String package_ID) {
            Package_ID = package_ID;
        }

        public String getServices() {
            return Services;
        }

        public void setServices(String services) {
            Services = services;
        }

        public String getCost() {
            return Cost;
        }

        public void setCost(String cost) {
            Cost = cost;
        }

        public String getPriority() {
            return Priority;
        }

        public void setPriority(String priority) {
            Priority = priority;
        }

        public String getPackage_Status() {
            return Package_Status;
        }

        public void setPackage_Status(String package_Status) {
            Package_Status = package_Status;
        }
    }
}
