package com.andryha.storage.models;


import com.andryha.storage.connections.MySqlConnection;
import com.andryha.storage.controllers.GlobalController;

import java.sql.*;
import java.util.ArrayList;

public class Good {
    private int id;
    private String name;
    private String description;
    private double price;
    private double balance;
    private Subtype subtype;
    private String measure;

    public Good() {
    }

    public Good(int id, String name, String description, double price, double balance, Subtype subtype) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.balance = balance;
        this.subtype = subtype;
    }

    public Subtype getSubtype() {
        return subtype;
    }

    public void setSubtype(Subtype subtype) {
        this.subtype = subtype;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", balance=" + balance +
                ", subtype=" + subtype +
                ", measure='" + measure + '\'' +
                '}';
    }

    public static ArrayList<Good> getGoods(){

        try {
            Connection connection= MySqlConnection.openConnection();
            Statement statement= connection.createStatement();
            ResultSet result =statement.executeQuery("SELECT a.* , s.name_subcat FROM global_list a left join subcategory s on a.id_subcategory=s.id;");

            ArrayList<Good> listOfGoods = new ArrayList<>();
            while (result.next()){
                Good good= new Good();
                good.setId(result.getInt("id"));
                good.setName(result.getString("name"));
                good.setDescription(result.getString("description"));
                good.setPrice(result.getDouble("price"));
                good.setBalance(result.getDouble("balance"));
                good.setSubtype(new Subtype(result.getInt("id_subcategory") ,result.getString("name_subcat")));
                listOfGoods.add(good);
            }
            return listOfGoods;
        } catch (SQLException e) {
            System.out.println("ошибка в получении товара");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static ArrayList<Good> getGoods(int id) {
        try {
            Connection connection=MySqlConnection.openConnection();
            String sql="SELECT a.* , s.name_subcat FROM global_list a LEFT JOIN subcategory s on a.id_subcategory=s.id WHERE a.id= ?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            System.out.println(preparedStatement);
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet result=preparedStatement.executeQuery();
            ArrayList<Good> arrayListByID= new ArrayList<>();
            while (result.next()){
                Good good= new Good();
                good.setId(result.getInt("id"));
                good.setName(result.getString("name"));
                good.setDescription(result.getString("description"));
                good.setPrice(result.getDouble("price"));
                good.setBalance(result.getDouble("balance"));
                good.setSubtype(new Subtype(result.getInt("id_subcategory") ,result.getString("name_subcat")));
                arrayListByID.add(good);

            }
            return arrayListByID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ошибка получения товаров по ID");
            return new ArrayList<>();
        }
    }

    public static ArrayList<Good> getGoods(String fxname){
        try {
            Connection connection= MySqlConnection.openConnection();
            String sql="SELECT a.* , s.name_subcat FROM global_list a LEFT JOIN subcategory s on a.id_subcategory=s.id WHERE name LIKE ?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,fxname+"%");
            ResultSet resultSet=preparedStatement.executeQuery();
            ArrayList <Good> goodArrayListByName= new ArrayList<>();
            while (resultSet.next()){
                Good good= new Good();
                good.setId(resultSet.getInt("id"));
                good.setName(resultSet.getString("name"));
                good.setDescription(resultSet.getString("description"));
                good.setPrice(resultSet.getDouble("price"));
                good.setBalance(resultSet.getDouble("balance"));
                good.setSubtype(new Subtype(resultSet.getInt("id_subcategory") ,resultSet.getString("name_subcat")));
                goodArrayListByName.add(good);
            }
            return goodArrayListByName;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка получения списка товаров по имени");
            return new ArrayList<>();
        }
    }

    public void create(){
        try {
            Connection connection = MySqlConnection.openConnection();
            String sql = "INSERT INTO global_list VALUES(null, ?, ?, ?, ?,?)";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setDouble(3, price);
            statement.setDouble(4, balance);
            statement.setInt(5, subtype.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка создания товара");
            e.printStackTrace();
        }

    }

    public  void update(){
        try {
            Connection connection= MySqlConnection.openConnection();
            String sql="UPDATE global_list SET name = ?, description = ?, price = ?, balance = ?, id_subcategory=?  WHERE id = ?";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2, description);
            statement.setDouble(3, price);
            statement.setDouble(4, balance);
            statement.setInt(5, subtype.getId());
            statement.setInt(6, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {

        try {
            Connection connection= MySqlConnection.openConnection();
            String sql="DELETE FROM global_list WHERE id = ?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления товара");
            e.printStackTrace();
        }
    }


}
