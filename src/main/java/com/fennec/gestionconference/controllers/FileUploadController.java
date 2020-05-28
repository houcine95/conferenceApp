package com.fennec.gestionconference.controllers;

import com.fennec.gestionconference.Utils;
import com.fennec.gestionconference.entities.upFileResponse;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileUploadController {

    private final Path root = Paths.get("./src/main/resources/Batch/InComing/");

    @PostMapping(path = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public /*ResponseEntity*/ upFileResponse uploadfile(@RequestParam("file") MultipartFile file) throws IOException, JSONException {

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(Date.from(Instant.now()));
            String result = String.format("-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.csv", cal);
            String fileName = file.getOriginalFilename() + Utils.getSaltString() + result;

            Files.copy(file.getInputStream(), this.root.resolve(fileName));


            //JSONObject obj = new JSONObject();
            //obj.put("message", "File uploaded successfully");

            upFileResponse response = new upFileResponse();
            response.setMessage("File uploaded successfully");

            //return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
            return response;
        }catch (Exception e){
            String exc = e.getMessage();
            upFileResponse response = new upFileResponse();
            response.setMessage("Erreur est surven");
            return response;
        }

    }



}
