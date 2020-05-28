package com.fennec.gestionconference.services;

import com.fennec.gestionconference.entities.smsRequest;
import com.fennec.gestionconference.entities.smsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class smsSenderService {

    @Autowired
    RestTemplate restTemplate;

    String api_key = "a7b3c7b9c6b835ede0fdc8a8d6fae6f6";

    List<smsRequest> mesages = new ArrayList<>();

    public String sendSms(){
        // request url
        String url = "https://api.tm4b.com/v1/sms";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // authentication header
        headers.set("Authorization", "Bearer "+api_key);

        // create a post object
        smsRequest smsRequest = new smsRequest();
        smsRequest.setContent("Fichier des abonnées est integré dans la base de données avec success.");
        smsRequest.setDestination_address("+212639926736");
        smsRequest.setSource_address("ITAdmin");
        mesages.add(smsRequest);

        // request body parameters
        Map<String, Object> map = new HashMap<>();
        map.put("messages", mesages);


        Gson gsonBuilder = new GsonBuilder().create();
        String jsonFromJavaArrayList = gsonBuilder.toJson(map);
        //System.out.println(jsonFromJavaArrayList);

        // build the request
        HttpEntity<String> entity = new HttpEntity<>(jsonFromJavaArrayList, headers);


        System.out.println("---------------------------------------");
        System.out.println(" Body : "+entity.getBody());
        System.out.println(" Headers : "+entity.getHeaders());
        System.out.println("----------------------------------------");


        // send POST request
        //ResponseEntity<smsResponse> response = restTemplate.postForEntity(url, entity, smsResponse.class);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // check response
        if (((ResponseEntity) response).getStatusCode() == HttpStatus.CREATED) {
            System.out.println("Post Created");
            System.out.println(response.getBody());
            return "Success";
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            return "faild";
        }
    }

}
