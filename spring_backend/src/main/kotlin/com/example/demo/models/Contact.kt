package com.example.demo.models

import org.springframework.lang.Nullable
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.CollectionTable
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.MapKey
import javax.persistence.MapKeyColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Table(name = "contacts")
@Entity(name = "contact")
data class Contact(
    @Id
    @GeneratedValue(generator = "uuid")
    var id : String,
    var firstName : String,
    var lastName : String,
    var phoneNumber : Map<String, Sim>,

    var createdAt : LocalDateTime,
    @Nullable var updatedAt : LocalDateTime,
    @Nullable var deletedAt : LocalDateTime
)