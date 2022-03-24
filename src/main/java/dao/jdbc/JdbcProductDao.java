package dao.jdbc;

import dao.ConnectionManager;
import dao.ProductDao;
import dao.jdbc.mapper.ProductRowMapper;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    private static final String FIND_ALL_SQL = "SELECT id, name, price, creation_date from products;";
    private static final String UPDATE_SQL = "UPDATE products SET name=?,price=? WHERE id=?";
    private static final String SAVE_SQL = "INSERT INTO products (name, price) VALUES (?,?);";
    private static final String FIND_BY_ID_SQL = "SELECT id, name, price, creation_date from products WHERE id=?;";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM products WHERE id=?;";


    public List<Product> findAll() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException exception) {
            throw new RuntimeException("Can't find products", exception);
        }

    }

    @Override
    public void save(Product product) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Can't save product", exception);
        }
    }

    @Override
    public void update(Product product) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Can't edit product with id " + product.getId(), exception);
        }
    }

    @Override
    public Product findById(int id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return PRODUCT_ROW_MAPPER.mapRow(resultSet);
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Can't find product by provided id", exception);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Can't delete product with by provided id ", exception);
        }
    }

}