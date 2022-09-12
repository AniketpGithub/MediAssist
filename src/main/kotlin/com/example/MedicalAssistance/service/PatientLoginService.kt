package com.example.MedicalAssistance.service

import com.example.MedicalAssistance.model.Patient
import com.example.MedicalAssistance.model.PatientRequest
import com.example.MedicalAssistance.repository.PatientRepository
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

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
}