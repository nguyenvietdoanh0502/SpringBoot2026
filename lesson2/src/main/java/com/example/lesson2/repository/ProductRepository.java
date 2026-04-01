package com.example.lesson2.repository;

import com.example.lesson2.model.Product;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    public List<Product> getAllProducts(){
        String sql = "SELECT * FROM product p";
        List<Product> products = new ArrayList<>();
        try (
                Connection conn = JDBCUtils.connectionDB();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ResultSet res = ps.executeQuery(sql);
            while(res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                double price = res.getDouble("price");
                Product x = new Product(id,name,price);
                products.add(x);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
    public Product getProductById(int id){
        String sql = "SELECT * FROM product p WHERE id = ?";
        Product x = new Product();
        try (
                Connection conn = JDBCUtils.connectionDB();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1,id);
            ResultSet res = ps.executeQuery();
            if(res.next()){
                x.setId(id);
                x.setName(res.getString("name"));
                x.setPrice(res.getDouble("price"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return x;
    }
    public void addProduct(Product product){
        String sql = "INSERT INTO product (name,price) VALUES (?,?)";
        try (
                Connection conn = JDBCUtils.connectionDB();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1,product.getName());
            ps.setDouble(2,product.getPrice());
            ps.executeUpdate();

            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void updateProductById(Product product,int id){
        String sql = "UPDATE product SET name = ?, price = ? WHERE id = ?";
        try(
                Connection conn = JDBCUtils.connectionDB();
                PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ps.setString(1,product.getName());
            ps.setDouble(2,product.getPrice());
            ps.setInt(3,id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteProductById(int id){
        String sql = "DELETE FROM product WHERE id = ?";
        try(
                Connection conn = JDBCUtils.connectionDB();
                PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
