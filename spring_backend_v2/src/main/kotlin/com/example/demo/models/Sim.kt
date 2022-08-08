package com.example.demo.models

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.mapping.Table

@Table("sims")
data class Sim(
    @Id
    var phoneNumber : String,
    var cardName : String
    )
