package com.example.MedicalAssistance.repository

import com.example.MedicalAssistance.model.BookAppointment
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface BookAppointmentRepository : ReactiveMongoRepository<BookAppointment, String> {
}