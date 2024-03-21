package com.example.hwSeminarThree.repository;

import com.example.hwSeminarThree.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("repositoryArrayList")
public class UserRepositoryListInMemory implements IUserRepository {
    private List<User> users = new ArrayList<>();
    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
