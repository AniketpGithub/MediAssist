package com.example.MedicalAssistance.controller

import com.example.MedicalAssistance.exception.RecordAlreadyPresentException
import com.example.MedicalAssistance.exception.RecordNotFoundException
import com.example.MedicalAssistance.model.BookAppointment
import com.example.MedicalAssistance.model.Patient

import com.example.MedicalAssistance.repository.BookAppointmentRepository
import com.example.MedicalAssistance.service.BookAppointmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@CrossOrigin(origins = ["http://localhost:3000"], maxAge=3600, allowCredentials = "true")
class BookAppointmentController (

    @Autowired
    val bookAppointmentService: BookAppointmentService

        ){

    @PostMapping("/bookAppointment")
    @ExceptionHandler(RecordAlreadyPresentException::class)
    fun saveAppointment(@RequestBody bookAppointment: BookAppointment) : Mono<BookAppointment> {
        return bookAppointmentService.addAppointment(bookAppointment)
    }


    @PutMapping("/updateAppointment/{id}")
    @ExceptionHandler(RecordNotFoundException::class)
    fun updateAppointment(@PathVariable id: String,@RequestBody bookAppointment: BookAppointment): Mono<BookAppointment> {
        return bookAppointmentService.updateAppointment(id,bookAppointment)
    }

    @DeleteMapping("/appointment/{id}")
    @ExceptionHandler(RecordNotFoundException::class)
    fun deleteAppointment(@PathVariable id: String): Mono<Void> {
        return bookAppointmentService.deleteById(id)
    }

    @GetMapping("/allAppointments")
    @ExceptionHandler(RecordNotFoundException::class)
    fun getAllAppointments(): Flux<BookAppointment> {
        return bookAppointmentService.findAllAppointments()
    }




}