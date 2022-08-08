package com.example.demo.repositories

import com.example.demo.models.Contact
import com.joutvhu.dynamic.jpa.DynamicQuery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<Contact, String> {

    @DynamicQuery("SELECT * FROM contacts " +
            "JOIN users ON " +
            "users.id = contacts.user_id " +
            "WHERE users.id = :userId")
    fun findAll(userId : String): MutableList<Contact>
}