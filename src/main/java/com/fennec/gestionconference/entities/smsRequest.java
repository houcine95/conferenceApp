package com.fennec.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class smsRequest {

    private String source_address;
    private String destination_address;
    private String content;
}
