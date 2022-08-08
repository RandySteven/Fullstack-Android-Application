package com.example.demo.enums

enum class ResponseMessage(message : String) {

    OK("OK"),
    POST("Created success"),
    NO_CONTENT("No content"),

    BAD_REQUEST("Invalid request"),
    UNAUTHORIZED("Unauthorized"),
    FORBIDDEN("Forbidden"),
    NOT_FOUND("Not found url");

    private var message : String = ""

    fun setMessage(message : String){
        this.message = message
    }
}