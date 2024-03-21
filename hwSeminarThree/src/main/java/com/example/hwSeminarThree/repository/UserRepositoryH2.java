package com.example.hwSeminarThree.repository;

import com.example.hwSeminarThree.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositoryH2")
public class UserRepositoryH2 implements IUserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUsers() {
        String sqlQuery = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (resultSet, rowNumber) -> {
            User extractedUser = new User();
            extractedUser.setName(resultSet.getString("name"));
            extractedUser.setAge(resultSet.getInt("age"));
            extractedUser.setEmail(resultSet.getString("email"));
            return extractedUser;
        };

        return jdbcTemplate.query(sqlQuery, userRowMapper);
    }

    @Override
    public void addUser(User user) {
        String sqlQuery = "INSERT INTO userTable (name, age, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, user.getName(), user.getAge(), user.getEmail());
    }
}
