package com.example.demo.repositories

import com.example.demo.models.User
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import java.util.*

interface UserRepository : CassandraRepository<User, String> {

    @Query("SELECT * FROM users WHERE deletedAt = NULL")
    override fun findAll(): MutableList<User>

    @Query("SELECT * FROM users WHERE id = ?0 AND deleted = NULL")
    override fun findById(id: String): Optional<User>
}