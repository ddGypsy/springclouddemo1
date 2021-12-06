package com.hjl.Controller;

import com.hjl.Service.UserService;
import com.hjl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public Map<String,Object> getUsers(){
        HashMap<String, Object> map = new HashMap<>();
        List<User> userlist = userService.getUserlist();
        map.put("list",userlist);

        String ProviderVersion="用户服务User1：0.0.1";
        map.put("ProviderVersion",ProviderVersion);
        return map;
    }

    @PostMapping("/save")
    public String createUser(@RequestBody User user){
        userService.createUser(user);
        return "success";
    }

    @GetMapping("/get/{id}")
    public User findUser(@PathVariable("id") long id){
        return userService.getUser(id);
    }

    @PutMapping("/update/{id}")
    public String updateUser(@RequestBody User user,@PathVariable Long id){
        userService.updateUser(id,user);
        return "success";
    }

    @DeleteMapping("/delete/{id}")
    public String delUser(@PathVariable("id") long id){
        userService.delUser(id);
        return "success";
    }

    @GetMapping("/getVersion")
    public String getVersion(){
        return "用户服务提供者1";
    }
}
