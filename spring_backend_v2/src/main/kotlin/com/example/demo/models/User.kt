package com.example.demo.models

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.lang.Nullable
import java.time.LocalDateTime

@Table("users")
data class User(
    @Id
    var id : String,
    var userName : String,
    var phoneNumber : Map<String, Sim>,
    var contacts : List<Contact>,

    var createdAt : LocalDateTime,
    @Nullable var updatedAt : LocalDateTime,
    @Nullable var deletedAt : LocalDateTime
)
