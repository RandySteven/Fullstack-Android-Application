package com.example.demo.models

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.lang.Nullable
import java.time.LocalDateTime

@Table("contacts")
data class Contact(
    @Id
    var id : String,
    var firstName : String,
    var lastName : String,
    var phoneNumber : Map<String, Sim>,

    var createdAt : LocalDateTime,
    @Nullable var updatedAt : LocalDateTime,
    @Nullable var deletedAt : LocalDateTime
)