package service.com.surebot.info.serviceperson.ResponseClass;

public class Category_List_Response {

    private Category_List_Records [] category_response;

    public Category_List_Records[] getCategory_response() {
        return category_response;
    }

    public void setCategory_response(Category_List_Records[] category_response) {
        this.category_response = category_response;
    }

    public  class Category_List_Records{

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
