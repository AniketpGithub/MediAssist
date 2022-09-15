package com.example.MedicalAssistance.repository

import com.example.MedicalAssistance.model.BookAppointment
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BookAppointmentRepository : ReactiveMongoRepository<BookAppointment, String> {
}