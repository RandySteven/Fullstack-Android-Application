package com.example.demo.daos

import com.example.demo.models.Contact
import org.springframework.http.ResponseEntity

interface ContactDAO {

    fun getAllContacts(userId : String) : ResponseEntity<Map<String, Any>>

    fun getContactById(userId : String, contactId : String) : ResponseEntity<Map<String, Any>>

    fun getContact(userId : String, contactId : String) : ResponseEntity<Map<String, Any>>

    fun addContact(userId : String, contact: Contact) : ResponseEntity<Map<String, Any>>

    fun editContact(userId : String, contactId : String, contact : Contact) : ResponseEntity<Map<String, Any>>

    fun deleteContact(userId : String, contactId : String) : ResponseEntity<Map<String, Any>>

}