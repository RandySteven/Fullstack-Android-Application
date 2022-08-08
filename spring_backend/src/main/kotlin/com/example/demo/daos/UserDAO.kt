package com.example.demo.daos

import com.example.demo.models.User
import org.springframework.http.ResponseEntity

interface UserDAO {

    fun getAllUsers() : ResponseEntity<Map<String, Any>>

    fun getUserById(id : String) : ResponseEntity<Map<String, Any>>

    fun getUser(id : String) : ResponseEntity<Map<String, Any>>

    fun postUser(user: User) : ResponseEntity<Map<String, Any>>

    fun patchUser(id : String, user : User) : ResponseEntity<Map<String, Any>>

    fun deleteUser(id : String) : ResponseEntity<Map<String, Any>>
}