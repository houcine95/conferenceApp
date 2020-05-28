package com.fennec.gestionconference.controllers;

import com.fennec.gestionconference.services.smsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class smsController {

    @Autowired
    private smsSenderService smsSenderService;

    @GetMapping(path = "/sendSms")
    @ResponseBody
    public String sendSms(){
        return smsSenderService.sendSms();
    }
}
