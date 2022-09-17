package com.example.MedicalAssistance.controller

import com.example.MedicalAssistance.model.Patient
import com.example.MedicalAssistance.model.PatientRequest
import com.example.MedicalAssistance.repository.PatientRepository
import com.example.MedicalAssistance.service.PatientLoginService
import com.example.MedicalAssistance.service.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.servlet.http.HttpSession


@RestController
@CrossOrigin(origins = ["http://localhost:3000"], maxAge=3600, allowCredentials = "true")
class PatientLoginController(

    @Autowired

    val patientLoginService: PatientLoginService
) {



    @PostMapping("/signin")
    fun authenticateUser(@RequestBody patientRequest: PatientRequest) : Mono<Patient>
    {
        return patientLoginService.patientLogin(patientRequest)
  }
}