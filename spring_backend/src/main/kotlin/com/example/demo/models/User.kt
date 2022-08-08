package com.example.demo.models

import org.hibernate.annotations.GenericGenerator
import org.springframework.lang.Nullable
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity(name = "user")
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(generator = "uuid")
    var id : String,
    var userName : String,
    var userPhone : Map<String, Sim>,

    var createdAt : LocalDateTime,
    @Nullable var updatedAt : LocalDateTime,
    @Nullable var deletedAt : LocalDateTime
)
