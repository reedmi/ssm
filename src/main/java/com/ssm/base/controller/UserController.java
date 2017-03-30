package com.ssm.base.controller;

import com.ssm.base.dao.UserMapper;
import com.ssm.base.model.User;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
    public String list(HttpServletRequest request, Model model) {
        List<User> users = userMapper.findAll();
        model.addAttribute("users", users);
        return "list";
    }

    @RequestMapping("/delete/{id}")
    public String save(@PathVariable Long id) {
        Integer ret = userMapper.delete(id);
        return "redirect:/users/list";
    }
}
