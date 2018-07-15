package com.andryha.storage.controllers;

import com.andryha.storage.models.Good;
import com.andryha.storage.models.Subtype;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateController {

    @FXML
    public TextField fxName;
    @FXML
    public TextField fxDescription;
    @FXML
    public TextField fxPrice;
    @FXML
    public TextField fxBalance;
    @FXML
    public ComboBox fxSubtype;

    public void initialize() {
        fxSubtype.getItems().addAll(Subtype.getSubtype());
//
    }
    public void create(ActionEvent event) {
        Good good= new Good();
        good.setName(fxName.getText());
        good.setDescription(fxDescription.getText());
        good.setPrice(Double.parseDouble(fxPrice.getText()));
        good.setBalance(Double.parseDouble(fxBalance.getText()));
        good.setSubtype((Subtype) fxSubtype.getValue());
        good.create();
        GlobalController.getCreateStageOfCreateOfGood().close();
    }
}
