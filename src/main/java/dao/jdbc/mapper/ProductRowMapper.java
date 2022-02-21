package dao.jdbc.mapper;

import entity.Product;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProductRowMapper {

    public Product mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        double price = resultSet.getDouble("price");
        String name = resultSet.getString("name");
        Date creationDate = resultSet.getDate("creation_date");
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .creationDate(creationDate)
                .build();
    }
}
