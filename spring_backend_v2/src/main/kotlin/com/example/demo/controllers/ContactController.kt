package com.example.demo.controllers

import com.example.demo.daos.ContactDAO
import com.example.demo.dtos.ContactDTO
import com.example.demo.enums.ResponseCode
import com.example.demo.enums.ResponseMessage
import com.example.demo.models.Contact
import com.example.demo.models.User
import com.example.demo.repositories.ContactRepository
import com.example.demo.repositories.UserRepository
import com.example.demo.utils.ResponseMapAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ContactController(

    var user : User,

    @Autowired
    val contactRepository: ContactRepository,

    @Autowired
    val userRepository: UserRepository,

    @Autowired
    val responseMapAction: ResponseMapAction,

    var contacts : List<Contact>,

    var responseMap : Map<String, Any>,

    var response : ResponseEntity<Map<String, Any>>,

    val LOGGER : Logger = LoggerFactory.getLogger(ContactController::class.java)

) : ContactDAO {

    companion object{
        const val GET_ALL_CONTACTS_ENDPOINT : String = "/users/{userId}/contacts"
        const val GET_CONTACT_BY_ID_ENDPOINT : String = "/users/{userId}/contacts/{contactId}"
        const val ADD_CONTACT_ENDPOINT : String = "/users/{userId}/contacts/add-contact"
    }

    @GetMapping(
        value = [
            GET_ALL_CONTACTS_ENDPOINT,
            GET_CONTACT_BY_ID_ENDPOINT
        ]
    )
    override fun getContact(
        @PathVariable userId : String,
        @PathVariable(required = false) contactId : String
    ) : ResponseEntity<Map<String, Any>>{
        return if(contactId == null){
            getAllContacts(userId)
        }else{
            getContactById(userId, contactId)
        }
    }

    override fun getAllContacts(userId: String): ResponseEntity<Map<String, Any>> {
        LOGGER.info("=========== Get All Contacts ===========")
        LOGGER.info("===== url : http://localhost:8080/$GET_ALL_CONTACTS_ENDPOINT")
        LOGGER.info("===== userId : $userId")

        user = userRepository.findById(userId).get();
        LOGGER.info("====== user : $user")
        contacts = user.contacts
        LOGGER.info("===== contacts : $contacts")

        var getContacts : List<ContactDTO> = ArrayList<ContactDTO>();

        LOGGER.info("==== convert contact")
        contacts.forEach { contact ->
            if(contact.deletedAt != null){
                getContacts += (ContactDTO(contact))
            }
        }

        if(getContacts.isEmpty()){
            responseMapAction.responseMapping(responseMap, HttpStatus.NO_CONTENT, 204, "No contacts")
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseMap)
        }else{
            responseMapAction.responseMapping(responseMap, HttpStatus.OK, 200, "Get all contacts from user : ${user.userName}")
            responseMap.getOrDefault("contacts", getContacts)
            response = ResponseEntity.ok(responseMap)
        }

        LOGGER.info("======= RESPONSE : $response")

        return response
    }

    override fun getContactById(userId: String, contactId: String): ResponseEntity<Map<String, Any>> {
        TODO("Not yet implemented")
    }

    @PostMapping(ADD_CONTACT_ENDPOINT)
    override fun addContact(userId: String, contact: Contact): ResponseEntity<Map<String, Any>> {

        LOGGER.info("============ Post/Add Contact ============")
        LOGGER.info("===== url : http://localhost:8080$ADD_CONTACT_ENDPOINT")
        user = userRepository.findById(userId).get()

        val newContact : Contact = contactRepository.save(contact)

        user.contacts.toMutableList().add(newContact)

        var addContactUser = userRepository.save(user)

        return response
    }

    override fun editContact(userId: String, contactId: String, contact: Contact): ResponseEntity<Map<String, Any>> {
        TODO("Not yet implemented")
    }

    override fun deleteContact(userId: String, contactId: String): ResponseEntity<Map<String, Any>> {
        TODO("Not yet implemented")
    }
}