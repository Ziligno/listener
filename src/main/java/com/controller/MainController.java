/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.google.gson.Gson;
import com.service.ProcessSmsService;
import com.utils.pojo.SmsPojo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author drnefario
 */
@RestController
@RequestMapping("api")
public class MainController {

    static String FAILED_RESPONSE = "ERROR";

    @PostMapping("/logSms")
    public String creatToken(@RequestBody String request) throws Exception {
       
        System.out.println("Request Reached at -> " + request);
       SmsPojo smsPojo = new Gson().fromJson(request.toString(),SmsPojo.class);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now) + "<  --  >" + smsPojo.getMsisdn());
         System.out.println(dtf.format(now) + "<  --  >" + smsPojo.getSms());
          System.out.println(dtf.format(now) + "<  --  >" + smsPojo.getTransactionId());

        return now.toString();
    }

    @GetMapping("/getnumberlist/{msisdn}/{transactionId}/{sms}/{senderID}")
    public ResponseEntity<?> getSMS(@PathVariable String msisdn, @PathVariable String transactionId, @PathVariable String sms, @PathVariable String senderID) {
        System.out.println("variables =========================> " + msisdn + transactionId + sms + senderID);
        return new ProcessSmsService().procSms(msisdn, transactionId, sms, senderID);
    }
;

}
