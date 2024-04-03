package com.example.hwSeminarEightStore.repository;

import com.example.hwSeminarEightStore.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    public Product getProduct(long id) {
        String sqlQuery = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new ProductRowMapper(), id);
    }

    public List<Product> getProducts() {
        String sqlQuery = "SELECT * FROM products";
        return jdbcTemplate.query(sqlQuery, new ProductRowMapper());
    }

    public void updateProductQuantity(Long id, int quantity) {
        String sqlQuery = "UPDATE products SET product_quantity = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, quantity, id);
    }

    private static class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Product extractedProduct = new Product();
            extractedProduct.setId(resultSet.getLong("id"));
            extractedProduct.setName(resultSet.getString("product_name"));
            extractedProduct.setPrice(resultSet.getDouble("product_price"));
            extractedProduct.setQuantity(resultSet.getInt("product_quantity"));
            return extractedProduct;
        }
    }
}
