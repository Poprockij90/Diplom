package com.andryha.storage.models;

import com.andryha.storage.connections.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class Subtype {
    private int id;
    private String name;
    private  Type type;

    public Subtype(int id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Subtype(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subtype(String name) {
        this.name = name;
    }

//    public Subtype(int id) {
//        this.id = id;
//    }

    public Subtype() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return name;
    }

    public static ArrayList<Subtype> getSubtype(){

        try {
            ArrayList subtypes = new ArrayList();
            Connection connection= MySqlConnection.openConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM subcategory");
            while (resultSet.next()){
                subtypes.add(new Subtype(resultSet.getInt("id"),resultSet.getString("name_subcat")));
            }
            return subtypes;
        } catch (SQLException e) {
            System.out.println("ощибка создания списка подкатегорий");
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public void createSubtype(){
        try {
            Connection connection= MySqlConnection.openConnection();
            String sql="INSERT INTO subcategory VALUES(null, ?, ?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, type.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ощибка создания подкатегории");
        }
    }
}
