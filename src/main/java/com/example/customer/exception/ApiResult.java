package com.example.customer.exception;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Getter
@Setter
public class ApiResult {

    private int Status;

    private String Path;

    private String Message;

    private String createdDate = nowDate();

    private Map<String, String> validationData;


    private String nowDate(){
        Locale locale = new Locale("tr", "TR");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", locale);
        Date date = new Date();
        String change = simpleDateFormat.format(date);
        return change;
    }

    public ApiResult(int status, String path, String message) {
        Status = status;
        Path = path;
        Message = message;
    }


}
