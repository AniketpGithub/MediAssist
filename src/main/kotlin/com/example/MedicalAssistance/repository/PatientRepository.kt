package com.example.MedicalAssistance.repository

import com.example.MedicalAssistance.model.Patient
import com.example.MedicalAssistance.model.PatientRequest
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface PatientRepository : ReactiveMongoRepository<Patient, String>

{
@Query("{'userName':?0}")
fun findByName(patientRequest: PatientRequest): Mono<Patient>

}