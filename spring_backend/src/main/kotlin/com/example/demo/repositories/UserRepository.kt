package com.example.demo.repositories

import com.example.demo.models.User
import com.joutvhu.dynamic.jpa.DynamicQuery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, String> {

    @Query("SELECT u FROM users u WHERE u.deletedAt = NULL")
    override fun findAll(): MutableList<User>

    @DynamicQuery("SELECT * FROM users WHERE deletedAt = NULL AND id = :id")
    override fun findById(id: String): Optional<User>
}