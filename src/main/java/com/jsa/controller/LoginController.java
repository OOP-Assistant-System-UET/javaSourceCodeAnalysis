package com.jsa.controller;

import com.jsa.service.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private DatabaseManager db;

    @RequestMapping(value = "/home" +
            "", method = RequestMethod.POST)
    public String login(@RequestParam("userName")String userName, @RequestParam("password")String password){
        if(db.checkUser(userName, password)){
            System.out.println("tuananh a");
            return "home";
        }
        else{
            System.out.println("tuan anh b");
            return "redirect:/";
        }
    }
}
