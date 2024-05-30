/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service;

import com.utils.HTTPOST;
import com.utils.pojo.SmsPojo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author drnefario
 */
public class ProcessSmsService {

    public ResponseEntity<?> procSms(String msisdn, String transactionId, String sms, String senderID) {

        String jsonString = "{\"msisdn\":\"" + msisdn + "\"\"transactionId\":\"" + transactionId + "\""
                + "\"sms\":\"" + sms + "\"\"senderID\":\"" + senderID + "\"}";

       
        String url = "http://localhost:9999/api/logSms";

        try {
            HTTPOST.postRequest(url, jsonString);
        } catch (Exception ex) {
            System.out.println("Exception ex at -> " + ex.toString());
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
