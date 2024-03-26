package ru.gb.hwSeminarTwo.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.hwSeminarTwo.configuration.SqlQueryProperties;
import ru.gb.hwSeminarTwo.model.User;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SqlQueryProperties sqlQueryProperties;

    public List<User> findAll() {
        String sqlQuery = sqlQueryProperties.getSelect();

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
        String sqlQuery = sqlQueryProperties.getInsert();
        jdbcTemplate.update(sqlQuery, user.getFirstName(), user.getLastName());
        return user;
    }

    public void delete(int id) {
        String sqlQuery = sqlQueryProperties.getDelete();
        jdbcTemplate.update(sqlQuery, id);
    }

    public void update(User user) {
        String sqlQuery = sqlQueryProperties.getUpdate();
        jdbcTemplate.update(sqlQuery, user.getFirstName(), user.getLastName(), user.getId());
    }
}
