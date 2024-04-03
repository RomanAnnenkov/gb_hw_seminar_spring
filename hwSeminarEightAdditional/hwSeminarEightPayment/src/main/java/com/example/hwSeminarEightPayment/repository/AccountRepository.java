package com.example.hwSeminarEightPayment.repository;

import com.example.hwSeminarEightPayment.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class AccountRepository {

    private JdbcTemplate jdbcTemplate;

    public Account findAccountById(long id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), id);
    }

    public List<Account> findAllAccounts() {
        String sql = "SELECT * FROM accounts";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    public void changeAmount(long id, BigDecimal amount) {
        String sql = "UPDATE accounts SET account_amount = ? WHERE id = ?";
        jdbcTemplate.update(sql, amount, id);
    }

    private class AccountRowMapper implements RowMapper<Account> {

        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account extractedAccount = new Account();
            extractedAccount.setId(rs.getLong("id"));
            extractedAccount.setName(rs.getString("account_name"));
            extractedAccount.setAmount(rs.getBigDecimal("account_amount"));
            return extractedAccount;
        }
    }
    
    
}

