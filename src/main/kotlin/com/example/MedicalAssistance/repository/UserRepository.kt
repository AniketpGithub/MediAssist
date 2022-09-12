package com.example.MedicalAssistance.repository

import com.example.MedicalAssistance.model.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository: ReactiveMongoRepository<User, Long> {
    fun findByUsername(username: String?): User?
}