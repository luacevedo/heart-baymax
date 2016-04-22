package com.example.luacevedo.heartbaymax.model.actions;

import com.example.luacevedo.heartbaymax.model.patient.PatientAttribute;

import java.util.List;

public class AddToListAction extends BaseAction<List<String>> {

    private String valueToAdd;

    public AddToListAction(String attributeRoot, String valueToAdd) {
        this.attributeRoot = attributeRoot;
        this.valueToAdd = valueToAdd;
    }

    @Override
    public void execute(PatientAttribute<List<String>> attribute) {
        attribute.getValue().add(valueToAdd);
    }
}
