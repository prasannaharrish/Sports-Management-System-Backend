package com.project.sportsManagement.views;

import com.project.sportsManagement.entity.Location;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class LocationField extends CustomField<Location> {

    private final TextArea institutionAddress = new TextArea("Institution Address");
    private final TextField district = new TextField("District");
    private final TextField state = new TextField("State");


    public LocationField() {
        institutionAddress.setWidth("100%");
        district.setWidth("100%");
        state.setWidth("100%");
        add(institutionAddress,district,state);
        district.getStyle().set("margin-right","25px");
    }

    @Override
    protected Location generateModelValue() {
        return new Location(institutionAddress.getValue(),district.getValue(),state.getValue());
    }

    @Override
    protected void setPresentationValue(Location location) {
        institutionAddress.setValue(location.getInstitutionAddress());
        district.setValue(location.getDistrict());
        state.setValue(location.getState());
    }


    public void setReadOnlyMode(boolean readOnly){
        institutionAddress.setReadOnly(readOnly);
        district.setReadOnly(readOnly);
        state.setReadOnly(readOnly);
    }
}
