package com.example.MedicalAssistance.controller

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
    fun saveAppointment(@RequestBody bookAppointment: BookAppointment) : Mono<BookAppointment> {
        return bookAppointmentService.addAppointment(bookAppointment)
    }


    @PutMapping("/updateAppointment/{id}")
    fun updateAppointment(@PathVariable("id") id: String,@RequestBody bookAppointment: BookAppointment): Mono<BookAppointment> {
        return bookAppointmentService.updateAppointment(bookAppointment)
    }

    @DeleteMapping("/Appointment/{id}")
    fun deleteAppointment(@PathVariable id: String): Mono<Void> {
        return bookAppointmentService.deleteById(id)
    }

    @GetMapping("/allAppointments")
    fun getAllAppointments(): Flux<BookAppointment> {
        return bookAppointmentService.findAllAppointments()
    }




}