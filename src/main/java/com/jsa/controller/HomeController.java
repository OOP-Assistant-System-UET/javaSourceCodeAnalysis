//package com.jsa.controller;
//
//import com.jsa.service.DatabaseManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
////@com.jsa.controller
//public class HomeController {
//   @Autowired
//    private DatabaseManager db;
//    @RequestMapping(value = "/home")
//    public String home() {
//        if(db.checkUser(db.getUserName(),db.getPassword()))
//           return "home";
//        return "redirect:/";
//    }
//}
