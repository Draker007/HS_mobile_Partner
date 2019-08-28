package service.com.surebot.info.serviceperson.utils;

public class SendquotetoUser {
   public static String subServicename;
    public static String partnermaping_Id;
    public static String quantity;



    public SendquotetoUser(String subServicename,String partnermaping_Id,String  quantity){
        this.subServicename=subServicename;
        this.partnermaping_Id=partnermaping_Id;
        this.quantity=quantity;

    }

    public String getSubServicename() {
        return subServicename;
    }

    public void setSubServicename(String subServicename) {
        this.subServicename = subServicename;
    }

    public String getPartnermaping_Id() {
        return partnermaping_Id;
    }

    public void setPartnermaping_Id(String partnermaping_Id) {
        this.partnermaping_Id = partnermaping_Id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    /* public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPartnermaping_Id() {
        return partnermaping_Id;
    }

    public void setPartnermaping_Id(String partnermaping_Id) {
        this.partnermaping_Id = partnermaping_Id;
    }

    public String getSubServicename() {
        return subServicename;
    }

    public void setSubServicename(String subServicename) {
        this.subServicename = subServicename;
    }*/
}
