package com.ecomm.ecommauth.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {

    /**
     * To create TraceId
     * @return TraceId
     */
    public static String createTraceId() {
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyyMMddHHmm");
        return "TRC" + dateTime.format(new Date());
    }

    /**
     * Generate random business key when Initiating AIR request
     * @return business Key
     */
    public static String createBusinessKey(){

        String businessKey = UUID.randomUUID().toString();
        return businessKey;
    }

    /**
     * to handle the Camunda calls
     * @return Http Headers
     */
    public static HttpHeaders getJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     *
     * @return current Date and time
     */
    public static LocalDateTime  createdDate() {
        LocalDateTime dateCreated = LocalDateTime.now();
        return dateCreated;
    }

    /**
     *
     * @param object
     * @return
     */
    public static String convertToString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return null;
        }
    }

    
}
