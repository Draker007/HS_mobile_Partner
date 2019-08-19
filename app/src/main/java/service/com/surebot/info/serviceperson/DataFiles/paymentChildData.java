package service.com.surebot.info.serviceperson.DataFiles;

public class paymentChildData {
    String serviceName,serviceDesc,Cost;

    public paymentChildData(String serviceName, String serviceDesc, String cost) {
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
        Cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }
}
