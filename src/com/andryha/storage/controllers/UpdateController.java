package com.andryha.storage.controllers;

import com.andryha.storage.connections.MySqlConnection;
import com.andryha.storage.models.Good;
import com.andryha.storage.models.Subtype;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class UpdateController {
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

    private Good good=null;

    public void initialize(){
        good=GlobalController.getUpddateGood();
        fxName.setText(good.getName());
        fxDescription.setText(good.getDescription());
        fxPrice.setText(String.valueOf(good.getPrice()));
        fxBalance.setText(String.valueOf(good.getBalance()));
//
        fxSubtype.getItems().addAll(Subtype.getSubtype());
        fxSubtype.setValue(good.getSubtype());

    }


    public void update(ActionEvent event) {
        good.setName(fxName.getText());
        good.setDescription(fxDescription.getText());
        good.setPrice(Double.parseDouble(fxPrice.getText()));
        good.setBalance(Double.parseDouble(fxBalance.getText()));
        good.setSubtype((Subtype) fxSubtype.getValue());
        good.update();
        GlobalController.getCreateStageOfUpddateOfGood().close();
    }
}
