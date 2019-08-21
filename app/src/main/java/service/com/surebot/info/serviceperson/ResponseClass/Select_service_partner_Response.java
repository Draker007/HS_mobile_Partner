package service.com.surebot.info.serviceperson.ResponseClass;

public class Select_service_partner_Response {


    Category_Records[] category_response;
    Location_Records[] location_reponse;


    public Category_Records[] getCategory_response() {
        return category_response;
    }

    public void setCategory_response(Category_Records[] category_response) {
        this.category_response = category_response;
    }

    public Location_Records[] getLocation_reponse() {
        return location_reponse;
    }

    public void setLocation_reponse(Location_Records[] location_reponse) {
        this.location_reponse = location_reponse;
    }

    public class Location_Records{
        String Location_ID,Location_Name,City_ID,Location_Status,City_Name,City_Status;

        public String getLocation_ID() {
            return Location_ID;
        }

        public void setLocation_ID(String location_ID) {
            Location_ID = location_ID;
        }

        public String getLocation_Name() {
            return Location_Name;
        }

        public void setLocation_Name(String location_Name) {
            Location_Name = location_Name;
        }

        public String getCity_ID() {
            return City_ID;
        }

        public void setCity_ID(String city_ID) {
            City_ID = city_ID;
        }

        public String getLocation_Status() {
            return Location_Status;
        }

        public void setLocation_Status(String location_Status) {
            Location_Status = location_Status;
        }

        public String getCity_Name() {
            return City_Name;
        }

        public void setCity_Name(String city_Name) {
            City_Name = city_Name;
        }

        public String getCity_Status() {
            return City_Status;
        }

        public void setCity_Status(String city_Status) {
            City_Status = city_Status;
        }
    }


    public class Category_Records{
        String Category_ID,Category_Name;

        public String getCategory_ID() {
            return Category_ID;
        }

        public void setCategory_ID(String category_ID) {
            Category_ID = category_ID;
        }

        public String getCategory_Name() {
            return Category_Name;
        }

        public void setCategory_Name(String category_Name) {
            Category_Name = category_Name;
        }
    }

}
