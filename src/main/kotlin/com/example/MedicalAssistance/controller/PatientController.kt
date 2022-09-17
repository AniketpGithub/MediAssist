package com.example.MedicalAssistance.controller


import com.example.MedicalAssistance.model.Patient
import com.example.MedicalAssistance.repository.PatientRepository
import com.example.MedicalAssistance.service.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController

@CrossOrigin(origins = ["http://localhost:3000"], maxAge=3600, allowCredentials = "true")
class PatientController (

    @Autowired
   val patientRepository: PatientRepository,
   val patientService: PatientService
        ){

    @GetMapping("/AllPatients")
    fun getAllPatients(): Flux<Patient> {
        return patientService.findAllPatients()
    }

    @GetMapping("{id}")
    fun getPatientById(@PathVariable id: String): Mono<Patient> {
        return patientService.findById(id)
    }

    @PostMapping("/CreatePatients")
    fun createPatient(@RequestBody patient: Patient): Mono<Patient> {
        return patientService.addPatient(patient)
    }

    @PutMapping("/update/{id}")
    fun updateById( @PathVariable id: String,@RequestBody  patient: Patient): Mono<Patient> {
        return patientService.updatePatientById(id,patient)
    }




    @DeleteMapping
    fun delete(): Mono<Void> {
        return patientRepository.deleteAll()
    }


    @DeleteMapping("/patients/{id}")
    fun deletePatient(@PathVariable id: String): Mono<Void> {
        return patientService.deleteById(id)
    }


}


