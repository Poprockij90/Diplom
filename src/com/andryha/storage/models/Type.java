package com.andryha.storage.models;

import com.andryha.storage.connections.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class Type {
    private int id;
    private String name;

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Type() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*на всякий случай*/

    @Override
    public String toString() {
        return name;
    }

    public static ArrayList<Type> getType(){
        try {
            ArrayList<Type> listOfTypes= new ArrayList<>();
            Connection connection= MySqlConnection.openConnection();
            String sql="SELECT * FROM category";
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            while (resultSet.next()){
                listOfTypes.add(new Type(resultSet.getInt("id_category"),resultSet.getString("name_category")));
            }
            return listOfTypes;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка получения типов");
            return  new ArrayList<>();
        }
    }
    public void createCategory(){
        try {
            Connection connection= MySqlConnection.openConnection();
            String sql="INSERT INTO category VALUES (null, ?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка добавления категории в базу");
        }

    }
}
