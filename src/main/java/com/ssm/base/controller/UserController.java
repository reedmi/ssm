package com.ssm.base.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ssm.base.dao.UserMapper;
import com.ssm.base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by ReedMi on 2017/3/22.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users = userMapper.selectList(
                new EntityWrapper<User>());
        model.addAttribute("users", users);
        return "list";
    }

    @RequestMapping("/delete/{id}")
    public String save(@PathVariable Long id) {
        System.out.println(userMapper.deleteById(id));
        return "redirect:/users/list";
    }
}
