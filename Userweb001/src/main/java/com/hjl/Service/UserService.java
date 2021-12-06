package com.hjl.Service;

import com.hjl.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public Map getUserMap();
    public void createUser(User user);
    public User getUser(Long id);
    public void updateUser(Long id, User user);
    public void deleteUser(Long id);
}
