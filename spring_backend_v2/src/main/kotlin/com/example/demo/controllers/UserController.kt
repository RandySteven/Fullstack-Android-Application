package com.example.demo.controllers

import com.example.demo.daos.UserDAO
import com.example.demo.models.User
import com.example.demo.repositories.UserRepository
import com.example.demo.utils.ResponseMapAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

class UserController(
    @Autowired
    val userRepository: UserRepository,

    @Autowired
    val responseMapAction: ResponseMapAction,

    var users : List<User>,

    var responseMap : Map<String, Any>,

    var response : ResponseEntity<Map<String, Any>>,

    var LOGGER : Logger = LoggerFactory.getLogger(UserController::class.java)

) : UserDAO {

    companion object{
        const val GET_ALL_USERS_ENDPOINT: String = "/"
        const val GET_USER_BY_ID_ENDPOINT : String = "/{id}"
        const val POST_USER_ENDPOINT : String = "/add-user"
        const val PATCH_USER_ENDPOINT : String = "/edit-user/{id}"
        const val DELETE_USER_ENDPOINT : String = "/delete-user/{id}"
    }

    override fun getAllUsers(): ResponseEntity<Map<String, Any>> {
        LOGGER.info("========== Get All Users ==========")
        LOGGER.info("==== url : http://localhost:8080/users$GET_ALL_USERS_ENDPOINT")
        users = userRepository.findAll()

        if(users.isEmpty()) {
            LOGGER.info("===== No Content ====")
            responseMapAction.responseMapping(responseMap, HttpStatus.NO_CONTENT, 204, "User still empty")
            LOGGER.info("==== responseMap : $responseMap")
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseMap)
        }else{
            responseMapAction.responseMapping(responseMap, HttpStatus.OK, 200, "Get all users")
            responseMap.getOrDefault("users", users)
            LOGGER.info("==== users : $users")
            LOGGER.info("==== responseMap : $responseMap")
            response = ResponseEntity.ok().body(responseMap)
        }

        LOGGER.info("==== RESPONSE : $response")

        return response
    }

    override fun getUserById(id : String): ResponseEntity<Map<String, Any>> {
        LOGGER.info("========== Get User By Id ==========")
        LOGGER.info("==== url : http://localhost:8080/users$GET_USER_BY_ID_ENDPOINT")
        LOGGER.info("==== requestId : $id")

        val user : User = userRepository.findById(id).get()
        LOGGER.info("==== user : $user")

        if(user.deletedAt != null || user != null){
            responseMapAction.responseMapping(responseMap, HttpStatus.OK, 200, "Get all users")
            responseMap.getOrDefault("user", user)
            LOGGER.info("==== responseMap : $responseMap")
            LOGGER.info("==== user : $user")
            response = ResponseEntity.ok().body(responseMap)
        }else{
            responseMapAction.responseMapping(responseMap, HttpStatus.NOT_FOUND, 404, "User is not found")
            LOGGER.info("==== responseMap : $responseMap")
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap)
        }

        LOGGER.info("===== RESPONSE : $response")

        return response
    }

    @GetMapping(value = [
        GET_ALL_USERS_ENDPOINT,
        GET_USER_BY_ID_ENDPOINT
    ])
    override fun getUser(@PathVariable(required = false) id: String): ResponseEntity<Map<String, Any>> {

        LOGGER.info("========= Get User Method =========")

        response = if(id == ""){
            LOGGER.info("========= Get All Users =========")
            getAllUsers()
        }else{
            LOGGER.info("========= Get User By Id =========")
            LOGGER.info("==== id : $id")
            getUserById(id)
        }

        return response
    }

    @PostMapping(POST_USER_ENDPOINT)
    override fun postUser(@RequestBody user: User): ResponseEntity<Map<String, Any>> {
        LOGGER.info("========== Post User ==========")
        LOGGER.info("==== url : http://localhost:8080/users$POST_USER_ENDPOINT")

        LOGGER.info("===== REQUEST body : $user")

        if(user == null){
            responseMapAction.responseMapping(responseMap, HttpStatus.BAD_REQUEST, 400, "Bad request")
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap)
        }else{
            user.createdAt = LocalDateTime.now()
            val postUser : User = userRepository.insert(user)
            LOGGER.info("===== postUser : $postUser")
            responseMapAction.responseMapping(responseMap, HttpStatus.CREATED, 201, "Add user success")
            responseMap.getOrDefault("postUser", postUser)
            LOGGER.info("==== responseMap : $responseMap")
            response = ResponseEntity.status(HttpStatus.CREATED).body(responseMap)
        }

        LOGGER.info("===== RESPONSE : $response")

        return response
    }

    @PatchMapping(PATCH_USER_ENDPOINT)
    override fun patchUser(@PathVariable id: String, @RequestBody user: User): ResponseEntity<Map<String, Any>> {
        LOGGER.info("========= Patch User =========")
        LOGGER.info("===== url : http://localhost:8080/users$PATCH_USER_ENDPOINT")
        LOGGER.info("==== id : $id")

        val findUser : User = userRepository.findById(id).get()
        LOGGER.info("===== Get User : $findUser")
        LOGGER.info("===== REQUEST user : $user")

        if(findUser.deletedAt != null || findUser != null){
            val oldUserName : String = findUser.userName
            findUser.userName = if(user.userName.isNullOrBlank()) oldUserName else user.userName
            val updateUser : User = userRepository.save(findUser)
            LOGGER.info("==== updateUser : $updateUser")
            responseMapAction.responseMapping(responseMap, HttpStatus.OK, 200, "User update success")
            responseMap.getOrDefault("updateUser", updateUser)
            LOGGER.info("==== responseMap : $responseMap")
            response = ResponseEntity.ok(responseMap)
        }else{
            responseMapAction.responseMapping(responseMap, HttpStatus.NOT_FOUND, 404, "User is not found")
            LOGGER.info("==== responseMap : $responseMap")
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap)
        }

        LOGGER.info("==== RESPONSE : $response")

        return response
    }

    @DeleteMapping(DELETE_USER_ENDPOINT)
    override fun deleteUser(@PathVariable id: String): ResponseEntity<Map<String, Any>> {
        LOGGER.info("======= Delete User =======")
        LOGGER.info("===== url : http://localhost:8080/users$DELETE_USER_ENDPOINT")

        val findUser : User = userRepository.findById(id).get()
        LOGGER.info("===== findUser : $findUser")

        if(findUser.deletedAt != null || findUser != null){
            findUser.deletedAt = LocalDateTime.now()
            val deleteUser : User = userRepository.save(findUser)
            LOGGER.info("==== deleteUser : $deleteUser")
            responseMapAction.responseMapping(responseMap, HttpStatus.OK, 200, "User deleted success")
            responseMap.getOrDefault("deleteUser", deleteUser)
            LOGGER.info("==== responseMap $responseMap")
            response = ResponseEntity.ok(responseMap)
        }else{
            responseMapAction.responseMapping(responseMap, HttpStatus.NOT_FOUND, 404, "User is not found")
            LOGGER.info("==== responseMap : $responseMap")
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap)
        }

        return response
    }

}