package com.hjl.Service;

import com.hjl.pojo.User;

import java.util.List;

public interface UserService {

    public List<User> getUserlist();

    public void createUser(User user);

    public User getUser(Long id);

    public void updateUser(Long id,User user);

    public void delUser(Long id);
}
