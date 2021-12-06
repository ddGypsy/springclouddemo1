package com.hjl.controller;

import com.hjl.Service.UserService;
import com.hjl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manageruser")
public class UserController {

    @Autowired
    UserService userService;

        @GetMapping("/")
        public String getUserList(Model model){
            Map map = userService.getUserMap();
            List<User> list = (List<User>)map.get("list");
            model.addAttribute("page",list);
            model.addAttribute("ProviderVersion",map.get("ProviderVersion"));
            return "user/list";
        }

    @RequestMapping("/toAdd")
    public String toAdd(User user){
        return "user/userAdd";
    }

    @PostMapping("/add")
    public String createUser(User user){
        userService.createUser(user);
        return "redirect:/manageruser/";
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model, @PathVariable("id")Long id){
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user){
        userService.updateUser(user.getId(),user);
        return "redirect:/manageruser/";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id")Long id){
        userService.deleteUser(id);
        return "redirect:/manageruser/";
    }
}
