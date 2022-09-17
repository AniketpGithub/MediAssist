package com.example.MedicalAssistance.service

import com.example.MedicalAssistance.exception.RecordAlreadyPresentException
import com.example.MedicalAssistance.model.BookAppointment
import com.example.MedicalAssistance.model.Patient
import com.example.MedicalAssistance.repository.BookAppointmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*


@Service
class BookAppointmentService(
    @Autowired
    val bookAppointmentRepository: BookAppointmentRepository

) {

    fun addAppointment(bookAppointment: BookAppointment):Mono<BookAppointment>{
        return bookAppointmentRepository.save(bookAppointment)
    }

//    fun addAppointment(newBooking: BookAppointment?): Mono<BookAppointment>? {
//        val findBookingById: Optional<BookAppointment?> = bookAppointmentRepository.findById(newBooking.patientId)
//        return try {
//            if (!findBookingById.isPresent()) {
//                bookAppointmentRepository!!.save(newBooking)
//                ResponseEntity(newBooking, HttpStatus.OK)
//            }
//            else throw RecordAlreadyPresentException(
//                "Booking with Booking Id: " + newBooking.patientId + " already exists!!"
//            )
//        } catch (e: RecordAlreadyPresentException) {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }




    fun updateAppointment( id:String, bookAppointment: BookAppointment):Mono<BookAppointment>{
        return bookAppointmentRepository.save(bookAppointment)
    }


    fun deleteById(id: String): Mono<Void> {
        return bookAppointmentRepository.deleteById(id)
    }

    fun findAllAppointments() : Flux<BookAppointment> {
        return bookAppointmentRepository.findAll()
    }


}