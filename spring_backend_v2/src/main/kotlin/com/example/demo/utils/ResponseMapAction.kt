package com.example.demo.utils

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ResponseMapAction {

    @Bean
    public open fun responseMapping(responseMap: Map<String, Any>, httpStatus : HttpStatus, httpCode : Int, responseMessage : String){
        responseMap.getOrDefault("httpStatus", httpStatus)
        responseMap.getOrDefault("httpCode", httpCode)
        responseMap.getOrDefault("responseMessage", responseMessage)
    }

}