package service.com.surebot.info.serviceperson.DataFiles;

import java.util.ArrayList;
import java.util.List;

public class paymentParentData {
    String id,name,paidMoney,date,time,total;
    List<paymentChildData> paymentChildData = new ArrayList<>();

    public paymentParentData(String id, String name, String paidMoney, String date, String time, String total, List<paymentChildData> paymentChildData) {
        this.id = id;
        this.name = name;
        this.paidMoney = paidMoney;
        this.date = date;
        this.time = time;
        this.total = total;
        this.paymentChildData.addAll(paymentChildData);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(String paidMoney) {
        this.paidMoney = paidMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<paymentChildData> getPaymentChildData() {
        return paymentChildData;
    }

    public void setPaymentChildData(List<paymentChildData> paymentChildData) {
        this.paymentChildData = paymentChildData;
    }
}
