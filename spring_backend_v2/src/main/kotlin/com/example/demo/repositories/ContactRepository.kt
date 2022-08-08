package com.example.demo.repositories

import com.example.demo.models.Contact
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : CassandraRepository<Contact, String> {


}