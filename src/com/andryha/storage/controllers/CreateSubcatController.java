package com.andryha.storage.controllers;

import com.andryha.storage.models.Subtype;
import com.andryha.storage.models.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateSubcatController {
    @FXML
    public ComboBox fxCategory;
    @FXML
    public TextField fxSubcat;
    public void initialize(){
        fxCategory.getItems().addAll(Type.getType());
    }

    public void createSubcat(ActionEvent event) {
        Subtype subtype= new Subtype();
        subtype.setName(fxSubcat.getText());
        subtype.setType((Type) fxCategory.getValue());
        subtype.createSubtype();
        GlobalController.getCreateStageOfSubcat().close();
    }
}
