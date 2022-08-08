package com.example.demo.daos

import com.example.demo.models.Contact
import org.springframework.http.ResponseEntity

interface ContactDAO {

    fun getAllContacts() : ResponseEntity<Map<String, Any>>

    fun getContactById(id : String) : ResponseEntity<Map<String, Any>>

    fun getContact(id : String) : ResponseEntity<Map<String, Any>>

    fun postContact() : ResponseEntity<Map<String, Any>>

    fun patchContact(id : String, contact : Contact) : ResponseEntity<Map<String, Any>>

    fun deleteContact(id : String) : ResponseEntity<Map<String, Any>>
}