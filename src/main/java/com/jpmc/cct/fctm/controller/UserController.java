package com.jpmc.cct.fctm.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpmc.cct.fctm.model.UserRequest;
import com.jpmc.cct.fctm.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private Producer producer;

    @PostMapping("publish")
    public String publishAccounts(@RequestBody List<String> accounts) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        UserRequest userRequest = new UserRequest();
        userRequest.setAccounts(accounts);
        userRequest.setUuid(UUID.randomUUID().toString());
        String json = gson.toJson(userRequest);
        System.out.println("Request : " + json);
        producer.sendMessage(json);
        return "success";
    }

}
