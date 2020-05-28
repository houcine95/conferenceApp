package com.fennec.gestionconference.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor  @AllArgsConstructor
public class smsResponse implements Serializable {

     private String object_type;
     private String id;
     private String source_address;
     private String source_country;
     private String destination_address;
     private String destination_country;
     private String direction;
     private String body;
     private int encoding;
     private String delivery_state;
     private String created;
     private String updated;
     private String expires;
     private float cost_per_fragment;
     private int fragment_count;
     private float total_cost;
     private String custom_args;
}
