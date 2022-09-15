package com.example.MedicalAssistance.service

import com.example.MedicalAssistance.exception.RecordNotFoundException
import com.example.MedicalAssistance.model.Patient
import com.example.MedicalAssistance.model.PatientRequest
import com.example.MedicalAssistance.repository.PatientRepository
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class PatientLoginService(

    @Autowired
    val patientRepository: PatientRepository
) {


    //    @PostMapping("/signin")
//    fun authenticateUser(@RequestBody patient: Patient): Mono<String?>? {
//        val authentication: Authentication = authenticationManager.authenticate(
//            UsernamePasswordAuthenticationToken(
//                loginDto.getUsernameOrEmail(), loginDto.getPassword()
//            )
//        )
//        SecurityContextHolder.getContext().setAuthentication(authentication)
//        return ResponseEntity("User signed-in successfully!.", HttpStatus.OK)
//    }


    fun patientLogin(patientRequest: PatientRequest ): Mono<Patient> {
        return patientRepository.findByName(patientRequest)
    }

//    fun patientLogin(patientRequest: PatientRequest ): Mono<Patient> {
//        val findPatientByName : Optional<Patient?> = patientRepository.findByName(patientRequest.username)
//        if(findPatientByName.isPresent()){
//
//        }
//        else
//            throw RecordNotFoundException("User not exist")
//
//        return patientRepository.findByName(patientRequest)
//    }

   // val findBookingById: Optional<Booking?> = bookingDao!!.findById(changedBooking!!.bookingId!!)

//    if (findBookingById.isPresent()) {
//        bookingDao!!.save(changedBooking)
//    } else throw RecordNotFoundException(
//    "Booking with Booking Id: " + changedBooking.bookingId + " not exists!!"
//    )
//    return changedBooking
}