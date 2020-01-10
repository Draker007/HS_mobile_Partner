package service.com.surebot.info.serviceperson.utils;

public class spinnerData {
    private String title;
    private boolean selected;

    private String StateId;

    private String CityId;

    private String PartnerStatus;


    private String State_status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getStateId() {
        return StateId;
    }

    public void setStateId(String stateId) {
        StateId = stateId;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
    }

    public String getState_status() {
        return State_status;
    }

    public void setState_status(String state_status) {
        State_status = state_status;
    }

    public String getPartnerStatus() {
        return PartnerStatus;
    }

    public void setPartnerStatus(String partnerStatus) {
        PartnerStatus = partnerStatus;
    }
}
