package com.example.demo.controllers

import com.example.demo.configs.ResponseMapAction
import com.example.demo.daos.ContactDAO
import com.example.demo.models.Contact
import com.example.demo.repositories.ContactRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contacts")
class ContactController(

    @Autowired
    val contactRepository: ContactRepository,

    @Autowired
    val responseMapAction: ResponseMapAction,

    var contacts : List<Contact>,

    var responseMap : Map<String, Any>,

    var response : ResponseEntity<Map<String, Any>>,

    var LOGGER : Logger = LoggerFactory.getLogger(ContactController::class.java)

) : ContactDAO {

    companion object{
        const val GET_ALL_CONTACTS_ENDPOINT : String= "/"
        const val GET_CONTACT_BY_ID_ENDPOINT : String = "/{id}"
        const val POST_CONTACT_ENDPOINT : String = "/add-contact"
        const val PATCH_CONTACT_BY_ID_ENDPOINT : String = "/update-contact/{id}"
        const val DELETE_CONTACT_BY_ID_ENDPOINT : String = "/delete-contact/{id}"
    }

    override fun getAllContacts(): ResponseEntity<Map<String, Any>> {
        return response
    }

    override fun getContactById(id: String): ResponseEntity<Map<String, Any>> {
        return response
    }

    @GetMapping(
        value = [
            GET_ALL_CONTACTS_ENDPOINT,
            GET_CONTACT_BY_ID_ENDPOINT
        ]
    )
    override fun getContact(id: String): ResponseEntity<Map<String, Any>> {
        response = if(id == null){
            getAllContacts()
        }else{
            getContactById(id)
        }
        return response
    }

    @PostMapping(POST_CONTACT_ENDPOINT)
    override fun postContact(): ResponseEntity<Map<String, Any>> {
        return response
    }

    @PatchMapping(PATCH_CONTACT_BY_ID_ENDPOINT)
    override fun patchContact(id: String, contact: Contact): ResponseEntity<Map<String, Any>> {
        return response
    }

    @DeleteMapping(DELETE_CONTACT_BY_ID_ENDPOINT)
    override fun deleteContact(id: String): ResponseEntity<Map<String, Any>> {
        return response
    }
}