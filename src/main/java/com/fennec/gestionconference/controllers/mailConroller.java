package com.fennec.gestionconference.controllers;

import com.fennec.gestionconference.services.sendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mailConroller {

    @Autowired
    private sendMailService sendMailService;

    @GetMapping(path = "/sendMail")
    @ResponseBody
    public String sendMail(){
        sendMailService.sendEmail();
        return "done";
    }
}
