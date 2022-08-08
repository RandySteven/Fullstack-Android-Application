package com.example.demo.dtos

import com.example.demo.models.Contact
import com.example.demo.models.Sim
import org.springframework.lang.Nullable
import java.time.LocalDateTime

data class ContactDTO (
    var fullName : String,
    var phoneNumber : Map<String, Sim>,
    var hateoasUrl : String

    ){
    constructor(contact : Contact) : this(
        fullName = contact.firstName + " " + contact.lastName,
        phoneNumber = contact.phoneNumber,
        hateoasUrl = "http://localhost:8080/contacts/${contact.id}"
    )
}