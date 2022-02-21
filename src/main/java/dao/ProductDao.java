package dao;

import entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface ProductDao {

    public List<Product> findAll();

    void save(Product productFromRequest);

}