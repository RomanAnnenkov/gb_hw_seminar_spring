package ru.gb.hwSeminarTwo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.hwSeminarTwo.model.User;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        String sqlQuery = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (resultSet, rowNumber) -> {
            User extractedUser = new User();
            extractedUser.setId(resultSet.getInt("id"));
            extractedUser.setFirstName(resultSet.getString("firstName"));
            extractedUser.setLastName(resultSet.getString("lastName"));
            return extractedUser;
        };

        return jdbcTemplate.query(sqlQuery, userRowMapper);
    }

    public User save(User user) {
        String sqlQuery = "INSERT INTO userTable (firstName, lastName) VALUES (?, ?)";
        jdbcTemplate.update(sqlQuery, user.getFirstName(), user.getLastName());
        return user;
    }

    public void delete(int id) {
        String sqlQuery = "DELETE FROM userTable WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
