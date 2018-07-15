package com.andryha.storage.controllers;

import com.andryha.storage.models.Good;
import com.andryha.storage.models.Subtype;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GlobalController {
    @FXML
    public TableView listOfAllGoods;
    @FXML
    public TableColumn fxName;
    @FXML
    public TableColumn fxPrice;
    @FXML
    public TableColumn fxId;
    @FXML
    public TableColumn fxDescription;
    @FXML
    public TableColumn fxSubtype;

    @FXML
    public TextField searchByID;
    @FXML
    public TextField searchByName;

//    создаем скрытые окна и НЕвыбранный товар
    private static Stage createStageOfCreateOfGood=null;
    private static Stage createStageOfUpddateOfGood=null;
    private static Stage createStageOfSubcat=null;
    private static Good upddateGood = null;
/* удалить по ненадобности*/
    public static Stage getCreateStageOfCreateOfGood() {
        return createStageOfCreateOfGood;
    }

    public static Stage getCreateStageOfUpddateOfGood() {
        return createStageOfUpddateOfGood;
    }

    public static Stage getCreateStageOfSubcat() {
        return createStageOfSubcat;
    }

    public static Good getUpddateGood() {
        return upddateGood;
    }

    /*метод типо конструктора при запуске окна*/
    public void initialize() {
        tableInitialize();

    }

    private void tableInitialize() {
        fxId.setCellValueFactory(new PropertyValueFactory<Good,Integer>("id"));
        fxName.setCellValueFactory(new PropertyValueFactory<Good, String>("name"));
        fxDescription.setCellValueFactory(new PropertyValueFactory<Good, String>("description"));
        fxPrice.setCellValueFactory(new PropertyValueFactory<Good, Integer>("price"));
        fxSubtype.setCellValueFactory(new PropertyValueFactory<Good,String>("subtype"));

//        listOfAllGoods.setItems(FXCollections.observableList(Good.getGoods()));

        show();



    }

    private void show() {
        listOfAllGoods.getItems().clear();
        listOfAllGoods.getItems().addAll(Good.getGoods());
    }
    private void show(int id) {

        listOfAllGoods.getItems().clear();
        listOfAllGoods.getItems().addAll(Good.getGoods(id));
    }

    private void show(String name) {
        listOfAllGoods.getItems().clear();
        listOfAllGoods.getItems().addAll(Good.getGoods(name));
    }

    public void addGood(ActionEvent event) {

        try {
            createStageOfCreateOfGood= new Stage();
            Parent root= FXMLLoader.load(getClass().getResource("../views/createGoods.fxml"));
            createStageOfCreateOfGood.setTitle("создать товар");
            createStageOfCreateOfGood.setScene(new Scene(root, 400,400));
            createStageOfCreateOfGood.show();
            createStageOfCreateOfGood.setOnHiding (event1 -> {
                        show();
                    });

        } catch (IOException e) {
            System.out.println("Ошибка создания окна создания товара");
            e.printStackTrace();
        }

    }

    public void deleteGood(ActionEvent event) {
        Good forDelete = new Good();
        forDelete= (Good) listOfAllGoods.getSelectionModel().getSelectedItem();
        if (forDelete!=null){
            forDelete.delete();
            show();
        }
    }

    public void upgrateGood(ActionEvent event) {
        upddateGood= (Good) listOfAllGoods.getSelectionModel().getSelectedItem();
        if (upddateGood!=null){
            try {
                createStageOfUpddateOfGood=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../views/updateGoods.fxml"));

                createStageOfUpddateOfGood.setTitle("Редактировать");
                createStageOfUpddateOfGood.setScene(new Scene(root,300,400));
                createStageOfUpddateOfGood.show();
                createStageOfUpddateOfGood.setOnHiding(event1 -> {
                    show();
                });
            } catch (IOException e) {
                System.out.println("Ошибка создания окна редктирования");
                e.printStackTrace();
            }
        }
    }

    public void addSubtype(ActionEvent event) {

        try {
            createStageOfSubcat= new Stage();
            Parent root=FXMLLoader.load(getClass().getResource("../views/createSubcat.fxml"));
            createStageOfSubcat.setTitle("создать подкатегорию");
            createStageOfSubcat.setScene(new Scene(root, 400, 400));
            createStageOfSubcat.show();
            createStageOfSubcat.setOnHiding(event1 ->{
                show();
            } );
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ошибка создания окна создания подкатегории");
        }


    }

    public void searchByID(KeyEvent keyEvent) {

        int id=Integer.parseInt(searchByID.getText());
        String s=searchByID.getText();

        if (s.equalsIgnoreCase("")){
            show();
        }else {
            show(id);
        }
    }



    public void searchByName(KeyEvent keyEvent) {
        String name=searchByName.getText();
        if (name==""){
            show();
            System.out.println("if");
        }else {
            show(name);
            System.out.println("else");
        }
    }


}
