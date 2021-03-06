package com.jsa.controller;

import com.jsa.model.RelationshipList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jsa.service.ParsePackage;

import java.io.IOException;

@Controller
public class DiagramController {

    @Autowired
    private ParsePackage parsePackage;

    @Autowired
    private RelationshipList rl;

    @RequestMapping(value = "/class_diagram", method = RequestMethod.GET)
    public String diagram() {
        return "diagram";
    }

//    @RequestMapping(value = "/redirect_page", method = RequestMethod.POST)
//    public String redirect() {
//        System.out.println("Redirecting Result To The Final Page");
//        return "redirect:class_diagram";
//    }


    @RequestMapping(value="/classes",  method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ParsePackage> response() throws IOException {
        return new ResponseEntity<>(parsePackage, HttpStatus.OK);
    }

    @RequestMapping(value="/relationships",  method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<RelationshipList> responseRela() throws IOException {
        return new ResponseEntity<>(rl, HttpStatus.OK);
    }

}

