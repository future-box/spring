package com.sample.dao;

import com.sample.domain.User;
import java.util.List;

public interface UserDao {

    void add(User user);

    User get(String id);

    List<User> get();

    void delete(String id);

    void delete();

    int getCount();

    void update(User user);
}
