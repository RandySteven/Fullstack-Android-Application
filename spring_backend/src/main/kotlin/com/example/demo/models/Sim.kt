package com.example.demo.models

import org.springframework.lang.Nullable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "sims")
@Entity(name = "sim")
data class Sim(
    @Id
    @GeneratedValue(generator = "uuid")
    var id : String,
    var simName : String,
    var simNumber : String
)
