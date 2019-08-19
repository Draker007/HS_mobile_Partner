package service.com.surebot.info.serviceperson.utils;

public class SelectSubServiceCheckbox {

    private boolean isSelected;
    private String subServiceName;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSubServiceName() {
        return subServiceName;
    }

    public void setSubServiceName(String subServiceName) {
        this.subServiceName = subServiceName;
    }
}
